package com.fkapi.datebase.domain;

import java.math.BigDecimal;
import java.util.Date;

public class jxl_phone_call_records {
    private String id;

    private Long primaryId;

    private String cellPhone;

    private String otherCellPhone;

    private String callPlace;

    private Date startTime;

    private Long useTime;

    private String callType;

    private String initType;

    private BigDecimal subtotal;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Long primaryId) {
        this.primaryId = primaryId;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone == null ? null : cellPhone.trim();
    }

    public String getOtherCellPhone() {
        return otherCellPhone;
    }

    public void setOtherCellPhone(String otherCellPhone) {
        this.otherCellPhone = otherCellPhone == null ? null : otherCellPhone.trim();
    }

    public String getCallPlace() {
        return callPlace;
    }

    public void setCallPlace(String callPlace) {
        this.callPlace = callPlace == null ? null : callPlace.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType == null ? null : callType.trim();
    }

    public String getInitType() {
        return initType;
    }

    public void setInitType(String initType) {
        this.initType = initType == null ? null : initType.trim();
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}