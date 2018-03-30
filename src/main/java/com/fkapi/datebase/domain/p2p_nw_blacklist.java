package com.fkapi.datebase.domain;

import java.math.BigDecimal;
import java.util.Date;

public class p2p_nw_blacklist extends p2p_nw_blacklistKey {
    private String loanSubSrc;

    private String remark;

    private Date overdueTime;

    private BigDecimal overdueCapital;

    private Date payoffTime;

    private Date createTime;

    private Date updateTime;

    public String getLoanSubSrc() {
        return loanSubSrc;
    }

    public void setLoanSubSrc(String loanSubSrc) {
        this.loanSubSrc = loanSubSrc == null ? null : loanSubSrc.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    public BigDecimal getOverdueCapital() {
        return overdueCapital;
    }

    public void setOverdueCapital(BigDecimal overdueCapital) {
        this.overdueCapital = overdueCapital;
    }

    public Date getPayoffTime() {
        return payoffTime;
    }

    public void setPayoffTime(Date payoffTime) {
        this.payoffTime = payoffTime;
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