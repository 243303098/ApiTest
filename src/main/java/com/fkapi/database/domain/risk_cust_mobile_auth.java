package com.fkapi.database.domain;

import java.util.Date;

public class risk_cust_mobile_auth {
    private Long custId;

    private String status;

    private Boolean isAuthing;

    private Date lastAuthTime;

    private Date expireTime;

    private Date createTime;

    private Date updateTime;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Boolean getIsAuthing() {
        return isAuthing;
    }

    public void setIsAuthing(Boolean isAuthing) {
        this.isAuthing = isAuthing;
    }

    public Date getLastAuthTime() {
        return lastAuthTime;
    }

    public void setLastAuthTime(Date lastAuthTime) {
        this.lastAuthTime = lastAuthTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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