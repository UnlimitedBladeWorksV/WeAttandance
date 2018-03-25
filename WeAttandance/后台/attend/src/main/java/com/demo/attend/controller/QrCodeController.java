package com.demo.attend.controller;

import com.demo.attend.domain.dict.AttendQrCodeReturnObj;
import com.demo.attend.domain.entity.Attend;
import com.demo.attend.domain.entity.AttendOrg;
import com.demo.attend.domain.entity.Result;
import com.demo.attend.mapper.AttendMapper;
import com.demo.attend.mapper.AttendOrgMapper;
import com.demo.attend.util.QrCodeUtil;
import com.demo.attend.util.ResultUtil;
import com.demo.attend.util.TimeProvider;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@PreAuthorize("hasRole('USER')")
@RestController
public class QrCodeController {

    @Autowired
    private AttendMapper attendMapper;
    @Autowired
    private AttendOrgMapper attendOrgMapper;
    @Autowired
    private TimeProvider timeProvider;

    @GetMapping(value = "${path.qrCodeCheck}")
    public Result qrCodeCheck(String qrCode) {

        if(qrCode.startsWith("ATTEND")) {

            return ResultUtil.success(200, "请跳转attendCheck");
        } else if(qrCode.startsWith("ORGATTEND")) {
            return ResultUtil.success(201, "请跳转joinOrgAttendByQrCode");
        }
        return ResultUtil.error(101, "无效的二维码");
    }

    @PostMapping(value = "${path.getNewAttendQrCode}")
    public Result getNewAttendQrCode(Long attendId, Long userId) {

        Attend attend = attendMapper.selectByPrimaryKey(attendId);
        if(attend.getAttendCreateById().equals(userId) && attend.getAttendOverTime().after(timeProvider.now())) {
            String newQrCode = "ATTEND" + QrCodeUtil.qrCodeSeparate + timeProvider.now().toString() + QrCodeUtil.qrCodeSeparate + attend.getPkAttendId();
            attend.setAttendQrCode(newQrCode);
            attend.setAttendQrCodeOverDue(timeProvider.overDuebySecond(attend.getAttendQrCodeRefreshSecond()));
            attendMapper.updateByPrimaryKey(attend);
            return ResultUtil.success(200, "获取成功", new AttendQrCodeReturnObj(newQrCode, attend.getAttendQrCodeRefreshSecond()));
        }
        return ResultUtil.error(101, "获取失败");

    }

    @PostMapping(value = "${path.getOrgAttendQrCode}")
    public Result getOrgAttendQrCode(Long orgAttendId, Long userId) {

        AttendOrg attendOrg = attendOrgMapper.selectByPrimaryKey(orgAttendId);
        if(attendOrg.getOrgAttendCreateById().equals(userId)){
            if(attendOrg.getOrgAttendQrCodeOverdue().after(timeProvider.now())) {
                String oldQrCode = attendOrg.getOrgAttendQrCode();
                return ResultUtil.success(200, "获取成功", oldQrCode);
            } else {
                String newQrCode = "ORGATTEND" + timeProvider.now().toString() + attendOrg.getPkOrgAttendId();
                attendOrg.setOrgAttendQrCode(newQrCode);
                attendOrg.setOrgAttendQrCodeOverdue(timeProvider.overDuebyDay(attendOrg.getOrgAttendQrCodeEffectiveDays()));

                attendOrgMapper.updateByPrimaryKey(attendOrg);
                return ResultUtil.success(201, "获取成功", newQrCode);
            }
        }
        return ResultUtil.error(101, "获取失败");
    }
}
