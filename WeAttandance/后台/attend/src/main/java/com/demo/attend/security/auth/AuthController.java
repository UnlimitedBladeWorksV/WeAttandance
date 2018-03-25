package com.demo.attend.security.auth;

import com.demo.attend.domain.entity.Result;
import com.demo.attend.domain.entity.User;
import com.demo.attend.security.jwt.JwtAuthenticationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @GetMapping(value = "${jwt.route.authentication.path}")
    public Result createAuthenticationToken(
             JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
        logger.info(authenticationRequest.getUsername() + "用户登录");

        // reutrn Result entity
        return authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    }

    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public Result refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        logger.info("TOKEN 刷新");
        String token = request.getHeader(tokenHeader);

        return authService.refresh(token);

    }

    @PostMapping(value = "${jwt.route.authentication.register}")
    public Result register(User addedUser, Integer veriCode) throws AuthenticationException{
        logger.info(addedUser.getUserUserName() + ": 用户注册");
        return authService.register(addedUser, veriCode);
    }
}
