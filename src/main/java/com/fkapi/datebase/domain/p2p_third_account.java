package com.fkapi.datebase.domain;

import java.util.Date;

public class p2p_third_account {
    private String acctNo;

    private String refNo;

    private String acctKind;

    private String thirdAcctNo;

    private String thirdUserId;

    private String activeFlag;

    private String openFlag;

    private String userRole;

    private Date createTime;

    private Date updateTime;

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo == null ? null : refNo.trim();
    }

    public String getAcctKind() {
        return acctKind;
    }

    public void setAcctKind(String acctKind) {
        this.acctKind = acctKind == null ? null : acctKind.trim();
    }

    public String getThirdAcctNo() {
        return thirdAcctNo;
    }

    public void setThirdAcctNo(String thirdAcctNo) {
        this.thirdAcctNo = thirdAcctNo == null ? null : thirdAcctNo.trim();
    }

    public String getThirdUserId() {
        return thirdUserId;
    }

    public void setThirdUserId(String thirdUserId) {
        this.thirdUserId = thirdUserId == null ? null : thirdUserId.trim();
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag == null ? null : activeFlag.trim();
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag == null ? null : openFlag.trim();
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole == null ? null : userRole.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}