package com.demo.attend.domain.entity;

public class UserOrgAttendKey {
    private Long fkUserId;

    private Long fkOrgAttendId;

    public UserOrgAttendKey(Long fkUserId, Long fkOrgAttendId) {
        this.fkUserId = fkUserId;
        this.fkOrgAttendId = fkOrgAttendId;
    }

    public UserOrgAttendKey() {
        super();
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Long getFkOrgAttendId() {
        return fkOrgAttendId;
    }

    public void setFkOrgAttendId(Long fkOrgAttendId) {
        this.fkOrgAttendId = fkOrgAttendId;
    }
}