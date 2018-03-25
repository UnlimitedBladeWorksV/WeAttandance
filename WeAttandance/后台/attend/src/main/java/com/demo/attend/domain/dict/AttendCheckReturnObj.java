package com.demo.attend.domain.dict;


import java.util.Date;

public class AttendCheckReturnObj {
    private Long attendId;
    private Long orgAttendId;
    private String attendTitle;
    private Date attendStartTime;
    private Date attendOverTime;
    private String orgAttendName;
    private Date attendTime;
    private String attendState;

    public AttendCheckReturnObj(Long attendId, Long orgAttendId, String attendTitle, Date attendStartTime, Date attendOverTime, String orgAttendName, Date attendTime, String attendState) {
        this.attendId = attendId;
        this.orgAttendId = orgAttendId;
        this.attendTitle = attendTitle;
        this.attendStartTime = attendStartTime;
        this.attendOverTime = attendOverTime;
        this.orgAttendName = orgAttendName;
        this.attendTime = attendTime;
        this.attendState = attendState;
    }

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public Long getOrgAttendId() {
        return orgAttendId;
    }

    public void setOrgAttendId(Long orgAttendId) {
        this.orgAttendId = orgAttendId;
    }

    public String getAttendTitle() {
        return attendTitle;
    }

    public void setAttendTitle(String attendTitle) {
        this.attendTitle = attendTitle;
    }

    public String getOrgAttendName() {
        return orgAttendName;
    }

    public void setOrgAttendName(String orgAttendName) {
        this.orgAttendName = orgAttendName;
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
        this.attendState = attendState;
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
}
