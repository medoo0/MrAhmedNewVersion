package com.am.mohamedraslan.hossamexams.JsonModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zone {


    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("zoneName")
    @Expose
    private String zoneName;
    @SerializedName("gmtOffset")
    @Expose
    private Integer gmtOffset;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(Integer gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

}
