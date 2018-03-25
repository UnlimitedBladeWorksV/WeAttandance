package com.demo.attend.domain.dict;

import java.util.Date;

public class AttendItemReturnObj {
    private Long attendId;
    private String attendTitle;
    private Date attendStartTime;
    private Date attendOverTime;
    private String attendLocation;

    public AttendItemReturnObj(Long attendId, String attendTitle, Date attendStartTime, Date attendOverTime, String attendLocation) {
        this.attendId = attendId;
        this.attendTitle = attendTitle;
        this.attendStartTime = attendStartTime;
        this.attendOverTime = attendOverTime;
        this.attendLocation = attendLocation;
    }

    public String getAttendLocation() {
        return attendLocation;
    }

    public void setAttendLocation(String attendLocation) {
        this.attendLocation = attendLocation;
    }

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public String getAttendTitle() {
        return attendTitle;
    }

    public void setAttendTitle(String attendTitle) {
        this.attendTitle = attendTitle;
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
