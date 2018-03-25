package com.demo.attend.domain.dict;

import java.util.Date;

public class UserAttendByStateItemReturnObj extends AttendItemReturnObj{
    private Long userId;
    private String userTrueName;
    private Date attendTime;

    public UserAttendByStateItemReturnObj(Long attendId, String attendTitle, Date attendStartTime, Date attendOverTime, String attendLocation, Long userId, String userTrueName, Date attendTime) {
        super(attendId, attendTitle, attendStartTime, attendOverTime, attendLocation);
        this.userId = userId;
        this.userTrueName = userTrueName;
        this.attendTime = attendTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public Date getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Date attendTime) {
        this.attendTime = attendTime;
    }
}
