package com.demo.attend.controller;

import com.demo.attend.domain.dict.OrgAttendAttendItemReturnObj;
import com.demo.attend.domain.dict.UserAttendByStateItemReturnObj;
import com.demo.attend.domain.dict.UserAttendItemReturnObj;
import com.demo.attend.domain.entity.*;
import com.demo.attend.mapper.AttendMapper;
import com.demo.attend.mapper.AttendOrgMapper;
import com.demo.attend.mapper.UserAttendMapper;
import com.demo.attend.mapper.UserMapper;
import com.demo.attend.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PreAuthorize("hasRole('USER')")
@RestController
public class AttendRecordController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserAttendMapper userAttendMapper;
    @Autowired
    private AttendMapper attendMapper;
    @Autowired
    private AttendOrgMapper attendOrgMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "${path.getUserAttendRecordByDate}")
    public Result<List<UserAttendItemReturnObj>> getUserAttendRecordByDate(Date date, Long userId) {
        List<UserAttend> userAttendList = userAttendMapper.selectByFkUserId(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        List<UserAttendItemReturnObj> recordItemReturnObjs = new ArrayList<UserAttendItemReturnObj>();

        for (UserAttend uA: userAttendList) {
            if(sdf.format(uA.getAttendTime()).equals(sdf.format(date))) {
                logger.info("attendID" + uA.getFkAttendId());
                Attend a = attendMapper.selectByPrimaryKey(uA.getFkAttendId());
                if(a == null) {
                    logger.info("attend nulllllllllllllllllllll");
                }
                logger.info(a.getAttendTitle() + "  " + a.getFkOrgAttendId());
                AttendOrg attendOrg = attendOrgMapper.selectByPrimaryKey(a.getFkOrgAttendId());
                if(attendOrg == null) {
                    logger.info("org nullllllllllllllllllllllllll");
                }
                recordItemReturnObjs.add(new UserAttendItemReturnObj(a.getPkAttendId(), a.getAttendTitle(),a.getAttendStartTime(),
                        a.getAttendOverTime(), a.getAttendLocation(), attendOrg==null?"该组织已不存在":attendOrg.getOrgAttendName(), uA.getAttendTime(), uA.getAttendState()));
            }
        }
        return ResultUtil.success(200, "获取列表成功", recordItemReturnObjs);
    }

    @GetMapping(value = "${path.getMyCreatedOrgAttendRecordByDate}")
    public Result<List<OrgAttendAttendItemReturnObj>> getMyCreatedOrgAttendRecordByDate(Long userId, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        List<AttendOrg> attendOrgList = attendOrgMapper.selectByOrgAttendCreateById(userId);
        List<OrgAttendAttendItemReturnObj> returnObjList = new ArrayList<OrgAttendAttendItemReturnObj>();

        for(AttendOrg aO: attendOrgList) {
            List<Attend> attendList = attendMapper.selectAttendsByFkOrgAttendId(aO.getPkOrgAttendId());
            for(Attend a: attendList) {
                logger.info(aO.getOrgAttendName()+"  "+ a.getAttendTitle() + sdf.format(date) +"   "+ sdf.format(a.getAttendStartTime()));
                if(sdf.format(a.getAttendStartTime()).equals(sdf.format(date))) {
                    returnObjList.add(new OrgAttendAttendItemReturnObj(a.getPkAttendId(), a.getAttendTitle(), a.getAttendStartTime(),
                            a.getAttendOverTime(), a.getAttendLocation(),
                            aO.getOrgAttendName(), a.getAttendPeopleNum(), a.getAttendTrueNum(), a.getAttendLateNum(), a.getAttendFalseNum()));
                }
            }
        }
        return ResultUtil.success(200, "获取列表成功", returnObjList);
    }

    @GetMapping(value = "${path.getUserAttendByState}")
    public Result getUserAttendByState(Long attendId, String state) {
        Attend attend = attendMapper.selectByPrimaryKey(attendId);
        List<UserAttend> userAttendList = userAttendMapper.selectByFkAttendId(attendId);
        List<UserAttendByStateItemReturnObj> returnObjs = new ArrayList<UserAttendByStateItemReturnObj>();
        for(UserAttend uA: userAttendList) {
            if(uA.getAttendState().equals(state) || state.equals("Should")) {
                User user = userMapper.selectByPrimaryKey(uA.getFkUserId());
                returnObjs.add(new UserAttendByStateItemReturnObj(attend.getPkAttendId(), attend.getAttendTitle(),
                        attend.getAttendStartTime(), attend.getAttendOverTime(), attend.getAttendLocation(), user.getPkUserId(), user.getUserTrueName(),
                        uA.getAttendTime()));
            }
        }
        return ResultUtil.success(200, "获取列表成功", returnObjs);
    }
}
