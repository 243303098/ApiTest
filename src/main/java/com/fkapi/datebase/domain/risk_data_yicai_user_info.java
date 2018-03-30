package com.fkapi.datebase.domain;

import java.util.Date;

public class risk_data_yicai_user_info {
    private Long id;

    private Long custId;

    private String certId;

    private String name;

    private Integer currentStatus;

    private Date entryDate;

    private String contractCompany;

    private Integer contraceType;

    private Integer contractSubType;

    private Date gmtCreate;

    private Date gmtModified;

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

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId == null ? null : certId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getContractCompany() {
        return contractCompany;
    }

    public void setContractCompany(String contractCompany) {
        this.contractCompany = contractCompany == null ? null : contractCompany.trim();
    }

    public Integer getContraceType() {
        return contraceType;
    }

    public void setContraceType(Integer contraceType) {
        this.contraceType = contraceType;
    }

    public Integer getContractSubType() {
        return contractSubType;
    }

    public void setContractSubType(Integer contractSubType) {
        this.contractSubType = contractSubType;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}