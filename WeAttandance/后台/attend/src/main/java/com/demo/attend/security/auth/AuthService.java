package com.demo.attend.security.auth;

import com.demo.attend.domain.entity.Result;
import com.demo.attend.domain.entity.User;

public interface AuthService {
    Result register(User userToAdd, Integer veriCode);
    Result login(String username, String password);
    Result refresh(String oldToken);
}
