package com.fkapi.datebase.domain;

import java.math.BigDecimal;
import java.util.Date;

public class p2p_loan_claim {
    private String projectNo;

    private String projectName;

    private String projectChannel;

    private Long loanTempId;

    private String loanTempName;

    private String originClaimNo;

    private BigDecimal loanAmount;

    private BigDecimal remainClaimAmount;

    private Integer loanTerm;

    private Integer remainTerm;

    private String timeType;

    private BigDecimal interestRate;

    private Date loanDate;

    private Date expireDate;

    private Date expectDate;

    private Date nextRepayDate;

    private String projectTo;

    private String releaseStatus;

    private Date createTime;

    private String prjChlCorp;

    private String prjChlType;

    private String guaranteeCorp;

    private String guaranteeMethod;

    private String guaranteeName;

    private BigDecimal platformRate;

    private BigDecimal guaranteeRate;

    private String isOverdue;

    private String repayType;

    private String loanProductType;

    private Long operator;

    private Long custId;

    private String repayMethod;

    private String projectType;

    private Long version;

    private String loanPurpose;

    private String repaySource;

    private String loanMethod;

    private BigDecimal applyAmount;

    private Integer applyTerm;

    private String allowEarlyRepay;

    private BigDecimal minLoanAmount;

    private BigDecimal maxLoanAmount;

    private String gatherFeePoint;

    private BigDecimal earlyPayRate;

    private String overdueAdvance;

    private String advanceOrder;

    private String partRepay;

    private String repurchaseType;

    private Integer repurchaseDays;

    private BigDecimal approveAmount;

    private Integer termNo;

    private BigDecimal repayCapital;

    private BigDecimal overdueFee;

    private Integer overdueDays;

    private String repayStatus;

    private BigDecimal repayInterest;

    private String auditStatus;

    private String borrowerName;

    private String borrowerType;

    private String corpNo;

    private String loanName;

    private Date forceLoseDate;

    private BigDecimal overdueRate;

    private String overdueFeeType;

    private BigDecimal lowPlatformFee;

    private String loanSrc;

    private BigDecimal platformServiceFee;

    private Date fundMatchTime;

    private Integer remitCounter;

    private String assetCorpNo;

    private String assetCorpName;

    private Date interviewDate;

    private BigDecimal prepaymentRate;

    private String signFlag;

    private String autoAdvanceFlag;

    private Date redeemDate;

    private String redemption;

    private String lockSign;

    private Date lockedTime;

    private String lockMethod;

    private String trustFlag;

    private String trustCorpType;

    private Date payoffDate;

    private String payoffCorp;

    private String filterSign;

    private String lockTerm;

    private String loanSubSrc;

    private Date currAuditTime;

    private BigDecimal repayCapitalRatio;

    private String prjCfgType;

    private String loanPayee;

    private BigDecimal poundageRate;

    private BigDecimal overdueFeeRate;

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectChannel() {
        return projectChannel;
    }

    public void setProjectChannel(String projectChannel) {
        this.projectChannel = projectChannel == null ? null : projectChannel.trim();
    }

    public Long getLoanTempId() {
        return loanTempId;
    }

    public void setLoanTempId(Long loanTempId) {
        this.loanTempId = loanTempId;
    }

    public String getLoanTempName() {
        return loanTempName;
    }

    public void setLoanTempName(String loanTempName) {
        this.loanTempName = loanTempName == null ? null : loanTempName.trim();
    }

    public String getOriginClaimNo() {
        return originClaimNo;
    }

    public void setOriginClaimNo(String originClaimNo) {
        this.originClaimNo = originClaimNo == null ? null : originClaimNo.trim();
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getRemainClaimAmount() {
        return remainClaimAmount;
    }

    public void setRemainClaimAmount(BigDecimal remainClaimAmount) {
        this.remainClaimAmount = remainClaimAmount;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public Integer getRemainTerm() {
        return remainTerm;
    }

    public void setRemainTerm(Integer remainTerm) {
        this.remainTerm = remainTerm;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType == null ? null : timeType.trim();
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(Date expectDate) {
        this.expectDate = expectDate;
    }

    public Date getNextRepayDate() {
        return nextRepayDate;
    }

    public void setNextRepayDate(Date nextRepayDate) {
        this.nextRepayDate = nextRepayDate;
    }

    public String getProjectTo() {
        return projectTo;
    }

    public void setProjectTo(String projectTo) {
        this.projectTo = projectTo == null ? null : projectTo.trim();
    }

    public String getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(String releaseStatus) {
        this.releaseStatus = releaseStatus == null ? null : releaseStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPrjChlCorp() {
        return prjChlCorp;
    }

    public void setPrjChlCorp(String prjChlCorp) {
        this.prjChlCorp = prjChlCorp == null ? null : prjChlCorp.trim();
    }

    public String getPrjChlType() {
        return prjChlType;
    }

    public void setPrjChlType(String prjChlType) {
        this.prjChlType = prjChlType == null ? null : prjChlType.trim();
    }

    public String getGuaranteeCorp() {
        return guaranteeCorp;
    }

    public void setGuaranteeCorp(String guaranteeCorp) {
        this.guaranteeCorp = guaranteeCorp == null ? null : guaranteeCorp.trim();
    }

    public String getGuaranteeMethod() {
        return guaranteeMethod;
    }

    public void setGuaranteeMethod(String guaranteeMethod) {
        this.guaranteeMethod = guaranteeMethod == null ? null : guaranteeMethod.trim();
    }

    public String getGuaranteeName() {
        return guaranteeName;
    }

    public void setGuaranteeName(String guaranteeName) {
        this.guaranteeName = guaranteeName == null ? null : guaranteeName.trim();
    }

    public BigDecimal getPlatformRate() {
        return platformRate;
    }

    public void setPlatformRate(BigDecimal platformRate) {
        this.platformRate = platformRate;
    }

    public BigDecimal getGuaranteeRate() {
        return guaranteeRate;
    }

    public void setGuaranteeRate(BigDecimal guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue == null ? null : isOverdue.trim();
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType == null ? null : repayType.trim();
    }

    public String getLoanProductType() {
        return loanProductType;
    }

    public void setLoanProductType(String loanProductType) {
        this.loanProductType = loanProductType == null ? null : loanProductType.trim();
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getRepayMethod() {
        return repayMethod;
    }

    public void setRepayMethod(String repayMethod) {
        this.repayMethod = repayMethod == null ? null : repayMethod.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose == null ? null : loanPurpose.trim();
    }

    public String getRepaySource() {
        return repaySource;
    }

    public void setRepaySource(String repaySource) {
        this.repaySource = repaySource == null ? null : repaySource.trim();
    }

    public String getLoanMethod() {
        return loanMethod;
    }

    public void setLoanMethod(String loanMethod) {
        this.loanMethod = loanMethod == null ? null : loanMethod.trim();
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public Integer getApplyTerm() {
        return applyTerm;
    }

    public void setApplyTerm(Integer applyTerm) {
        this.applyTerm = applyTerm;
    }

    public String getAllowEarlyRepay() {
        return allowEarlyRepay;
    }

    public void setAllowEarlyRepay(String allowEarlyRepay) {
        this.allowEarlyRepay = allowEarlyRepay == null ? null : allowEarlyRepay.trim();
    }

    public BigDecimal getMinLoanAmount() {
        return minLoanAmount;
    }

    public void setMinLoanAmount(BigDecimal minLoanAmount) {
        this.minLoanAmount = minLoanAmount;
    }

    public BigDecimal getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(BigDecimal maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public String getGatherFeePoint() {
        return gatherFeePoint;
    }

    public void setGatherFeePoint(String gatherFeePoint) {
        this.gatherFeePoint = gatherFeePoint == null ? null : gatherFeePoint.trim();
    }

    public BigDecimal getEarlyPayRate() {
        return earlyPayRate;
    }

    public void setEarlyPayRate(BigDecimal earlyPayRate) {
        this.earlyPayRate = earlyPayRate;
    }

    public String getOverdueAdvance() {
        return overdueAdvance;
    }

    public void setOverdueAdvance(String overdueAdvance) {
        this.overdueAdvance = overdueAdvance == null ? null : overdueAdvance.trim();
    }

    public String getAdvanceOrder() {
        return advanceOrder;
    }

    public void setAdvanceOrder(String advanceOrder) {
        this.advanceOrder = advanceOrder == null ? null : advanceOrder.trim();
    }

    public String getPartRepay() {
        return partRepay;
    }

    public void setPartRepay(String partRepay) {
        this.partRepay = partRepay == null ? null : partRepay.trim();
    }

    public String getRepurchaseType() {
        return repurchaseType;
    }

    public void setRepurchaseType(String repurchaseType) {
        this.repurchaseType = repurchaseType == null ? null : repurchaseType.trim();
    }

    public Integer getRepurchaseDays() {
        return repurchaseDays;
    }

    public void setRepurchaseDays(Integer repurchaseDays) {
        this.repurchaseDays = repurchaseDays;
    }

    public BigDecimal getApproveAmount() {
        return approveAmount;
    }

    public void setApproveAmount(BigDecimal approveAmount) {
        this.approveAmount = approveAmount;
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

    public BigDecimal getOverdueFee() {
        return overdueFee;
    }

    public void setOverdueFee(BigDecimal overdueFee) {
        this.overdueFee = overdueFee;
    }

    public Integer getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(Integer overdueDays) {
        this.overdueDays = overdueDays;
    }

    public String getRepayStatus() {
        return repayStatus;
    }

    public void setRepayStatus(String repayStatus) {
        this.repayStatus = repayStatus == null ? null : repayStatus.trim();
    }

    public BigDecimal getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(BigDecimal repayInterest) {
        this.repayInterest = repayInterest;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName == null ? null : borrowerName.trim();
    }

    public String getBorrowerType() {
        return borrowerType;
    }

    public void setBorrowerType(String borrowerType) {
        this.borrowerType = borrowerType == null ? null : borrowerType.trim();
    }

    public String getCorpNo() {
        return corpNo;
    }

    public void setCorpNo(String corpNo) {
        this.corpNo = corpNo == null ? null : corpNo.trim();
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName == null ? null : loanName.trim();
    }

    public Date getForceLoseDate() {
        return forceLoseDate;
    }

    public void setForceLoseDate(Date forceLoseDate) {
        this.forceLoseDate = forceLoseDate;
    }

    public BigDecimal getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(BigDecimal overdueRate) {
        this.overdueRate = overdueRate;
    }

    public String getOverdueFeeType() {
        return overdueFeeType;
    }

    public void setOverdueFeeType(String overdueFeeType) {
        this.overdueFeeType = overdueFeeType == null ? null : overdueFeeType.trim();
    }

    public BigDecimal getLowPlatformFee() {
        return lowPlatformFee;
    }

    public void setLowPlatformFee(BigDecimal lowPlatformFee) {
        this.lowPlatformFee = lowPlatformFee;
    }

    public String getLoanSrc() {
        return loanSrc;
    }

    public void setLoanSrc(String loanSrc) {
        this.loanSrc = loanSrc == null ? null : loanSrc.trim();
    }

    public BigDecimal getPlatformServiceFee() {
        return platformServiceFee;
    }

    public void setPlatformServiceFee(BigDecimal platformServiceFee) {
        this.platformServiceFee = platformServiceFee;
    }

    public Date getFundMatchTime() {
        return fundMatchTime;
    }

    public void setFundMatchTime(Date fundMatchTime) {
        this.fundMatchTime = fundMatchTime;
    }

    public Integer getRemitCounter() {
        return remitCounter;
    }

    public void setRemitCounter(Integer remitCounter) {
        this.remitCounter = remitCounter;
    }

    public String getAssetCorpNo() {
        return assetCorpNo;
    }

    public void setAssetCorpNo(String assetCorpNo) {
        this.assetCorpNo = assetCorpNo == null ? null : assetCorpNo.trim();
    }

    public String getAssetCorpName() {
        return assetCorpName;
    }

    public void setAssetCorpName(String assetCorpName) {
        this.assetCorpName = assetCorpName == null ? null : assetCorpName.trim();
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public BigDecimal getPrepaymentRate() {
        return prepaymentRate;
    }

    public void setPrepaymentRate(BigDecimal prepaymentRate) {
        this.prepaymentRate = prepaymentRate;
    }

    public String getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag == null ? null : signFlag.trim();
    }

    public String getAutoAdvanceFlag() {
        return autoAdvanceFlag;
    }

    public void setAutoAdvanceFlag(String autoAdvanceFlag) {
        this.autoAdvanceFlag = autoAdvanceFlag == null ? null : autoAdvanceFlag.trim();
    }

    public Date getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(Date redeemDate) {
        this.redeemDate = redeemDate;
    }

    public String getRedemption() {
        return redemption;
    }

    public void setRedemption(String redemption) {
        this.redemption = redemption == null ? null : redemption.trim();
    }

    public String getLockSign() {
        return lockSign;
    }

    public void setLockSign(String lockSign) {
        this.lockSign = lockSign == null ? null : lockSign.trim();
    }

    public Date getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Date lockedTime) {
        this.lockedTime = lockedTime;
    }

    public String getLockMethod() {
        return lockMethod;
    }

    public void setLockMethod(String lockMethod) {
        this.lockMethod = lockMethod == null ? null : lockMethod.trim();
    }

    public String getTrustFlag() {
        return trustFlag;
    }

    public void setTrustFlag(String trustFlag) {
        this.trustFlag = trustFlag == null ? null : trustFlag.trim();
    }

    public String getTrustCorpType() {
        return trustCorpType;
    }

    public void setTrustCorpType(String trustCorpType) {
        this.trustCorpType = trustCorpType == null ? null : trustCorpType.trim();
    }

    public Date getPayoffDate() {
        return payoffDate;
    }

    public void setPayoffDate(Date payoffDate) {
        this.payoffDate = payoffDate;
    }

    public String getPayoffCorp() {
        return payoffCorp;
    }

    public void setPayoffCorp(String payoffCorp) {
        this.payoffCorp = payoffCorp == null ? null : payoffCorp.trim();
    }

    public String getFilterSign() {
        return filterSign;
    }

    public void setFilterSign(String filterSign) {
        this.filterSign = filterSign == null ? null : filterSign.trim();
    }

    public String getLockTerm() {
        return lockTerm;
    }

    public void setLockTerm(String lockTerm) {
        this.lockTerm = lockTerm == null ? null : lockTerm.trim();
    }

    public String getLoanSubSrc() {
        return loanSubSrc;
    }

    public void setLoanSubSrc(String loanSubSrc) {
        this.loanSubSrc = loanSubSrc == null ? null : loanSubSrc.trim();
    }

    public Date getCurrAuditTime() {
        return currAuditTime;
    }

    public void setCurrAuditTime(Date currAuditTime) {
        this.currAuditTime = currAuditTime;
    }

    public BigDecimal getRepayCapitalRatio() {
        return repayCapitalRatio;
    }

    public void setRepayCapitalRatio(BigDecimal repayCapitalRatio) {
        this.repayCapitalRatio = repayCapitalRatio;
    }

    public String getPrjCfgType() {
        return prjCfgType;
    }

    public void setPrjCfgType(String prjCfgType) {
        this.prjCfgType = prjCfgType == null ? null : prjCfgType.trim();
    }

    public String getLoanPayee() {
        return loanPayee;
    }

    public void setLoanPayee(String loanPayee) {
        this.loanPayee = loanPayee == null ? null : loanPayee.trim();
    }

    public BigDecimal getPoundageRate() {
        return poundageRate;
    }

    public void setPoundageRate(BigDecimal poundageRate) {
        this.poundageRate = poundageRate;
    }

    public BigDecimal getOverdueFeeRate() {
        return overdueFeeRate;
    }

    public void setOverdueFeeRate(BigDecimal overdueFeeRate) {
        this.overdueFeeRate = overdueFeeRate;
    }
}