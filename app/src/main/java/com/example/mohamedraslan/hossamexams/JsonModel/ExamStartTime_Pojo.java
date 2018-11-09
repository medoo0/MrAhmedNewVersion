package com.example.mohamedraslan.hossamexams.JsonModel;

/**
 * Created by microprocess on 2018-10-16.
 */

public class ExamStartTime_Pojo  {

    private String startTime , endTime ;

    public ExamStartTime_Pojo(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ExamStartTime_Pojo() {
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
