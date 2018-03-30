package com.fkapi.datebase.domain;

import java.util.Date;

public class p2p_customer {
    private Long id;

    private String loginName;

    private String loginPassword;

    private String recommendCode;

    private String custStatus;

    private Integer pwdErrorCounter;

    private Date createTime;

    private Date lastLoginTime;

    private Date lastUpdateTime;

    private String payPwd;

    private String regSource;

    private String regDevice;

    private String regOs;

    private Long referee;

    private String nickName;

    private String loginIp;

    private Date lockTime;

    private Integer loginCount;

    private String custType;

    private String mobile;

    private String email;

    private String custKind;

    private String activitySrc;

    private String belongPlatform;

    private String randomNo;

    private Date resetTime;

    private String autoPwd;

    private String autoInvestAuth;

    private String direcTrfAuth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode == null ? null : recommendCode.trim();
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus == null ? null : custStatus.trim();
    }

    public Integer getPwdErrorCounter() {
        return pwdErrorCounter;
    }

    public void setPwdErrorCounter(Integer pwdErrorCounter) {
        this.pwdErrorCounter = pwdErrorCounter;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd == null ? null : payPwd.trim();
    }

    public String getRegSource() {
        return regSource;
    }

    public void setRegSource(String regSource) {
        this.regSource = regSource == null ? null : regSource.trim();
    }

    public String getRegDevice() {
        return regDevice;
    }

    public void setRegDevice(String regDevice) {
        this.regDevice = regDevice == null ? null : regDevice.trim();
    }

    public String getRegOs() {
        return regOs;
    }

    public void setRegOs(String regOs) {
        this.regOs = regOs == null ? null : regOs.trim();
    }

    public Long getReferee() {
        return referee;
    }

    public void setReferee(Long referee) {
        this.referee = referee;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType == null ? null : custType.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCustKind() {
        return custKind;
    }

    public void setCustKind(String custKind) {
        this.custKind = custKind == null ? null : custKind.trim();
    }

    public String getActivitySrc() {
        return activitySrc;
    }

    public void setActivitySrc(String activitySrc) {
        this.activitySrc = activitySrc == null ? null : activitySrc.trim();
    }

    public String getBelongPlatform() {
        return belongPlatform;
    }

    public void setBelongPlatform(String belongPlatform) {
        this.belongPlatform = belongPlatform == null ? null : belongPlatform.trim();
    }

    public String getRandomNo() {
        return randomNo;
    }

    public void setRandomNo(String randomNo) {
        this.randomNo = randomNo == null ? null : randomNo.trim();
    }

    public Date getResetTime() {
        return resetTime;
    }

    public void setResetTime(Date resetTime) {
        this.resetTime = resetTime;
    }

    public String getAutoPwd() {
        return autoPwd;
    }

    public void setAutoPwd(String autoPwd) {
        this.autoPwd = autoPwd == null ? null : autoPwd.trim();
    }

    public String getAutoInvestAuth() {
        return autoInvestAuth;
    }

    public void setAutoInvestAuth(String autoInvestAuth) {
        this.autoInvestAuth = autoInvestAuth == null ? null : autoInvestAuth.trim();
    }

    public String getDirecTrfAuth() {
        return direcTrfAuth;
    }

    public void setDirecTrfAuth(String direcTrfAuth) {
        this.direcTrfAuth = direcTrfAuth == null ? null : direcTrfAuth.trim();
    }

}