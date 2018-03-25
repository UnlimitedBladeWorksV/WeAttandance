package com.demo.attend.mapper;

import com.demo.attend.domain.entity.AttendOrg;
import com.demo.attend.domain.entity.User;
import com.demo.attend.domain.entity.UserOrgAttend;
import com.demo.attend.domain.entity.UserOrgAttendKey;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public interface UserOrgAttendMapper {
    int deleteByPrimaryKey(UserOrgAttendKey key);

    int deleteByFkOrgAttendId(Long fkOrgAttendId);

    int insert(UserOrgAttend record);

    int insertSelective(UserOrgAttend record);

    UserOrgAttend selectByPrimaryKey(UserOrgAttendKey key);

    int updateByPrimaryKeySelective(UserOrgAttend record);

    int updateByPrimaryKey(UserOrgAttend record);

    List<User> selectUsersByFkOrgAttendId(Long fkOrgAttendId);

    List<AttendOrg> selectAttendOrgsByFkUserId(Long fkUserId);

    List<Long> selectUserIdsByFkOrgAttendId(Long fkOrgAttendId);

    List<Long> selectOrgAttendIdsByFkUserId(Long fkUserId);
}