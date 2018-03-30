package com.fkapi.datebase.domain;

import java.util.Date;

public class p2p_juxinli_mobile_auth {
    private Long id;

    private Long custId;

    private String status;

    private String lastStatus;

    private Date createTime;

    private Date updateTime;

    private Date expireTime;

    private Date lastUpdateTime;

    private String type;

    private String failAffirmFlag;

    private String authServiceProvider;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus == null ? null : lastStatus.trim();
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

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFailAffirmFlag() {
        return failAffirmFlag;
    }

    public void setFailAffirmFlag(String failAffirmFlag) {
        this.failAffirmFlag = failAffirmFlag == null ? null : failAffirmFlag.trim();
    }

    public String getAuthServiceProvider() {
        return authServiceProvider;
    }

    public void setAuthServiceProvider(String authServiceProvider) {
        this.authServiceProvider = authServiceProvider == null ? null : authServiceProvider.trim();
    }
}