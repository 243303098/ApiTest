package com.fkapi.datebase.domain;

import java.util.Date;

public class risk_audit_log {
    private Long id;

    private String projectNo;

    private Long custId;

    private String auditResult;

    private Long executeMillisecond;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult == null ? null : auditResult.trim();
    }

    public Long getExecuteMillisecond() {
        return executeMillisecond;
    }

    public void setExecuteMillisecond(Long executeMillisecond) {
        this.executeMillisecond = executeMillisecond;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}