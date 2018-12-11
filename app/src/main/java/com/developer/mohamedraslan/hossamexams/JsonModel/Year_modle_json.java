package com.developer.mohamedraslan.hossamexams.JsonModel;

public class Year_modle_json {



    private String yearName;
    private long timeAdded;


    public Year_modle_json(String yearName, long timeAdded) {
        this.yearName = yearName;
        this.timeAdded = timeAdded;
    }


    public Year_modle_json(){}


    public String getYearName() {
        return yearName;
    }

    public long getTimeAdded() {
        return timeAdded;
    }
}
