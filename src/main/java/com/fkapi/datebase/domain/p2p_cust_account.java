package com.fkapi.datebase.domain;

import java.math.BigDecimal;
import java.util.Date;

public class p2p_cust_account {
    private String accountNo;

    private Long custId;

    private BigDecimal collectingPrincipal;

    private BigDecimal collectingRevenue;

    private BigDecimal forzenAmount;

    private String accountStatus;

    private BigDecimal collectedPrincipal;

    private BigDecimal collectedRevenue;

    private BigDecimal usableBalance;

    private String investFlg;

    private String loanFlg;

    private Date updateTime;

    private Date lockTime;

    private String isDefault;

    private Long custRuleId;

    private BigDecimal withdrewAmount;

    private BigDecimal allInvestAmount;

    private BigDecimal allRechargeAmount;

    private Long version;

    private BigDecimal lockAmount;

    private BigDecimal withdrawForzen;

    private BigDecimal directForzen;

    private BigDecimal planForzen;

    private BigDecimal directRevenue;

    private BigDecimal planRevenue;

    private BigDecimal hadDirectRevenue;

    private BigDecimal hadPlanRevenue;

    private BigDecimal incomeLock;

    private BigDecimal planLock;

    private BigDecimal directPrincipal;

    private BigDecimal planPrincipal;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public BigDecimal getCollectingPrincipal() {
        return collectingPrincipal;
    }

    public void setCollectingPrincipal(BigDecimal collectingPrincipal) {
        this.collectingPrincipal = collectingPrincipal;
    }

    public BigDecimal getCollectingRevenue() {
        return collectingRevenue;
    }

    public void setCollectingRevenue(BigDecimal collectingRevenue) {
        this.collectingRevenue = collectingRevenue;
    }

    public BigDecimal getForzenAmount() {
        return forzenAmount;
    }

    public void setForzenAmount(BigDecimal forzenAmount) {
        this.forzenAmount = forzenAmount;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus == null ? null : accountStatus.trim();
    }

    public BigDecimal getCollectedPrincipal() {
        return collectedPrincipal;
    }

    public void setCollectedPrincipal(BigDecimal collectedPrincipal) {
        this.collectedPrincipal = collectedPrincipal;
    }

    public BigDecimal getCollectedRevenue() {
        return collectedRevenue;
    }

    public void setCollectedRevenue(BigDecimal collectedRevenue) {
        this.collectedRevenue = collectedRevenue;
    }

    public BigDecimal getUsableBalance() {
        return usableBalance;
    }

    public void setUsableBalance(BigDecimal usableBalance) {
        this.usableBalance = usableBalance;
    }

    public String getInvestFlg() {
        return investFlg;
    }

    public void setInvestFlg(String investFlg) {
        this.investFlg = investFlg == null ? null : investFlg.trim();
    }

    public String getLoanFlg() {
        return loanFlg;
    }

    public void setLoanFlg(String loanFlg) {
        this.loanFlg = loanFlg == null ? null : loanFlg.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    public Long getCustRuleId() {
        return custRuleId;
    }

    public void setCustRuleId(Long custRuleId) {
        this.custRuleId = custRuleId;
    }

    public BigDecimal getWithdrewAmount() {
        return withdrewAmount;
    }

    public void setWithdrewAmount(BigDecimal withdrewAmount) {
        this.withdrewAmount = withdrewAmount;
    }

    public BigDecimal getAllInvestAmount() {
        return allInvestAmount;
    }

    public void setAllInvestAmount(BigDecimal allInvestAmount) {
        this.allInvestAmount = allInvestAmount;
    }

    public BigDecimal getAllRechargeAmount() {
        return allRechargeAmount;
    }

    public void setAllRechargeAmount(BigDecimal allRechargeAmount) {
        this.allRechargeAmount = allRechargeAmount;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public BigDecimal getLockAmount() {
        return lockAmount;
    }

    public void setLockAmount(BigDecimal lockAmount) {
        this.lockAmount = lockAmount;
    }

    public BigDecimal getWithdrawForzen() {
        return withdrawForzen;
    }

    public void setWithdrawForzen(BigDecimal withdrawForzen) {
        this.withdrawForzen = withdrawForzen;
    }

    public BigDecimal getDirectForzen() {
        return directForzen;
    }

    public void setDirectForzen(BigDecimal directForzen) {
        this.directForzen = directForzen;
    }

    public BigDecimal getPlanForzen() {
        return planForzen;
    }

    public void setPlanForzen(BigDecimal planForzen) {
        this.planForzen = planForzen;
    }

    public BigDecimal getDirectRevenue() {
        return directRevenue;
    }

    public void setDirectRevenue(BigDecimal directRevenue) {
        this.directRevenue = directRevenue;
    }

    public BigDecimal getPlanRevenue() {
        return planRevenue;
    }

    public void setPlanRevenue(BigDecimal planRevenue) {
        this.planRevenue = planRevenue;
    }

    public BigDecimal getHadDirectRevenue() {
        return hadDirectRevenue;
    }

    public void setHadDirectRevenue(BigDecimal hadDirectRevenue) {
        this.hadDirectRevenue = hadDirectRevenue;
    }

    public BigDecimal getHadPlanRevenue() {
        return hadPlanRevenue;
    }

    public void setHadPlanRevenue(BigDecimal hadPlanRevenue) {
        this.hadPlanRevenue = hadPlanRevenue;
    }

    public BigDecimal getIncomeLock() {
        return incomeLock;
    }

    public void setIncomeLock(BigDecimal incomeLock) {
        this.incomeLock = incomeLock;
    }

    public BigDecimal getPlanLock() {
        return planLock;
    }

    public void setPlanLock(BigDecimal planLock) {
        this.planLock = planLock;
    }

    public BigDecimal getDirectPrincipal() {
        return directPrincipal;
    }

    public void setDirectPrincipal(BigDecimal directPrincipal) {
        this.directPrincipal = directPrincipal;
    }

    public BigDecimal getPlanPrincipal() {
        return planPrincipal;
    }

    public void setPlanPrincipal(BigDecimal planPrincipal) {
        this.planPrincipal = planPrincipal;
    }
}