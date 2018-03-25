package com.demo.attend.domain.dict;



public class OrgAttendItemReturnObj {
    private Long orgAttendId;
    private String orgAttendName;
    private String orgAttendDescription;
    private String orgAttendQrCode;

    public OrgAttendItemReturnObj() {
    }

    public OrgAttendItemReturnObj(Long orgAttendId, String orgAttendName, String orgAttendDescription, String orgAttendQrCode) {
        this.orgAttendId = orgAttendId;
        this.orgAttendName = orgAttendName;
        this.orgAttendDescription = orgAttendDescription;
        this.orgAttendQrCode = orgAttendQrCode;
    }

    public Long getOrgAttendId() {
        return orgAttendId;
    }

    public void setOrgAttendId(Long orgAttendId) {
        this.orgAttendId = orgAttendId;
    }

    public String getOrgAttendName() {
        return orgAttendName;
    }

    public void setOrgAttendName(String orgAttendName) {
        this.orgAttendName = orgAttendName;
    }

    public String getOrgAttendDescription() {
        return orgAttendDescription;
    }

    public void setOrgAttendDescription(String orgAttendDescription) {
        this.orgAttendDescription = orgAttendDescription;
    }

    public String getOrgAttendQrCode() {
        return orgAttendQrCode;
    }

    public void setOrgAttendQrCode(String orgAttendQrCode) {
        this.orgAttendQrCode = orgAttendQrCode;
    }
}
