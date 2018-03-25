package com.demo.attend.domain.entity;

import java.util.Date;

public class AttendOrg {
    private Long pkOrgAttendId;

    private String orgAttendName;

    private Long orgAttendCreateById;

    private Date orgAttendCreateTime;

    private Integer orgAttendPeopleNum;

    private String orgAttendQrCode;

    private Integer orgAttendQrCodeEffectiveDays;

    private Date orgAttendQrCodeOverdue;

    private String orgAttendDescription;


    public AttendOrg() {
        super();
    }

    public AttendOrg(Long pkOrgAttendId, String orgAttendName, Long orgAttendCreateById, Date orgAttendCreateTime, Integer orgAttendPeopleNum, String orgAttendQrCode, Integer orgAttendQrCodeEffectiveDays, Date orgAttendQrCodeOverdue, String orgAttendDescription) {
        this.pkOrgAttendId = pkOrgAttendId;
        this.orgAttendName = orgAttendName;
        this.orgAttendCreateById = orgAttendCreateById;
        this.orgAttendCreateTime = orgAttendCreateTime;
        this.orgAttendPeopleNum = orgAttendPeopleNum;
        this.orgAttendQrCode = orgAttendQrCode;
        this.orgAttendQrCodeEffectiveDays = orgAttendQrCodeEffectiveDays;
        this.orgAttendQrCodeOverdue = orgAttendQrCodeOverdue;
        this.orgAttendDescription = orgAttendDescription;
    }

    public Long getPkOrgAttendId() {
        return pkOrgAttendId;
    }

    public void setPkOrgAttendId(Long pkOrgAttendId) {
        this.pkOrgAttendId = pkOrgAttendId;
    }

    public String getOrgAttendName() {
        return orgAttendName;
    }

    public void setOrgAttendName(String orgAttendName) {
        this.orgAttendName = orgAttendName;
    }

    public Long getOrgAttendCreateById() {
        return orgAttendCreateById;
    }

    public void setOrgAttendCreateById(Long orgAttendCreateById) {
        this.orgAttendCreateById = orgAttendCreateById;
    }

    public Date getOrgAttendCreateTime() {
        return orgAttendCreateTime;
    }

    public void setOrgAttendCreateTime(Date orgAttendCreateTime) {
        this.orgAttendCreateTime = orgAttendCreateTime;
    }

    public Integer getOrgAttendPeopleNum() {
        return orgAttendPeopleNum;
    }

    public void setOrgAttendPeopleNum(Integer orgAttendPeopleNum) {
        this.orgAttendPeopleNum = orgAttendPeopleNum;
    }

    public String getOrgAttendQrCode() {
        return orgAttendQrCode;
    }

    public void setOrgAttendQrCode(String orgAttendQrCode) {
        this.orgAttendQrCode = orgAttendQrCode;
    }

    public Integer getOrgAttendQrCodeEffectiveDays() {
        return orgAttendQrCodeEffectiveDays;
    }

    public void setOrgAttendQrCodeEffectiveDays(Integer orgAttendQrCodeEffectiveDays) {
        this.orgAttendQrCodeEffectiveDays = orgAttendQrCodeEffectiveDays;
    }

    public Date getOrgAttendQrCodeOverdue() {
        return orgAttendQrCodeOverdue;
    }

    public void setOrgAttendQrCodeOverdue(Date orgAttendQrCodeOverdue) {
        this.orgAttendQrCodeOverdue = orgAttendQrCodeOverdue;
    }

    public String getOrgAttendDescription() {
        return orgAttendDescription;
    }

    public void setOrgAttendDescription(String orgAttendDescription) {
        this.orgAttendDescription = orgAttendDescription;
    }
}