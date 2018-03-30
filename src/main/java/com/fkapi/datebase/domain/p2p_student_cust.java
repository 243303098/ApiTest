package com.fkapi.datebase.domain;

import java.util.Date;

public class p2p_student_cust {
    private Long custId;

    private String signTxt;

    private String schoolName;

    private String college;

    private String major;

    private Integer enterYear;

    private String dreams;

    private String relationship;

    private String birthSecret;

    private String schoolNameSecret;

    private String sexSecret;

    private String relationshipSecret;

    private String province;

    private String city;

    private String parentSecret;

    private String appMarket;

    private String talkFlag;

    private String cadre;

    private Date lastUpdateTime;

    private String cardNo;

    private Long schoolId;

    private Boolean isAdult;

    private Date graduateTime;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getSignTxt() {
        return signTxt;
    }

    public void setSignTxt(String signTxt) {
        this.signTxt = signTxt == null ? null : signTxt.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Integer getEnterYear() {
        return enterYear;
    }

    public void setEnterYear(Integer enterYear) {
        this.enterYear = enterYear;
    }

    public String getDreams() {
        return dreams;
    }

    public void setDreams(String dreams) {
        this.dreams = dreams == null ? null : dreams.trim();
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }

    public String getBirthSecret() {
        return birthSecret;
    }

    public void setBirthSecret(String birthSecret) {
        this.birthSecret = birthSecret == null ? null : birthSecret.trim();
    }

    public String getSchoolNameSecret() {
        return schoolNameSecret;
    }

    public void setSchoolNameSecret(String schoolNameSecret) {
        this.schoolNameSecret = schoolNameSecret == null ? null : schoolNameSecret.trim();
    }

    public String getSexSecret() {
        return sexSecret;
    }

    public void setSexSecret(String sexSecret) {
        this.sexSecret = sexSecret == null ? null : sexSecret.trim();
    }

    public String getRelationshipSecret() {
        return relationshipSecret;
    }

    public void setRelationshipSecret(String relationshipSecret) {
        this.relationshipSecret = relationshipSecret == null ? null : relationshipSecret.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getParentSecret() {
        return parentSecret;
    }

    public void setParentSecret(String parentSecret) {
        this.parentSecret = parentSecret == null ? null : parentSecret.trim();
    }

    public String getAppMarket() {
        return appMarket;
    }

    public void setAppMarket(String appMarket) {
        this.appMarket = appMarket == null ? null : appMarket.trim();
    }

    public String getTalkFlag() {
        return talkFlag;
    }

    public void setTalkFlag(String talkFlag) {
        this.talkFlag = talkFlag == null ? null : talkFlag.trim();
    }

    public String getCadre() {
        return cadre;
    }

    public void setCadre(String cadre) {
        this.cadre = cadre == null ? null : cadre.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean isAdult) {
        this.isAdult = isAdult;
    }

    public Date getGraduateTime() {
        return graduateTime;
    }

    public void setGraduateTime(Date graduateTime) {
        this.graduateTime = graduateTime;
    }
}