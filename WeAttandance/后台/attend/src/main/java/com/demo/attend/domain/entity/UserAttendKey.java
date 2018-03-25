package com.demo.attend.domain.entity;

public class UserAttendKey {
    private Long fkAttendId;

    private Long fkUserId;

    public UserAttendKey(Long fkAttendId, Long fkUserId) {
        this.fkAttendId = fkAttendId;
        this.fkUserId = fkUserId;
    }

    public UserAttendKey() {
        super();
    }

    public Long getFkAttendId() {
        return fkAttendId;
    }

    public void setFkAttendId(Long fkAttendId) {
        this.fkAttendId = fkAttendId;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }
}