package com.demo.attend.controller;


import com.demo.attend.domain.dict.AttendCheckReturnObj;
import com.demo.attend.domain.dict.AttendItemReturnObj;
import com.demo.attend.domain.entity.*;
import com.demo.attend.mapper.AttendMapper;
import com.demo.attend.mapper.AttendOrgMapper;
import com.demo.attend.mapper.UserAttendMapper;
import com.demo.attend.mapper.UserOrgAttendMapper;
import com.demo.attend.util.QrCodeUtil;
import com.demo.attend.util.ResultUtil;
import com.demo.attend.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasRole('USER')")
@RestController
public class AttendController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AttendMapper attendMapper;
    @Autowired
    private UserAttendMapper userAttendMapper;
    @Autowired
    private UserOrgAttendMapper userOrgAttendMapper;
    @Autowired
    private AttendOrgMapper attendOrgMapper;
    @Autowired
    private TimeProvider timeProvider;

    @PostMapping(value = "${path.createAttend}")
    public Result createAttend(Attend attend) {
        logger.info(attend.getAttendTitle()+ "112222222222222222222222222222222222");
        logger.info("createAttend 1111111111111111111111111111111");
        AttendOrg attendOrg = attendOrgMapper.selectByPrimaryKey(attend.getFkOrgAttendId());
        Integer orgPeopleNum = attendOrg.getOrgAttendPeopleNum();
        attend.setAttendPeopleNum(orgPeopleNum - 1);
        attend.setAttendTrueNum(0);
        attend.setAttendLateNum(0);
        attend.setAttendFalseNum(orgPeopleNum - 1);

        attend.setAttendCreateTime(timeProvider.now());
        attend.setAttendQrCodeOverDue(timeProvider.overDuebySecond(attend.getAttendQrCodeRefreshSecond()));
        attend.setAttendQrCode(attend.getAttendCreateTime().toString());

        if(attendMapper.insert(attend) == 1){
            attend.setAttendQrCode("ATTEND" + QrCodeUtil.qrCodeSeparate +attend.getAttendQrCode() + QrCodeUtil.qrCodeSeparate + attend.getPkAttendId()); //标志， 创建时间，考勤id
            attendMapper.updateByPrimaryKey(attend);
            //对考勤组织内每个成员生成考勤记录
            //签到时间未开始， 但默认为签到时间为【创建考勤时间】，签到状态为【缺勤】
            List<Long> uIdList = userOrgAttendMapper.selectUserIdsByFkOrgAttendId(attend.getFkOrgAttendId());
            for(Long id: uIdList) {
                userAttendMapper.insert(new UserAttend(attend.getPkAttendId(), id,
                        timeProvider.now(), "False"));
            }
            return ResultUtil.success(200, "创建成功");
        } else {
            return ResultUtil.error(101, "创建失败");
        }
    }

    @GetMapping(value = "${path.getAttendItemsByClickOrgAttend}")
    public Result<List<Attend>> getAttendItemsByClickOrgAttend(Long orgAttendId){
        List<Attend> attends = attendMapper.selectAttendsByFkOrgAttendId(orgAttendId);

        List<AttendItemReturnObj> returnObjs = new ArrayList<AttendItemReturnObj>();
        for(Attend attend: attends) {
            returnObjs.add(new AttendItemReturnObj(attend.getPkAttendId(),
                    attend.getAttendTitle(), attend.getAttendStartTime(), attend.getAttendOverTime(), attend.getAttendLocation()));
        }

        return ResultUtil.success(200, "获取列表成功", returnObjs);
    }

    @GetMapping(value = "${path.getAttendDetailByClickListItem}")
    public Result<AttendItemReturnObj> getAttendDetailByClickListItem(Long attendId) {

        Attend attend = attendMapper.selectByPrimaryKey(attendId);

        AttendItemReturnObj returnObj = new AttendItemReturnObj(attend.getPkAttendId(),
                attend.getAttendTitle(), attend.getAttendStartTime(), attend.getAttendOverTime(),
                attend.getAttendLocation());

        return ResultUtil.success(200, "获取成功", returnObj);
    }

    @PostMapping(value = "${path.attendCheck}")
    public Result attendCheck(String qrCode, Long userId) {
        String[] createTimeAndAttendId = qrCode.split(QrCodeUtil.qrCodeSeparate);
        Attend attend = attendMapper.selectByPrimaryKey(Long.valueOf(createTimeAndAttendId[2]));
        UserAttend userAttend = userAttendMapper.selectByPrimaryKey(new UserAttendKey(Long.valueOf(createTimeAndAttendId[2]), userId));
        AttendOrg attendOrg = attendOrgMapper.selectByPrimaryKey(attend.getFkOrgAttendId());

        if(! (attend.getAttendQrCode().equals(qrCode) && attend.getAttendQrCodeOverDue().after(timeProvider.now()))){
            ResultUtil.error(101, "二维码失效");
        }
        if(userAttend == null) {
            return ResultUtil.error(102, "未参与考勤");
        }
        if(! userAttend.getAttendState().equals("False")) {
            return ResultUtil.error(103, "已签到过");
        }
        userAttend.setAttendTime(timeProvider.now());
        if(attend.getAttendStartTime().after(userAttend.getAttendTime())) {
            attend.setAttendTrueNum(attend.getAttendTrueNum() + 1);
            attend.setAttendFalseNum(attend.getAttendFalseNum() - 1); //因为默认【缺勤】人数为参与签到的人数，签到一个人时减一
            userAttend.setAttendState("True");
            userAttendMapper.updateByPrimaryKey(userAttend);
            attendMapper.updateByPrimaryKey(attend);
            return ResultUtil.success(200, "签到成功", new AttendCheckReturnObj(attend.getPkAttendId(),
                    attendOrg.getPkOrgAttendId(), attend.getAttendTitle(), attend.getAttendStartTime(), attend.getAttendOverTime(), attendOrg.getOrgAttendName(), userAttend.getAttendTime(), userAttend.getAttendState()));
        } else if(attend.getAttendOverTime().after(userAttend.getAttendTime())){
            attend.setAttendLateNum(attend.getAttendLateNum() + 1);
            attend.setAttendFalseNum(attend.getAttendFalseNum() - 1);
            userAttend.setAttendState("Late");
            userAttendMapper.updateByPrimaryKey(userAttend);
            attendMapper.updateByPrimaryKey(attend);
            return ResultUtil.success(201, "已迟到", new AttendCheckReturnObj(attend.getPkAttendId(),
                    attendOrg.getPkOrgAttendId(), attend.getAttendTitle(), attend.getAttendStartTime(), attend.getAttendOverTime(), attendOrg.getOrgAttendName(), userAttend.getAttendTime(), userAttend.getAttendState()));
        } else {
            return ResultUtil.success(202, "已缺勤", new AttendCheckReturnObj(attend.getPkAttendId(),
                    attendOrg.getPkOrgAttendId(), attend.getAttendTitle(), attend.getAttendStartTime(), attend.getAttendOverTime(), attendOrg.getOrgAttendName(), userAttend.getAttendTime(), userAttend.getAttendState()));
        }
    }

    @PostMapping(value = "${path.updateAttend}")
    public Result updateAttend(Attend attend) {
        if(attend.getAttendQrCodeRefreshSecond() != null) {
            attend.setAttendQrCodeOverDue(timeProvider.overDuebySecond(attend.getAttendQrCodeRefreshSecond()));
        }
        attendMapper.updateByPrimaryKey(attend);

        return ResultUtil.success(200, "更新成功");
    }

    @PostMapping(value = "${path.deleteAttend}")
    public Result deleteOrgAttend(Long attendId, Long userId) {
        if(attendMapper.selectByPrimaryKey(attendId).getAttendCreateById().equals(userId)) {
            userAttendMapper.deleteByPrimaryKey(new UserAttendKey(attendId, userId));
            attendMapper.set_foreign_key_off();
            attendMapper.deleteByPrimaryKey(attendId);
            attendMapper.set_foreign_key_on();
            return ResultUtil.success(200, "删除成功");
        }
        return ResultUtil.error(101, "删除失败");
    }

}
