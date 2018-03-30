package com.fkapi.datebase.domain;

import java.util.Date;

public class py_education {
    private Integer id;

    private Long custId;

    private String reqName;

    private String reqDocumentNo;

    private String college;

    private String levelNo;

    private String startTime;

    private String graduateTime;

    private String studyStyle;

    private String studyType;

    private String specialty;

    private String isKeySubject;

    private String degree;

    private String studyResult;
    
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName == null ? null : reqName.trim();
    }

    public String getReqDocumentNo() {
        return reqDocumentNo;
    }

    public void setReqDocumentNo(String reqDocumentNo) {
        this.reqDocumentNo = reqDocumentNo == null ? null : reqDocumentNo.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo == null ? null : levelNo.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getGraduateTime() {
        return graduateTime;
    }

    public void setGraduateTime(String graduateTime) {
        this.graduateTime = graduateTime == null ? null : graduateTime.trim();
    }

    public String getStudyStyle() {
        return studyStyle;
    }

    public void setStudyStyle(String studyStyle) {
        this.studyStyle = studyStyle == null ? null : studyStyle.trim();
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType == null ? null : studyType.trim();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    public String getIsKeySubject() {
        return isKeySubject;
    }

    public void setIsKeySubject(String isKeySubject) {
        this.isKeySubject = isKeySubject == null ? null : isKeySubject.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getStudyResult() {
        return studyResult;
    }

    public void setStudyResult(String studyResult) {
        this.studyResult = studyResult == null ? null : studyResult.trim();
    }

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}