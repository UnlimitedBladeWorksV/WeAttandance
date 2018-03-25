package com.demo.attend.domain.dict;

import java.util.Date;

public class OrgAttendAttendItemReturnObj extends AttendItemReturnObj{
    private String orgAttendName;
    private Integer shouldNum;
    private Integer TrueNum;
    private Integer LateNum;
    private Integer FalseNum;

    public OrgAttendAttendItemReturnObj(Long attendId, String attendTitle, Date attendStartTime, Date attendOverTime, String attendLocation, String orgAttendName, Integer shouldNum, Integer trueNum, Integer lateNum, Integer falseNum) {
        super(attendId, attendTitle, attendStartTime, attendOverTime, attendLocation);
        this.orgAttendName = orgAttendName;
        this.shouldNum = shouldNum;
        TrueNum = trueNum;
        LateNum = lateNum;
        FalseNum = falseNum;
    }

    public String getOrgAttendName() {
        return orgAttendName;
    }

    public void setOrgAttendName(String orgAttendName) {
        this.orgAttendName = orgAttendName;
    }

    public Integer getShouldNum() {
        return shouldNum;
    }

    public void setShouldNum(Integer shouldNum) {
        this.shouldNum = shouldNum;
    }

    public Integer getTrueNum() {
        return TrueNum;
    }

    public void setTrueNum(Integer trueNum) {
        TrueNum = trueNum;
    }

    public Integer getLateNum() {
        return LateNum;
    }

    public void setLateNum(Integer lateNum) {
        LateNum = lateNum;
    }

    public Integer getFalseNum() {
        return FalseNum;
    }

    public void setFalseNum(Integer falseNum) {
        FalseNum = falseNum;
    }
}
