package com.demo.attend.mapper;

import com.demo.attend.domain.entity.AttendOrg;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AttendOrgMapper {
    int deleteByPrimaryKey(Long pkOrgAttendId);

    int insert(AttendOrg record);

    int insertSelective(AttendOrg record);

    AttendOrg selectByPrimaryKey(Long pkOrgAttendId);

    int updateByPrimaryKeySelective(AttendOrg record);

    int updateByPrimaryKey(AttendOrg record);

    List<AttendOrg> selectByOrgAttendCreateById(Long orgAttendCreateById);

    void set_foreign_key_off();
    void set_foreign_key_on();
}