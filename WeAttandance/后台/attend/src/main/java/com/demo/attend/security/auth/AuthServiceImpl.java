package com.demo.attend.security.auth;



import com.demo.attend.domain.entity.Result;
import com.demo.attend.domain.entity.User;
import com.demo.attend.domain.entity.VerificationRegisterEmail;
import com.demo.attend.mapper.UserMapper;
import com.demo.attend.mapper.VerificationRegisterEmailMapper;
import com.demo.attend.security.enumerate.AuthorityName;
import com.demo.attend.security.jwt.JwtTokenUtil;
import com.demo.attend.security.jwt.JwtUser;
import com.demo.attend.util.PasswordEncoderUtil;
import com.demo.attend.util.ResultUtil;
import com.demo.attend.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationRegisterEmailMapper verificationRegisterEmailMapper;

    @Autowired
    private TimeProvider timeProvider;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Result register(User userToAdd, Integer veriCode) {
        final String userName = userToAdd.getUserUserName();
        final String email = userToAdd.getUserEmail();
        final Date date = timeProvider.now();
        if(userMapper.selectByUserUserName(userName)!=null) {
            return ResultUtil.error(101, "用户名已被使用");
        }
        VerificationRegisterEmail v = verificationRegisterEmailMapper.selectByPrimaryKey(email);
        if(v == null || !v.getVeriCode().equals(veriCode)) {
            return ResultUtil.error(102, "验证码错误");
        } else if(v.getVeriIsRegistered()) {
            return ResultUtil.error(103, "邮箱已被注册");
        } else if(v.getVeriOverdue().before(timeProvider.now())) {
            logger.info(v.getVeriOverdue() + " before " + timeProvider.now());
            return ResultUtil.error(104, "验证码已过期");
        }


        final String rawPassword = userToAdd.getUserPasswd();
        userToAdd.setUserPasswd(PasswordEncoderUtil.passwordEncode(rawPassword));

        userToAdd.setUserRegisterTime(date);
        userToAdd.setUserLastResetPasswdTime(date);
        userToAdd.setUserDescription("");
        userToAdd.setUserIsLogin(Boolean.FALSE);
        userToAdd.setUserAuthority(AuthorityName.ROLE_USER.toString());
        userMapper.insert(userToAdd);
        // 更新邮箱验证表：用户已注册
        v.setVeriIsRegistered(Boolean.TRUE);
        verificationRegisterEmailMapper.updateByPrimaryKey(v);
        return ResultUtil.success(200, "注册成功");
    }

    @Override
    public Result<User> login(String username, String password) {

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        logger.info(username+ "000000000000000"+ password);
        // Perform the security
        final Authentication authentication;
        try {
             authentication = authenticationManager.authenticate(upToken);
        } catch (BadCredentialsException badCredentialsException) {
            return ResultUtil.error(101, "密码错误");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        final User user = userMapper.selectByUserUserName(username);
        user.setUserIsLogin(true);
        userMapper.updateByPrimaryKey(user);
        return ResultUtil.success(200, token, user);
    }

    @Override
    public Result refresh(String Authorization) {
        logger.info("刷新TOkeNNNNNNNNNNNNNNNNNNNN");
        final String token = Authorization.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if(jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return ResultUtil.success(200, jwtTokenUtil.refreshToken(token));
        }
        return ResultUtil.error(101, "刷新失败");
    }
}
