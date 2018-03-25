package com.demo.attend.domain.entity;

import java.util.Date;

public class UserOrgAttend extends UserOrgAttendKey {
    private Date userAttendTime;

    private Integer attendTrueNum;

    private Integer attendLateNum;

    private Integer attendFalseNum;

    public UserOrgAttend(Long fkUserId, Long fkOrgAttendId, Date userAttendTime, Integer attendTrueNum, Integer attendLateNum, Integer attendFalseNum) {
        super(fkUserId, fkOrgAttendId);
        this.userAttendTime = userAttendTime;
        this.attendTrueNum = attendTrueNum;
        this.attendLateNum = attendLateNum;
        this.attendFalseNum = attendFalseNum;
    }

    public UserOrgAttend() {
        super();
    }

    public Date getUserAttendTime() {
        return userAttendTime;
    }

    public void setUserAttendTime(Date userAttendTime) {
        this.userAttendTime = userAttendTime;
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
}