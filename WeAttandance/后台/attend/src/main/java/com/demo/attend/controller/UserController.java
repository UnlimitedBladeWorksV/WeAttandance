package com.demo.attend.controller;


import com.demo.attend.domain.dict.UserItemReturnObj;
import com.demo.attend.domain.entity.Result;
import com.demo.attend.domain.entity.User;
import com.demo.attend.domain.entity.UserOrgAttend;
import com.demo.attend.domain.entity.UserOrgAttendKey;
import com.demo.attend.mapper.AttendOrgMapper;
import com.demo.attend.mapper.UserMapper;
import com.demo.attend.mapper.UserOrgAttendMapper;
import com.demo.attend.security.jwt.JwtTokenUtil;
import com.demo.attend.util.PasswordEncoderUtil;
import com.demo.attend.util.ResultUtil;
import com.demo.attend.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasRole('USER')")
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserOrgAttendMapper userOrgAttendMapper;
    @Autowired
    private AttendOrgMapper attendOrgMapper;
    @Autowired
    private TimeProvider timeProvider;

    @GetMapping(value = "${path.getUserManageList}")
    public Result<List<UserItemReturnObj>> getUserManageList(Long orgAttendId) {
        List<Long> userIds = userOrgAttendMapper.selectUserIdsByFkOrgAttendId(orgAttendId);
        List<UserItemReturnObj> returnObjs = new ArrayList<UserItemReturnObj>();
        for(Long id: userIds) {
            User u = userMapper.selectByPrimaryKey(id);
            returnObjs.add(new UserItemReturnObj(u.getPkUserId(), u.getUserTrueName()));
        }

        return ResultUtil.success(200, "获取列表成功", returnObjs);
    }

    @PostMapping(value = "${path.deleteUserFromOrgAttendByList}")
    public Result deleteUserByList(List<Long> userIds, Long orgAttendId, Long userId) {
        if(attendOrgMapper.selectByPrimaryKey(orgAttendId).getOrgAttendCreateById().equals(userId)) {
            for(Long id: userIds) {
                userOrgAttendMapper.deleteByPrimaryKey(new UserOrgAttendKey(id, orgAttendId));
            }
            return ResultUtil.success(200, "删除成功");
        }

        return ResultUtil.error(101, "删除失败");
    }

    @PostMapping(value = "${path.updateUserInfo}")
    public Result updateUserInfo(User user) {
        logger.info(String.valueOf(user.getUserSex()));
        userMapper.updateByPrimaryKeySelective(user);
        User uToReturn = userMapper.selectByPrimaryKey(user.getPkUserId());
        return ResultUtil.success(200, "更新成功", uToReturn);
    }

    @PostMapping(value = "${path.updatePasswd}")
    public Result updatePasswd(String oldPasswd, String newPasswd, Long userId) {
        User u = userMapper.selectByPrimaryKey(userId);
        if(PasswordEncoderUtil.passwordEncoder().matches(oldPasswd, u.getUserPasswd())) {
            u.setUserPasswd(PasswordEncoderUtil.passwordEncode(newPasswd));
            u.setUserLastResetPasswdTime(timeProvider.now());
            userMapper.updateByPrimaryKey(u);
            logger.info(u.getUserUserName() + "   修改成功");
            final UserDetails userDetails = userDetailsService.loadUserByUsername(u.getUserUserName());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResultUtil.success(200, "修改成功", token);
        } else {
            logger.info("旧密码错误");
            return ResultUtil.error(101, "旧密码错误");
        }
    }

}
