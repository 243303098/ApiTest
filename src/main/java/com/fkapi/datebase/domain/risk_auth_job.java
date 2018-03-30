package com.fkapi.datebase.domain;

import java.util.Date;

public class risk_auth_job {
    private Long id;

    private Long custId;

    private String authStatus;

    private Date gmtExpiry;

    private Date gmtSucceed;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus == null ? null : authStatus.trim();
    }

    public Date getGmtExpiry() {
        return gmtExpiry;
    }

    public void setGmtExpiry(Date gmtExpiry) {
        this.gmtExpiry = gmtExpiry;
    }

    public Date getGmtSucceed() {
        return gmtSucceed;
    }

    public void setGmtSucceed(Date gmtSucceed) {
        this.gmtSucceed = gmtSucceed;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}