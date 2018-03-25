package com.demo.attend.mapper;

import com.demo.attend.domain.entity.User;
import com.demo.attend.domain.entity.UserAttend;
import com.demo.attend.domain.entity.UserAttendKey;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserAttendMapper {
    int deleteByPrimaryKey(UserAttendKey key);

    int insert(UserAttend record);

    int insertSelective(UserAttend record);

    UserAttend selectByPrimaryKey(UserAttendKey key);

    List<UserAttend> selectByFkUserId(Long fkUserId);

    List<UserAttend> selectByFkAttendId(Long fkAttendId);

    int updateByPrimaryKeySelective(UserAttend record);

    int updateByPrimaryKey(UserAttend record);
}