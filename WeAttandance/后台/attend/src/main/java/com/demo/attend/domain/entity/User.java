package com.demo.attend.domain.entity;

import java.util.Date;

public class User {
    private Long pkUserId;

    private String userEmail;

    private String userPasswd;

    private String userUserName;

    private String userPhone;

    private Boolean userSex;

    private Boolean userIsLogin;

    private Date userRegisterTime;

    private String userDescription;

    private String userTrueName;

    private String userAuthority;

    private Date userLastResetPasswdTime;

    public User(Long pkUserId, String userEmail, String userPasswd, String userUserName, String userPhone, Boolean userSex, Boolean userIsLogin, Date userRegisterTime, String userDescription, String userTrueName, String userAuthority, Date userLastResetPasswdTime) {
        this.pkUserId = pkUserId;
        this.userEmail = userEmail;
        this.userPasswd = userPasswd;
        this.userUserName = userUserName;
        this.userPhone = userPhone;
        this.userSex = userSex;
        this.userIsLogin = userIsLogin;
        this.userRegisterTime = userRegisterTime;
        this.userDescription = userDescription;
        this.userTrueName = userTrueName;
        this.userAuthority = userAuthority;
        this.userLastResetPasswdTime = userLastResetPasswdTime;
    }

    public User() {
        super();
    }

    public Long getPkUserId() {
        return pkUserId;
    }

    public void setPkUserId(Long pkUserId) {
        this.pkUserId = pkUserId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd == null ? null : userPasswd.trim();
    }

    public String getUserUserName() {
        return userUserName;
    }

    public void setUserUserName(String userUserName) {
        this.userUserName = userUserName == null ? null : userUserName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(Boolean userSex) {
        this.userSex = userSex;
    }

    public Boolean getUserIsLogin() {
        return userIsLogin;
    }

    public void setUserIsLogin(Boolean userIsLogin) {
        this.userIsLogin = userIsLogin;
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Date userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription == null ? null : userDescription.trim();
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName == null ? null : userTrueName.trim();
    }

    public String getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(String userAuthority) {
        this.userAuthority = userAuthority == null ? null : userAuthority.trim();
    }

    public Date getUserLastResetPasswdTime() {
        return userLastResetPasswdTime;
    }

    public void setUserLastResetPasswdTime(Date userLastResetPasswdTime) {
        this.userLastResetPasswdTime = userLastResetPasswdTime;
    }
}