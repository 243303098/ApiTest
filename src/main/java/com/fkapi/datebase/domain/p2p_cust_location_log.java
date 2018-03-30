package com.fkapi.datebase.domain;

import java.util.Date;

public class p2p_cust_location_log {
    private Long id;

    private Long custId;

    private String ip;

    private String mobileSign;

    private String mobileOs;

    private String longitude;

    private String latitude;

    private String locational;

    private String cityCode;

    private String deviceModel;

    private String deviceVersion;

    private String devicePhoneNum;

    private String deviceSim;

    private String deviceMac;

    private String appVersion;

    private Date createTime;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMobileSign() {
        return mobileSign;
    }

    public void setMobileSign(String mobileSign) {
        this.mobileSign = mobileSign == null ? null : mobileSign.trim();
    }

    public String getMobileOs() {
        return mobileOs;
    }

    public void setMobileOs(String mobileOs) {
        this.mobileOs = mobileOs == null ? null : mobileOs.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLocational() {
        return locational;
    }

    public void setLocational(String locational) {
        this.locational = locational == null ? null : locational.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion == null ? null : deviceVersion.trim();
    }

    public String getDevicePhoneNum() {
        return devicePhoneNum;
    }

    public void setDevicePhoneNum(String devicePhoneNum) {
        this.devicePhoneNum = devicePhoneNum == null ? null : devicePhoneNum.trim();
    }

    public String getDeviceSim() {
        return deviceSim;
    }

    public void setDeviceSim(String deviceSim) {
        this.deviceSim = deviceSim == null ? null : deviceSim.trim();
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac == null ? null : deviceMac.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}