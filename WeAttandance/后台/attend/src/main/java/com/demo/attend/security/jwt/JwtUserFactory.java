package com.demo.attend.security.jwt;

import java.util.List;
import java.util.stream.Collectors;

import com.demo.attend.domain.entity.User;
import com.demo.attend.util.StringListConvertUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static java.util.Arrays.asList;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getPkUserId(),
                user.getUserUserName(),
                user.getUserPasswd(),
                user.getUserEmail(),
                mapToGrantedAuthorities(asList(user.getUserAuthority())),
                user.getUserLastResetPasswdTime()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

