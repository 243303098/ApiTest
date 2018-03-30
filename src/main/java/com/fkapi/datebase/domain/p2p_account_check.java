package com.fkapi.datebase.domain;

public class p2p_account_check extends p2p_account_checkKey {
    private String acctNo;

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }
}