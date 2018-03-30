package com.fkapi.datebase.domain;

import java.util.Date;

public class risk_credit_yicai_tiqianhua_log {
    private Long id;

    private Long custId;

    private String cityLevel;

    private Integer overduleNumber;

    private Long overdueFloor;

    private Integer repayNumber;

    private Long repayFloor;

    private Long creditLimit;

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

    public String getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(String cityLevel) {
        this.cityLevel = cityLevel == null ? null : cityLevel.trim();
    }

    public Integer getOverduleNumber() {
        return overduleNumber;
    }

    public void setOverduleNumber(Integer overduleNumber) {
        this.overduleNumber = overduleNumber;
    }

    public Long getOverdueFloor() {
        return overdueFloor;
    }

    public void setOverdueFloor(Long overdueFloor) {
        this.overdueFloor = overdueFloor;
    }

    public Integer getRepayNumber() {
        return repayNumber;
    }

    public void setRepayNumber(Integer repayNumber) {
        this.repayNumber = repayNumber;
    }

    public Long getRepayFloor() {
        return repayFloor;
    }

    public void setRepayFloor(Long repayFloor) {
        this.repayFloor = repayFloor;
    }

    public Long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
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