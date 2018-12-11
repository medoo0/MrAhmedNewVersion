package com.developer.mohamedraslan.hossamexams.JsonModel;

public class Unites_Model_Json {


    private String unitName;
    private long unitTime;


    public Unites_Model_Json(String unitName, long unitTime) {
        this.unitName = unitName;
        this.unitTime = unitTime;
    }

    public Unites_Model_Json(){}

    public String getUnitName() {
        return unitName;
    }

    public long getUnitTime() {
        return unitTime;
    }
}
