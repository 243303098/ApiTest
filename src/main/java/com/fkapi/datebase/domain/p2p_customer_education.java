package com.fkapi.datebase.domain;

import java.util.Date;

public class p2p_customer_education {
    private Long id;

    private Long custId;

    private String education;

    private String schoolRoll;

    private String proCert;

    private String schoolName;

    private Date entranceTime;

    private Date graduationTime;

    private String major;

    private String academyAddr;

    private String department;

    private String schoolPart;

    private String examKind;

    private String schoolJob;

    private String schoolHonour;

    private Date createTime;

    private String authStatus;

    private Date authTime;

    private Date applyTime;

    private Long schoolId;

    private Long schoolNameSecret;

    private Long province;

    private Long city;

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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getSchoolRoll() {
        return schoolRoll;
    }

    public void setSchoolRoll(String schoolRoll) {
        this.schoolRoll = schoolRoll == null ? null : schoolRoll.trim();
    }

    public String getProCert() {
        return proCert;
    }

    public void setProCert(String proCert) {
        this.proCert = proCert == null ? null : proCert.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Date getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(Date entranceTime) {
        this.entranceTime = entranceTime;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getAcademyAddr() {
        return academyAddr;
    }

    public void setAcademyAddr(String academyAddr) {
        this.academyAddr = academyAddr == null ? null : academyAddr.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getSchoolPart() {
        return schoolPart;
    }

    public void setSchoolPart(String schoolPart) {
        this.schoolPart = schoolPart == null ? null : schoolPart.trim();
    }

    public String getExamKind() {
        return examKind;
    }

    public void setExamKind(String examKind) {
        this.examKind = examKind == null ? null : examKind.trim();
    }

    public String getSchoolJob() {
        return schoolJob;
    }

    public void setSchoolJob(String schoolJob) {
        this.schoolJob = schoolJob == null ? null : schoolJob.trim();
    }

    public String getSchoolHonour() {
        return schoolHonour;
    }

    public void setSchoolHonour(String schoolHonour) {
        this.schoolHonour = schoolHonour == null ? null : schoolHonour.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus == null ? null : authStatus.trim();
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getSchoolNameSecret() {
        return schoolNameSecret;
    }

    public void setSchoolNameSecret(Long schoolNameSecret) {
        this.schoolNameSecret = schoolNameSecret;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }
}