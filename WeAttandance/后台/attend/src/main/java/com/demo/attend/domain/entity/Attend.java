package com.demo.attend.domain.entity;

import java.util.Date;

public class Attend {
    private Long pkAttendId;

    private Long fkOrgAttendId;

    private String attendTitle;

    private Long attendCreateById;

    private Date attendCreateTime;

    private String attendLocation;

    private Date attendStartTime;

    private Date attendOverTime;

    private Integer attendPeopleNum;

    private Integer attendTrueNum;

    private Integer attendLateNum;

    private Integer attendFalseNum;

    private String attendQrCode;

    private Date attendQrCodeOverDue;

    private Integer attendQrCodeRefreshSecond;

    public Attend() {
        super();
    }

    public Attend(Long pkAttendId, Long fkOrgAttendId, String attendTitle, Long attendCreateById, Date attendCreateTime, String attendLocation, Date attendStartTime, Date attendOverTime, Integer attendPeopleNum, Integer attendTrueNum, Integer attendLateNum, Integer attendFalseNum, String attendQrCode, Date attendQrCodeOverDue, Integer attendQrCodeRefreshSecond) {
        this.pkAttendId = pkAttendId;
        this.fkOrgAttendId = fkOrgAttendId;
        this.attendTitle = attendTitle;
        this.attendCreateById = attendCreateById;
        this.attendCreateTime = attendCreateTime;
        this.attendLocation = attendLocation;
        this.attendStartTime = attendStartTime;
        this.attendOverTime = attendOverTime;
        this.attendPeopleNum = attendPeopleNum;
        this.attendTrueNum = attendTrueNum;
        this.attendLateNum = attendLateNum;
        this.attendFalseNum = attendFalseNum;
        this.attendQrCode = attendQrCode;
        this.attendQrCodeOverDue = attendQrCodeOverDue;
        this.attendQrCodeRefreshSecond = attendQrCodeRefreshSecond;
    }

    public Long getPkAttendId() {
        return pkAttendId;
    }

    public void setPkAttendId(Long pkAttendId) {
        this.pkAttendId = pkAttendId;
    }

    public Long getFkOrgAttendId() {
        return fkOrgAttendId;
    }

    public void setFkOrgAttendId(Long fkOrgAttendId) {
        this.fkOrgAttendId = fkOrgAttendId;
    }

    public String getAttendTitle() {
        return attendTitle;
    }

    public void setAttendTitle(String attendTitle) {
        this.attendTitle = attendTitle;
    }

    public Long getAttendCreateById() {
        return attendCreateById;
    }

    public void setAttendCreateById(Long attendCreateById) {
        this.attendCreateById = attendCreateById;
    }

    public Date getAttendCreateTime() {
        return attendCreateTime;
    }

    public void setAttendCreateTime(Date attendCreateTime) {
        this.attendCreateTime = attendCreateTime;
    }

    public String getAttendLocation() {
        return attendLocation;
    }

    public void setAttendLocation(String attendLocation) {
        this.attendLocation = attendLocation;
    }

    public Date getAttendStartTime() {
        return attendStartTime;
    }

    public void setAttendStartTime(Date attendStartTime) {
        this.attendStartTime = attendStartTime;
    }

    public Date getAttendOverTime() {
        return attendOverTime;
    }

    public void setAttendOverTime(Date attendOverTime) {
        this.attendOverTime = attendOverTime;
    }

    public Integer getAttendPeopleNum() {
        return attendPeopleNum;
    }

    public void setAttendPeopleNum(Integer attendPeopleNum) {
        this.attendPeopleNum = attendPeopleNum;
    }

    public Integer getAttendTrueNum() {
        return attendTrueNum;
    }

    public void setAttendTrueNum(Integer attendTrueNum) {
        this.attendTrueNum = attendTrueNum;
    }

    public Integer getAttendLateNum() {
        return attendLateNum;
    }

    public void setAttendLateNum(Integer attendLateNum) {
        this.attendLateNum = attendLateNum;
    }

    public Integer getAttendFalseNum() {
        return attendFalseNum;
    }

    public void setAttendFalseNum(Integer attendFalseNum) {
        this.attendFalseNum = attendFalseNum;
    }

    public String getAttendQrCode() {
        return attendQrCode;
    }

    public void setAttendQrCode(String attendQrCode) {
        this.attendQrCode = attendQrCode;
    }

    public Integer getAttendQrCodeRefreshSecond() {
        return attendQrCodeRefreshSecond;
    }

    public void setAttendQrCodeRefreshSecond(Integer attendQrCodeRefreshSecond) {
        this.attendQrCodeRefreshSecond = attendQrCodeRefreshSecond;
    }

    public Date getAttendQrCodeOverDue() {
        return attendQrCodeOverDue;
    }

    public void setAttendQrCodeOverDue(Date attendQrCodeOverDue) {
        this.attendQrCodeOverDue = attendQrCodeOverDue;
    }
}