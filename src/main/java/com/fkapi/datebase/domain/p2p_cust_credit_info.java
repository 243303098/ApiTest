package com.fkapi.datebase.domain;

import java.util.Date;

public class p2p_cust_credit_info {
    private Long custId;

    private Integer creditScore;

    private Integer oldCreditScore;

    private Long creditAmount;

    private Long nxbAmount;

    private Long oldCreditAmount;

    private Long oldNxbAmount;

    private Long usedCreditAmount;

    private String isActivity;

    private Integer cityLevel;

    private String segmentId;

    private Date createTime;

    private Date updateTime;

    private Long version;

    private String delFlag;

    private String oldSegmentId;

    private Integer itemCfgVal;

    private Integer creditAuth;

    private Integer oldCreditAuth;

    private Long ndkAmount;

    private Long oldNdkAmount;

    private String groupSign;

    private Integer segment;

    private Long ndkConvertBaseAmount;

    private String convertWaitRepayLoan;

    private String groupSign2;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getOldCreditScore() {
        return oldCreditScore;
    }

    public void setOldCreditScore(Integer oldCreditScore) {
        this.oldCreditScore = oldCreditScore;
    }

    public Long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Long creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Long getNxbAmount() {
        return nxbAmount;
    }

    public void setNxbAmount(Long nxbAmount) {
        this.nxbAmount = nxbAmount;
    }

    public Long getOldCreditAmount() {
        return oldCreditAmount;
    }

    public void setOldCreditAmount(Long oldCreditAmount) {
        this.oldCreditAmount = oldCreditAmount;
    }

    public Long getOldNxbAmount() {
        return oldNxbAmount;
    }

    public void setOldNxbAmount(Long oldNxbAmount) {
        this.oldNxbAmount = oldNxbAmount;
    }

    public Long getUsedCreditAmount() {
        return usedCreditAmount;
    }

    public void setUsedCreditAmount(Long usedCreditAmount) {
        this.usedCreditAmount = usedCreditAmount;
    }

    public String getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(String isActivity) {
        this.isActivity = isActivity == null ? null : isActivity.trim();
    }

    public Integer getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(Integer cityLevel) {
        this.cityLevel = cityLevel;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId == null ? null : segmentId.trim();
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getOldSegmentId() {
        return oldSegmentId;
    }

    public void setOldSegmentId(String oldSegmentId) {
        this.oldSegmentId = oldSegmentId == null ? null : oldSegmentId.trim();
    }

    public Integer getItemCfgVal() {
        return itemCfgVal;
    }

    public void setItemCfgVal(Integer itemCfgVal) {
        this.itemCfgVal = itemCfgVal;
    }

    public Integer getCreditAuth() {
        return creditAuth;
    }

    public void setCreditAuth(Integer creditAuth) {
        this.creditAuth = creditAuth;
    }

    public Integer getOldCreditAuth() {
        return oldCreditAuth;
    }

    public void setOldCreditAuth(Integer oldCreditAuth) {
        this.oldCreditAuth = oldCreditAuth;
    }

    public Long getNdkAmount() {
        return ndkAmount;
    }

    public void setNdkAmount(Long ndkAmount) {
        this.ndkAmount = ndkAmount;
    }

    public Long getOldNdkAmount() {
        return oldNdkAmount;
    }

    public void setOldNdkAmount(Long oldNdkAmount) {
        this.oldNdkAmount = oldNdkAmount;
    }

    public String getGroupSign() {
        return groupSign;
    }

    public void setGroupSign(String groupSign) {
        this.groupSign = groupSign == null ? null : groupSign.trim();
    }

    public Integer getSegment() {
        return segment;
    }

    public void setSegment(Integer segment) {
        this.segment = segment;
    }

    public Long getNdkConvertBaseAmount() {
        return ndkConvertBaseAmount;
    }

    public void setNdkConvertBaseAmount(Long ndkConvertBaseAmount) {
        this.ndkConvertBaseAmount = ndkConvertBaseAmount;
    }

    public String getConvertWaitRepayLoan() {
        return convertWaitRepayLoan;
    }

    public void setConvertWaitRepayLoan(String convertWaitRepayLoan) {
        this.convertWaitRepayLoan = convertWaitRepayLoan == null ? null : convertWaitRepayLoan.trim();
    }

    public String getGroupSign2() {
        return groupSign2;
    }

    public void setGroupSign2(String groupSign2) {
        this.groupSign2 = groupSign2 == null ? null : groupSign2.trim();
    }
}