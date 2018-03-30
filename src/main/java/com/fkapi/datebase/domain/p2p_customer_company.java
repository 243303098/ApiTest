package com.fkapi.datebase.domain;

import java.math.BigDecimal;
import java.util.Date;

public class p2p_customer_company {
    private Long id;

    private Long custId;

    private String compName;

    private String compAddr;

    private String compTel;

    private String industryType;

    private String position;

    private Date inTime;

    private Date departureTime;

    private BigDecimal salary;

    private BigDecimal actualSalary;

    private BigDecimal otherIncoming;

    private String socialSecuryStatus;

    private String socialSecuryType;

    private BigDecimal socialSecuryBase;

    private BigDecimal socialSecuryAmount;

    private String accumulationFundStatus;

    private BigDecimal accumulationFundBase;

    private BigDecimal accumulationFundAmount;

    private Date createTime;

    private Date updateTime;

    private String compAddrProvinceCode;

    private String compAddrCityCode;

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

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName == null ? null : compName.trim();
    }

    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr == null ? null : compAddr.trim();
    }

    public String getCompTel() {
        return compTel;
    }

    public void setCompTel(String compTel) {
        this.compTel = compTel == null ? null : compTel.trim();
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType == null ? null : industryType.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getActualSalary() {
        return actualSalary;
    }

    public void setActualSalary(BigDecimal actualSalary) {
        this.actualSalary = actualSalary;
    }

    public BigDecimal getOtherIncoming() {
        return otherIncoming;
    }

    public void setOtherIncoming(BigDecimal otherIncoming) {
        this.otherIncoming = otherIncoming;
    }

    public String getSocialSecuryStatus() {
        return socialSecuryStatus;
    }

    public void setSocialSecuryStatus(String socialSecuryStatus) {
        this.socialSecuryStatus = socialSecuryStatus == null ? null : socialSecuryStatus.trim();
    }

    public String getSocialSecuryType() {
        return socialSecuryType;
    }

    public void setSocialSecuryType(String socialSecuryType) {
        this.socialSecuryType = socialSecuryType == null ? null : socialSecuryType.trim();
    }

    public BigDecimal getSocialSecuryBase() {
        return socialSecuryBase;
    }

    public void setSocialSecuryBase(BigDecimal socialSecuryBase) {
        this.socialSecuryBase = socialSecuryBase;
    }

    public BigDecimal getSocialSecuryAmount() {
        return socialSecuryAmount;
    }

    public void setSocialSecuryAmount(BigDecimal socialSecuryAmount) {
        this.socialSecuryAmount = socialSecuryAmount;
    }

    public String getAccumulationFundStatus() {
        return accumulationFundStatus;
    }

    public void setAccumulationFundStatus(String accumulationFundStatus) {
        this.accumulationFundStatus = accumulationFundStatus == null ? null : accumulationFundStatus.trim();
    }

    public BigDecimal getAccumulationFundBase() {
        return accumulationFundBase;
    }

    public void setAccumulationFundBase(BigDecimal accumulationFundBase) {
        this.accumulationFundBase = accumulationFundBase;
    }

    public BigDecimal getAccumulationFundAmount() {
        return accumulationFundAmount;
    }

    public void setAccumulationFundAmount(BigDecimal accumulationFundAmount) {
        this.accumulationFundAmount = accumulationFundAmount;
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

    public String getCompAddrProvinceCode() {
        return compAddrProvinceCode;
    }

    public void setCompAddrProvinceCode(String compAddrProvinceCode) {
        this.compAddrProvinceCode = compAddrProvinceCode == null ? null : compAddrProvinceCode.trim();
    }

    public String getCompAddrCityCode() {
        return compAddrCityCode;
    }

    public void setCompAddrCityCode(String compAddrCityCode) {
        this.compAddrCityCode = compAddrCityCode == null ? null : compAddrCityCode.trim();
    }
}