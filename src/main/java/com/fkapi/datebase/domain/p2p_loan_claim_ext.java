package com.fkapi.datebase.domain;

import java.math.BigDecimal;
import java.util.Date;

public class p2p_loan_claim_ext {
    private String projectNo;

    private String actOverdueFlag;

    private Date failScaleTime;

    private String pengyuanReqStatus;

    private BigDecimal productPrice;

    private BigDecimal rebateRate;

    private String bdfPrjType;

    private String failScaleKind;

    private String groupSign;

    private Integer segment;

    private String groupSign2;

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getActOverdueFlag() {
        return actOverdueFlag;
    }

    public void setActOverdueFlag(String actOverdueFlag) {
        this.actOverdueFlag = actOverdueFlag == null ? null : actOverdueFlag.trim();
    }

    public Date getFailScaleTime() {
        return failScaleTime;
    }

    public void setFailScaleTime(Date failScaleTime) {
        this.failScaleTime = failScaleTime;
    }

    public String getPengyuanReqStatus() {
        return pengyuanReqStatus;
    }

    public void setPengyuanReqStatus(String pengyuanReqStatus) {
        this.pengyuanReqStatus = pengyuanReqStatus == null ? null : pengyuanReqStatus.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(BigDecimal rebateRate) {
        this.rebateRate = rebateRate;
    }

    public String getBdfPrjType() {
        return bdfPrjType;
    }

    public void setBdfPrjType(String bdfPrjType) {
        this.bdfPrjType = bdfPrjType == null ? null : bdfPrjType.trim();
    }

    public String getFailScaleKind() {
        return failScaleKind;
    }

    public void setFailScaleKind(String failScaleKind) {
        this.failScaleKind = failScaleKind == null ? null : failScaleKind.trim();
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

    public String getGroupSign2() {
        return groupSign2;
    }

    public void setGroupSign2(String groupSign2) {
        this.groupSign2 = groupSign2 == null ? null : groupSign2.trim();
    }
}