package com.demo.attend.domain.entity;

import java.util.Date;

public class VerificationRegisterEmail {
    private String veriEmail;

    private Integer veriCode;

    private Date veriOverdue;

    private Boolean veriIsRegistered;

    public VerificationRegisterEmail(String veriEmail, Integer veriCode, Date veriOverdue, Boolean veriIsRegistered) {
        this.veriEmail = veriEmail;
        this.veriCode = veriCode;
        this.veriOverdue = veriOverdue;
        this.veriIsRegistered = veriIsRegistered;
    }

    public VerificationRegisterEmail() {
        super();
    }

    public String getVeriEmail() {
        return veriEmail;
    }

    public void setVeriEmail(String veriEmail) {
        this.veriEmail = veriEmail == null ? null : veriEmail.trim();
    }

    public Integer getVeriCode() {
        return veriCode;
    }

    public void setVeriCode(Integer veriCode) {
        this.veriCode = veriCode;
    }

    public Date getVeriOverdue() {
        return veriOverdue;
    }

    public void setVeriOverdue(Date veriOverdue) {
        this.veriOverdue = veriOverdue;
    }

    public Boolean getVeriIsRegistered() {
        return veriIsRegistered;
    }

    public void setVeriIsRegistered(Boolean veriIsRegistered) {
        this.veriIsRegistered = veriIsRegistered;
    }
}