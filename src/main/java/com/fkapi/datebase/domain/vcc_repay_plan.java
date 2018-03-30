package com.fkapi.datebase.domain;

import java.math.BigDecimal;
import java.util.Date;

public class vcc_repay_plan {
    private Long id;

    private String loanNo;

    private Long custId;

    private Integer termNo;

    private BigDecimal repayCapital;

    private BigDecimal repayInterest;

    private BigDecimal serviceFee;

    private BigDecimal returnFee;

    private Date repayDate;

    private Date actualPayTime;

    private String repayStatus;

    private BigDecimal repayAmount;

    private BigDecimal overdueInterest;

    private BigDecimal actRepayAmount;

    private BigDecimal actRepayInterest;

    private BigDecimal actOverdueInterest;

    private BigDecimal actReturnFee;

    private BigDecimal overduMgmtFee;

    private BigDecimal actOverduMgmtFee;

    private String billOutFlag;

    private String delFlag;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo == null ? null : loanNo.trim();
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Integer getTermNo() {
        return termNo;
    }

    public void setTermNo(Integer termNo) {
        this.termNo = termNo;
    }

    public BigDecimal getRepayCapital() {
        return repayCapital;
    }

    public void setRepayCapital(BigDecimal repayCapital) {
        this.repayCapital = repayCapital;
    }

    public BigDecimal getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(BigDecimal repayInterest) {
        this.repayInterest = repayInterest;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getReturnFee() {
        return returnFee;
    }

    public void setReturnFee(BigDecimal returnFee) {
        this.returnFee = returnFee;
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public Date getActualPayTime() {
        return actualPayTime;
    }

    public void setActualPayTime(Date actualPayTime) {
        this.actualPayTime = actualPayTime;
    }

    public String getRepayStatus() {
        return repayStatus;
    }

    public void setRepayStatus(String repayStatus) {
        this.repayStatus = repayStatus == null ? null : repayStatus.trim();
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public BigDecimal getOverdueInterest() {
        return overdueInterest;
    }

    public void setOverdueInterest(BigDecimal overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    public BigDecimal getActRepayAmount() {
        return actRepayAmount;
    }

    public void setActRepayAmount(BigDecimal actRepayAmount) {
        this.actRepayAmount = actRepayAmount;
    }

    public BigDecimal getActRepayInterest() {
        return actRepayInterest;
    }

    public void setActRepayInterest(BigDecimal actRepayInterest) {
        this.actRepayInterest = actRepayInterest;
    }

    public BigDecimal getActOverdueInterest() {
        return actOverdueInterest;
    }

    public void setActOverdueInterest(BigDecimal actOverdueInterest) {
        this.actOverdueInterest = actOverdueInterest;
    }

    public BigDecimal getActReturnFee() {
        return actReturnFee;
    }

    public void setActReturnFee(BigDecimal actReturnFee) {
        this.actReturnFee = actReturnFee;
    }

    public BigDecimal getOverduMgmtFee() {
        return overduMgmtFee;
    }

    public void setOverduMgmtFee(BigDecimal overduMgmtFee) {
        this.overduMgmtFee = overduMgmtFee;
    }

    public BigDecimal getActOverduMgmtFee() {
        return actOverduMgmtFee;
    }

    public void setActOverduMgmtFee(BigDecimal actOverduMgmtFee) {
        this.actOverduMgmtFee = actOverduMgmtFee;
    }

    public String getBillOutFlag() {
        return billOutFlag;
    }

    public void setBillOutFlag(String billOutFlag) {
        this.billOutFlag = billOutFlag == null ? null : billOutFlag.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
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