package com.demo.attend.domain.entity;

import java.util.Date;

public class UserAttend extends UserAttendKey {
    private Date attendTime;

    private String attendState;

    public UserAttend(Long fkAttendId, Long fkUserId, Date attendTime, String attendState) {
        super(fkAttendId, fkUserId);
        this.attendTime = attendTime;
        this.attendState = attendState;
    }

    public UserAttend() {
        super();
    }

    public Date getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Date attendTime) {
        this.attendTime = attendTime;
    }

    public String getAttendState() {
        return attendState;
    }

    public void setAttendState(String attendState) {
        this.attendState = attendState == null ? null : attendState.trim();
    }
}