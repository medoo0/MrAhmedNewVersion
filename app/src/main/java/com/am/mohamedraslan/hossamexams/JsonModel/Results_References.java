package com.am.mohamedraslan.hossamexams.JsonModel;

/**
 * Created by microprocess on 2018-10-19.
 */

public class Results_References   {

    private String examID  , examName , examDate ;

    public Results_References(String examID, String examName, String examDate) {
        this.examID = examID;
        this.examName = examName;
        this.examDate = examDate;
    }

    public Results_References() {
    }

    public String getExamID() {
        return examID;
    }

    public String getExamName() {
        return examName;
    }

    public String getExamDate() {
        return examDate;
    }


}
