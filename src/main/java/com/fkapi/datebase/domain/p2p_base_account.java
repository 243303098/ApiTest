package com.fkapi.datebase.domain;

import java.math.BigDecimal;
import java.util.Date;

public class p2p_base_account {
    private String acctNo;

    private String acctName;

    private BigDecimal acctBal;

    private String openOrgCode;

    private String openOrgName;

    private Date openTime;

    private String remark;

    private String acctKind;

    private Date createTime;

    private Long version;

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName == null ? null : acctName.trim();
    }

    public BigDecimal getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(BigDecimal acctBal) {
        this.acctBal = acctBal;
    }

    public String getOpenOrgCode() {
        return openOrgCode;
    }

    public void setOpenOrgCode(String openOrgCode) {
        this.openOrgCode = openOrgCode == null ? null : openOrgCode.trim();
    }

    public String getOpenOrgName() {
        return openOrgName;
    }

    public void setOpenOrgName(String openOrgName) {
        this.openOrgName = openOrgName == null ? null : openOrgName.trim();
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAcctKind() {
        return acctKind;
    }

    public void setAcctKind(String acctKind) {
        this.acctKind = acctKind == null ? null : acctKind.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}