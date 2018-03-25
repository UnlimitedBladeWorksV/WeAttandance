package com.demo.attend.domain.dict;

import java.util.Date;

public class UserAttendItemReturnObj extends AttendItemReturnObj {
    String orgAttendName;
    private Date attendTime;
    private String attendState;

    public UserAttendItemReturnObj(Long attendId, String attendTitle, Date attendStartTime, Date attendOverTime, String attendLocation, String orgAttendName, Date attendTime, String attendState) {
        super(attendId, attendTitle, attendStartTime, attendOverTime, attendLocation);
        this.orgAttendName = orgAttendName;
        this.attendTime = attendTime;
        this.attendState = attendState;
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
}
