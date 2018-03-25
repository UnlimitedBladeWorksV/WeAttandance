package com.demo.attend.domain.dict;

public class UserItemReturnObj {

    private Long userId;
    private String userTrueName;

    public UserItemReturnObj(Long userId, String userTrueName) {
        this.userId = userId;
        this.userTrueName = userTrueName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getuserTrueName() {
        return userTrueName;
    }

    public void setuserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }
}
