package com.demo.attend.controller;

import com.demo.attend.domain.entity.Result;
import com.demo.attend.domain.entity.User;
import com.demo.attend.domain.entity.VerificationRegisterEmail;
import com.demo.attend.mapper.UserMapper;
import com.demo.attend.mapper.VerificationRegisterEmailMapper;
import com.demo.attend.service.EmailService;
import com.demo.attend.util.PasswordEncoderUtil;
import com.demo.attend.util.ResultUtil;
import com.demo.attend.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class ForgetPasswdController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerificationRegisterEmailMapper verificationRegisterEmailMapper;
    @Autowired
    private TimeProvider timeProvider;

    @PostMapping(value="${path.forgetPasswd}")
    public Result getVerificationCode(String email) {
        logger.info(email + "访问 forgetPasswd");
        email = email.trim();
        Random r = new Random();
        int verfiCode = r.nextInt(900000) + 100000;
        Date overDue= timeProvider.overDuebyMinute(10);
        VerificationRegisterEmail v = verificationRegisterEmailMapper.selectByPrimaryKey(email);
        if(!(v != null && v.getVeriIsRegistered())) {
            return ResultUtil.error(101, "该邮箱未与用户帐号绑定");
        } else {
            logger.info(email + " 重置密码验证邮箱");
            v.setVeriCode(verfiCode);
            v.setVeriOverdue(overDue);
            verificationRegisterEmailMapper.updateByPrimaryKey(v);
        }

        String content="<html>\n" +
                "<body>\n" +
                "    <h3>尊敬的用户：</h3>\n" +
                "<p>您的邮箱验证码为：" + verfiCode + "</p>" +
                "<p>十分钟内有效，如非本人操作，请忽略本邮件。</p>" +
                "</body>\n" +
                "</html>";
        emailService.sendVerificationCode(email, content);
        return ResultUtil.success(200, "验证码发送成功");
    }

    @GetMapping(value="${path.forgetVerify}")
    public Result forgetVerify(String email, Integer veriCode) {
        logger.info(email + " forgetVerify");
        VerificationRegisterEmail v = verificationRegisterEmailMapper.selectByPrimaryKey(email);
        if(v != null && v.getVeriIsRegistered() && v.getVeriOverdue().after(timeProvider.now())
                && v.getVeriCode().equals(veriCode)) {
            logger.info(email + " 验证码正确");
            return ResultUtil.success(200, "验证码正确");
        } else {
            logger.info(email + "验证失败");
            return ResultUtil.error(101, "验证失败");
        }

    }

    @PostMapping(value="${path.forgetModify}")
    public Result forgetModify(String email, Integer veriCode, String passwd) {

        /**
         * 重置密码之前再一次校对之前发送的验证码，验证成功才可重置密码
         *  200 成功
         *  101 失败
         */
        Result result = this.forgetVerify(email, veriCode);
        if (result.getCode().equals(200)) {
            User user = userMapper.selectByEmail(email);
            user.setUserIsLogin(Boolean.FALSE);
            user.setUserPasswd(PasswordEncoderUtil.passwordEncode(passwd));
            user.setUserLastResetPasswdTime(timeProvider.now());
            userMapper.updateByPrimaryKey(user);
            logger.info(user.getUserUserName() + " 重置密码成功");
            return ResultUtil.success(200, "重置密码成功");
        } else if (result.getCode().equals(101)) {
            return ResultUtil.error(101, "重置密码失败");
        }
        return ResultUtil.error(101, "重置密码失败");
    }

}
