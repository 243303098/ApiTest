package com.fkapi.datebase.domain;

import java.math.BigDecimal;
import java.util.Date;

public class p2p_repay_plan {
    private Long id;

    private String projectNo;

    private Long repaymentId;

    private Integer termNo;

    private BigDecimal repayCapital;

    private BigDecimal repayInterest;

    private Date repayDate;

    private Date actualPayTime;

    private String repayStatus;

    private String delFlag;

    private String isAdvance;

    private String advancePeople;

    private BigDecimal repayAmount;

    private BigDecimal overdueFee;

    private BigDecimal platformFee;

    private BigDecimal actRepayAmount;

    private BigDecimal actRepayInterest;

    private BigDecimal actOverdueFee;

    private BigDecimal actPlatformFee;

    private String corpNo;

    private String borrowerType;

    private Date advanceTime;

    private BigDecimal overdueMgmtFee;

    private BigDecimal overdueInterest;

    private BigDecimal actOverdueMgmtFee;

    private BigDecimal actOverdueInterest;

    private String advanceType;

    private BigDecimal actCapital;

    private String offlinePayoffFlag;

    private Date calcOverdueTime;

    private BigDecimal poundage;

    private BigDecimal actPoundage;

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

    public Long getRepaymentId() {
        return repaymentId;
    }

    public void setRepaymentId(Long repaymentId) {
        this.repaymentId = repaymentId;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getIsAdvance() {
        return isAdvance;
    }

    public void setIsAdvance(String isAdvance) {
        this.isAdvance = isAdvance == null ? null : isAdvance.trim();
    }

    public String getAdvancePeople() {
        return advancePeople;
    }

    public void setAdvancePeople(String advancePeople) {
        this.advancePeople = advancePeople == null ? null : advancePeople.trim();
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public BigDecimal getOverdueFee() {
        return overdueFee;
    }

    public void setOverdueFee(BigDecimal overdueFee) {
        this.overdueFee = overdueFee;
    }

    public BigDecimal getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(BigDecimal platformFee) {
        this.platformFee = platformFee;
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

    public BigDecimal getActOverdueFee() {
        return actOverdueFee;
    }

    public void setActOverdueFee(BigDecimal actOverdueFee) {
        this.actOverdueFee = actOverdueFee;
    }

    public BigDecimal getActPlatformFee() {
        return actPlatformFee;
    }

    public void setActPlatformFee(BigDecimal actPlatformFee) {
        this.actPlatformFee = actPlatformFee;
    }

    public String getCorpNo() {
        return corpNo;
    }

    public void setCorpNo(String corpNo) {
        this.corpNo = corpNo == null ? null : corpNo.trim();
    }

    public String getBorrowerType() {
        return borrowerType;
    }

    public void setBorrowerType(String borrowerType) {
        this.borrowerType = borrowerType == null ? null : borrowerType.trim();
    }

    public Date getAdvanceTime() {
        return advanceTime;
    }

    public void setAdvanceTime(Date advanceTime) {
        this.advanceTime = advanceTime;
    }

    public BigDecimal getOverdueMgmtFee() {
        return overdueMgmtFee;
    }

    public void setOverdueMgmtFee(BigDecimal overdueMgmtFee) {
        this.overdueMgmtFee = overdueMgmtFee;
    }

    public BigDecimal getOverdueInterest() {
        return overdueInterest;
    }

    public void setOverdueInterest(BigDecimal overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    public BigDecimal getActOverdueMgmtFee() {
        return actOverdueMgmtFee;
    }

    public void setActOverdueMgmtFee(BigDecimal actOverdueMgmtFee) {
        this.actOverdueMgmtFee = actOverdueMgmtFee;
    }

    public BigDecimal getActOverdueInterest() {
        return actOverdueInterest;
    }

    public void setActOverdueInterest(BigDecimal actOverdueInterest) {
        this.actOverdueInterest = actOverdueInterest;
    }

    public String getAdvanceType() {
        return advanceType;
    }

    public void setAdvanceType(String advanceType) {
        this.advanceType = advanceType == null ? null : advanceType.trim();
    }

    public BigDecimal getActCapital() {
        return actCapital;
    }

    public void setActCapital(BigDecimal actCapital) {
        this.actCapital = actCapital;
    }

    public String getOfflinePayoffFlag() {
        return offlinePayoffFlag;
    }

    public void setOfflinePayoffFlag(String offlinePayoffFlag) {
        this.offlinePayoffFlag = offlinePayoffFlag == null ? null : offlinePayoffFlag.trim();
    }

    public Date getCalcOverdueTime() {
        return calcOverdueTime;
    }

    public void setCalcOverdueTime(Date calcOverdueTime) {
        this.calcOverdueTime = calcOverdueTime;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public BigDecimal getActPoundage() {
        return actPoundage;
    }

    public void setActPoundage(BigDecimal actPoundage) {
        this.actPoundage = actPoundage;
    }
}