package com.fkapi.datebase.domain;

import java.util.Date;

public class p2p_loan_claim_relative_app {
    private Long id;

    private String projectNo;

    private String mobileSign;

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

    public String getMobileSign() {
        return mobileSign;
    }

    public void setMobileSign(String mobileSign) {
        this.mobileSign = mobileSign == null ? null : mobileSign.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}