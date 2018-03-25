package com.demo.attend.mapper;

import com.demo.attend.domain.entity.VerificationRegisterEmail;
import org.springframework.stereotype.Component;

@Component
public interface VerificationRegisterEmailMapper {
    int deleteByPrimaryKey(String veriEmail);

    int insert(VerificationRegisterEmail record);

    int insertSelective(VerificationRegisterEmail record);

    VerificationRegisterEmail selectByPrimaryKey(String veriEmail);

    int updateByPrimaryKeySelective(VerificationRegisterEmail record);

    int updateByPrimaryKey(VerificationRegisterEmail record);
}