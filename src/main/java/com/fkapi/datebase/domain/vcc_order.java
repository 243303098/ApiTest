package com.fkapi.datebase.domain;

import java.math.BigDecimal;
import java.util.Date;

public class vcc_order {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.ORDER_NO
     *
     * @mbg.generated
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.MERCHANT_NO
     *
     * @mbg.generated
     */
    private String merchantNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.CARD_NO
     *
     * @mbg.generated
     */
    private String cardNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.SUBMIT_TIME
     *
     * @mbg.generated
     */
    private Date submitTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.TRANS_AMOUNT
     *
     * @mbg.generated
     */
    private BigDecimal transAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.RETURN_AMOUNT
     *
     * @mbg.generated
     */
    private BigDecimal returnAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.TRANS_TIME
     *
     * @mbg.generated
     */
    private Date transTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.REVOKED_TIME
     *
     * @mbg.generated
     */
    private Date revokedTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.ORDER_STATUS
     *
     * @mbg.generated
     */
    private String orderStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.AUDIT_STATUS
     *
     * @mbg.generated
     */
    private String auditStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.LIQUIDATION_TIME
     *
     * @mbg.generated
     */
    private Date liquidationTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.CREATE_TIME
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.UPDATE_TIME
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcc_order.VERSION
     *
     * @mbg.generated
     */
    private Long version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.ORDER_NO
     *
     * @return the value of vcc_order.ORDER_NO
     *
     * @mbg.generated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.ORDER_NO
     *
     * @param orderNo the value for vcc_order.ORDER_NO
     *
     * @mbg.generated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.MERCHANT_NO
     *
     * @return the value of vcc_order.MERCHANT_NO
     *
     * @mbg.generated
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.MERCHANT_NO
     *
     * @param merchantNo the value for vcc_order.MERCHANT_NO
     *
     * @mbg.generated
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.CARD_NO
     *
     * @return the value of vcc_order.CARD_NO
     *
     * @mbg.generated
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.CARD_NO
     *
     * @param cardNo the value for vcc_order.CARD_NO
     *
     * @mbg.generated
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.SUBMIT_TIME
     *
     * @return the value of vcc_order.SUBMIT_TIME
     *
     * @mbg.generated
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.SUBMIT_TIME
     *
     * @param submitTime the value for vcc_order.SUBMIT_TIME
     *
     * @mbg.generated
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.TRANS_AMOUNT
     *
     * @return the value of vcc_order.TRANS_AMOUNT
     *
     * @mbg.generated
     */
    public BigDecimal getTransAmount() {
        return transAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.TRANS_AMOUNT
     *
     * @param transAmount the value for vcc_order.TRANS_AMOUNT
     *
     * @mbg.generated
     */
    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.RETURN_AMOUNT
     *
     * @return the value of vcc_order.RETURN_AMOUNT
     *
     * @mbg.generated
     */
    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.RETURN_AMOUNT
     *
     * @param returnAmount the value for vcc_order.RETURN_AMOUNT
     *
     * @mbg.generated
     */
    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.TRANS_TIME
     *
     * @return the value of vcc_order.TRANS_TIME
     *
     * @mbg.generated
     */
    public Date getTransTime() {
        return transTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.TRANS_TIME
     *
     * @param transTime the value for vcc_order.TRANS_TIME
     *
     * @mbg.generated
     */
    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.REVOKED_TIME
     *
     * @return the value of vcc_order.REVOKED_TIME
     *
     * @mbg.generated
     */
    public Date getRevokedTime() {
        return revokedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.REVOKED_TIME
     *
     * @param revokedTime the value for vcc_order.REVOKED_TIME
     *
     * @mbg.generated
     */
    public void setRevokedTime(Date revokedTime) {
        this.revokedTime = revokedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.ORDER_STATUS
     *
     * @return the value of vcc_order.ORDER_STATUS
     *
     * @mbg.generated
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.ORDER_STATUS
     *
     * @param orderStatus the value for vcc_order.ORDER_STATUS
     *
     * @mbg.generated
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.AUDIT_STATUS
     *
     * @return the value of vcc_order.AUDIT_STATUS
     *
     * @mbg.generated
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.AUDIT_STATUS
     *
     * @param auditStatus the value for vcc_order.AUDIT_STATUS
     *
     * @mbg.generated
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.LIQUIDATION_TIME
     *
     * @return the value of vcc_order.LIQUIDATION_TIME
     *
     * @mbg.generated
     */
    public Date getLiquidationTime() {
        return liquidationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.LIQUIDATION_TIME
     *
     * @param liquidationTime the value for vcc_order.LIQUIDATION_TIME
     *
     * @mbg.generated
     */
    public void setLiquidationTime(Date liquidationTime) {
        this.liquidationTime = liquidationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.CREATE_TIME
     *
     * @return the value of vcc_order.CREATE_TIME
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.CREATE_TIME
     *
     * @param createTime the value for vcc_order.CREATE_TIME
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.UPDATE_TIME
     *
     * @return the value of vcc_order.UPDATE_TIME
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.UPDATE_TIME
     *
     * @param updateTime the value for vcc_order.UPDATE_TIME
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcc_order.VERSION
     *
     * @return the value of vcc_order.VERSION
     *
     * @mbg.generated
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcc_order.VERSION
     *
     * @param version the value for vcc_order.VERSION
     *
     * @mbg.generated
     */
    public void setVersion(Long version) {
        this.version = version;
    }
}