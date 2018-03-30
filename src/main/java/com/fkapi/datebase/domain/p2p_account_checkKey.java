package com.fkapi.datebase.domain;

public class p2p_account_checkKey {
    private String openAcctUserNo;

    private String acctType;

    public String getOpenAcctUserNo() {
        return openAcctUserNo;
    }

    public void setOpenAcctUserNo(String openAcctUserNo) {
        this.openAcctUserNo = openAcctUserNo == null ? null : openAcctUserNo.trim();
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }
}