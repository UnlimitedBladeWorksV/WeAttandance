package com.demo.attend.mapper;

import com.demo.attend.domain.entity.Attend;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AttendMapper {
    int deleteByPrimaryKey(Long pkAttendId);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Long pkAttendId);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    List<Attend> selectAttendsByFkOrgAttendId(Long fkOrgAttendId);

    void set_foreign_key_off();
    void set_foreign_key_on();
}