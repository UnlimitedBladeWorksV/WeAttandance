package com.demo.attend.domain.dict;

public class AttendQrCodeReturnObj {

    private String qrCode;
    private Integer attendQrCodeRefreshSecond;

    public AttendQrCodeReturnObj(String qrCode, Integer attendQrCodeRefreshSecond) {
        this.qrCode = qrCode;
        this.attendQrCodeRefreshSecond = attendQrCodeRefreshSecond;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getAttendQrCodeRefreshSecond() {
        return attendQrCodeRefreshSecond;
    }

    public void setAttendQrCodeRefreshSecond(Integer attendQrCodeRefreshSecond) {
        this.attendQrCodeRefreshSecond = attendQrCodeRefreshSecond;
    }
}
