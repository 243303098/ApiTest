package com.fkapi.datebase.domain;

import java.util.Date;

public class jxl_primary_info {
    private Long id;

    private Long custId;

    private Long authLogId;

    private String authType;

    private String status;

    private String updateTime;

    private String reqArgs;

    private String errorCode;

    private String errorMsg;

    private Date createTime;

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

    public Long getAuthLogId() {
        return authLogId;
    }

    public void setAuthLogId(Long authLogId) {
        this.authLogId = authLogId;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType == null ? null : authType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getReqArgs() {
        return reqArgs;
    }

    public void setReqArgs(String reqArgs) {
        this.reqArgs = reqArgs == null ? null : reqArgs.trim();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}