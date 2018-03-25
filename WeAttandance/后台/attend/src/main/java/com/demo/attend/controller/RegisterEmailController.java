package com.demo.attend.controller;

import com.demo.attend.domain.entity.Result;
import com.demo.attend.domain.entity.VerificationRegisterEmail;
import com.demo.attend.mapper.VerificationRegisterEmailMapper;
import com.demo.attend.service.EmailService;
import com.demo.attend.util.ResultUtil;
import com.demo.attend.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class RegisterEmailController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationRegisterEmailMapper verificationRegisterEmailMapper;
    @Autowired
    private TimeProvider timeProvider;

    @PostMapping(value="${path.getVerificationCode}")
    public Result getVerificationCode(String email) {
        logger.info(email + "访问 getVerificationCode");
        email = email.trim();
        Random r = new Random();
        int verfiCode = r.nextInt(900000) + 100000;
        Date overDue= timeProvider.overDuebyMinute(10);
        VerificationRegisterEmail v = verificationRegisterEmailMapper.selectByPrimaryKey(email);
        if(v != null && v.getVeriIsRegistered()) {

            return ResultUtil.error(101, "邮箱已被注册");
        } else if(v != null && !v.getVeriIsRegistered()){
            v = new VerificationRegisterEmail(email, verfiCode, overDue, false);
            verificationRegisterEmailMapper.updateByPrimaryKey(v);
        } else {
            v = new VerificationRegisterEmail(email, verfiCode, overDue, false);
            verificationRegisterEmailMapper.insert(v);
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

}