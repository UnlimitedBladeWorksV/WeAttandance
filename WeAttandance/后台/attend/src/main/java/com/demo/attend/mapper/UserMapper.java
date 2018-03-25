package com.demo.attend.mapper;

import com.demo.attend.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    int deleteByPrimaryKey(Long pkUserId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long pkUserId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByEmail(String userEmail);

    User selectByUserUserName(String userUserName);
}