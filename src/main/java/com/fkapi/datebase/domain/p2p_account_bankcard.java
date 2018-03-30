package com.fkapi.datebase.domain;

import java.util.Date;

public class p2p_account_bankcard extends p2p_account_bankcardKey {
    private String bankCode;

    private String bankName;

    private Date bindTime;

    private String status;

    private String isDefault;

    private String expressFlag;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    public String getExpressFlag() {
        return expressFlag;
    }

    public void setExpressFlag(String expressFlag) {
        this.expressFlag = expressFlag == null ? null : expressFlag.trim();
    }
}