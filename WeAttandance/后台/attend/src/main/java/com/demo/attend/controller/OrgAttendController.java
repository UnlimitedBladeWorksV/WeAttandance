package com.demo.attend.controller;

import com.demo.attend.domain.dict.OrgAttendItemReturnObj;
import com.demo.attend.domain.entity.*;
import com.demo.attend.mapper.AttendMapper;
import com.demo.attend.mapper.AttendOrgMapper;
import com.demo.attend.mapper.UserOrgAttendMapper;
import com.demo.attend.util.QrCodeUtil;
import com.demo.attend.util.ResultUtil;
import com.demo.attend.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasRole('USER')")
@RestController
public class OrgAttendController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TimeProvider timeProvider;
    @Autowired
    private AttendOrgMapper attendOrgMapper;
    @Autowired
    private UserOrgAttendMapper userOrgAttendMapper;

    @GetMapping(value="${path.myCreatedOrgAttend}")
    public Result<List<AttendOrg>> myCreatedOrgAttend(Long userId) {

        List<AttendOrg> orgAttends = attendOrgMapper.selectByOrgAttendCreateById(userId);

        List<OrgAttendItemReturnObj> returnObjs = new ArrayList<OrgAttendItemReturnObj>();
        for(AttendOrg org: orgAttends) {
            returnObjs.add(new OrgAttendItemReturnObj(org.getPkOrgAttendId(), org.getOrgAttendName(), org.getOrgAttendDescription(), null));
        }

        return ResultUtil.success(200, "获取列表成功", returnObjs);
    }

    @GetMapping(value = "${path.myJoinedOrgAttend}")
    public Result<List<AttendOrg>> myJoinedOrgAttend(Long userId) {
        List<Long> orgAttendIds = userOrgAttendMapper.selectOrgAttendIdsByFkUserId(userId);
        List<OrgAttendItemReturnObj> returnObjs = new ArrayList<OrgAttendItemReturnObj>();
        for(Long id: orgAttendIds){
            AttendOrg attendOrg = attendOrgMapper.selectByPrimaryKey(id);
            returnObjs.add(new OrgAttendItemReturnObj(attendOrg.getPkOrgAttendId(), attendOrg.getOrgAttendName(), attendOrg.getOrgAttendDescription(), null));
        }

        return ResultUtil.success(200, "获取列表成功", returnObjs);
    }

    @PostMapping(value = "${path.createOrgAttend}")
    public Result createOrgAttend(AttendOrg attendOrg) {

        attendOrg.setOrgAttendCreateTime(timeProvider.now());
        attendOrg.setOrgAttendQrCode(attendOrg.getOrgAttendCreateTime().toString());
        attendOrg.setOrgAttendQrCodeOverdue(timeProvider.overDuebyDay(attendOrg.getOrgAttendQrCodeEffectiveDays()));
        attendOrg.setOrgAttendPeopleNum(1);

        if(attendOrgMapper.insert(attendOrg) == 1){
            logger.info("新建组织  " + String.valueOf(attendOrg.getPkOrgAttendId()));
            //标志， 创建时间， 考勤组织id
            attendOrg.setOrgAttendQrCode("ORGATTEND" + QrCodeUtil.qrCodeSeparate + attendOrg.getOrgAttendQrCode() + QrCodeUtil.qrCodeSeparate + attendOrg.getPkOrgAttendId());

            attendOrgMapper.updateByPrimaryKey(attendOrg);
            OrgAttendItemReturnObj returnObj = new OrgAttendItemReturnObj(attendOrg.getPkOrgAttendId(), attendOrg.getOrgAttendName(),
                    attendOrg.getOrgAttendDescription(), attendOrg.getOrgAttendQrCode());
            return ResultUtil.success(200, "创建成功", returnObj);
        } else {
            return ResultUtil.error(101, "创建失败");
        }
    }


    @PostMapping(value = "${path.joinOrgAttendByQrCode}")
    public Result joinOrgAttendByQrCode(String qrCode, Long userId) {

        String[] createDateAndOrgAttendId = qrCode.split(QrCodeUtil.qrCodeSeparate);
        for (String s: createDateAndOrgAttendId) {
            logger.info(s+"1111111111111111111111111111111111111111111");
        }
        UserOrgAttend u = userOrgAttendMapper.selectByPrimaryKey(new UserOrgAttendKey(userId, Long.valueOf(createDateAndOrgAttendId[2])));
        if(u != null) {
             return ResultUtil.error(103, "已加入过组织");
        }
        AttendOrg attendOrg = attendOrgMapper.selectByPrimaryKey(Long.valueOf(createDateAndOrgAttendId[2]));
        if(! (attendOrg.getOrgAttendQrCode().equals(qrCode) && attendOrg.getOrgAttendQrCodeOverdue().after(timeProvider.now()))) {
            return ResultUtil.error(101, "二维码失效");
        }
        
        UserOrgAttend userOrgAttend = new UserOrgAttend();
        userOrgAttend.setFkUserId(userId);
        userOrgAttend.setFkOrgAttendId(Long.valueOf(createDateAndOrgAttendId[2])); //组织id放在二维码中
        userOrgAttend.setAttendFalseNum(0);
        userOrgAttend.setAttendTrueNum(0);
        userOrgAttend.setAttendLateNum(0);
        userOrgAttend.setUserAttendTime(timeProvider.now());

        if(userOrgAttendMapper.insert(userOrgAttend) == 1) {
            OrgAttendItemReturnObj returnObj = new OrgAttendItemReturnObj(attendOrg.getPkOrgAttendId(),
                    attendOrg.getOrgAttendName(),attendOrg.getOrgAttendDescription(), null);
            //考勤组织人数加一
            attendOrg.setOrgAttendPeopleNum(attendOrg.getOrgAttendPeopleNum() + 1);
            attendOrgMapper.updateByPrimaryKey(attendOrg);
            return ResultUtil.success(200, "加入成功", returnObj);
        }
        return ResultUtil.error(102, "加入失败");
    }


    @PostMapping(value = "${path.deleteOrgAttend}")
    public Result deleteOrgAttend(Long orgAttendId, Long userId) {
        if(attendOrgMapper.selectByPrimaryKey(orgAttendId).getOrgAttendCreateById().equals(userId)) {
            userOrgAttendMapper.deleteByFkOrgAttendId(orgAttendId);
            attendOrgMapper.set_foreign_key_off();
            attendOrgMapper.deleteByPrimaryKey(orgAttendId);
            attendOrgMapper.set_foreign_key_on();
            return ResultUtil.success(200, "删除成功");
        }
        return ResultUtil.error(101, "删除失败");
    }

    @GetMapping(value = "${path.getOrgAttend}")
    public Result getOrgAttend(Long orgAttendId, Long userId) {
        AttendOrg attendOrg = attendOrgMapper.selectByPrimaryKey(orgAttendId);
        if(attendOrg.getOrgAttendCreateById().equals(userId)) {
            return ResultUtil.success(200, "获取成功", attendOrg);
        }
        return ResultUtil.error(101, "获取失败");
    }
    @PostMapping(value = "${path.updateOrgAttend}")
    public Result updateOrgAttend(AttendOrg attendOrg, Long userId) {

        if(attendOrg.getOrgAttendCreateById().equals(userId)) {
            attendOrgMapper.updateByPrimaryKey(attendOrg);
            return ResultUtil.success(200, "更新成功");
        }
        return ResultUtil.error(101, "更新失败");
    }

    @PostMapping(value = "${path.withdrawOrgAttend")
    public Result withdrawOrgAttend(Long orgAttendId, Long userId) {

        if(userOrgAttendMapper.deleteByPrimaryKey(new UserOrgAttendKey(userId, orgAttendId)) == 1) {
            AttendOrg attendOrg = attendOrgMapper.selectByPrimaryKey(orgAttendId);
            attendOrg.setOrgAttendPeopleNum(attendOrg.getOrgAttendPeopleNum() - 1);
            ResultUtil.success(200, "退出成功");
        }
        return ResultUtil.error(101, "退出失败");
    }
}
