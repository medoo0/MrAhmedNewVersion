package com.example.mohamedraslan.hossamexams.JsonModel;

public class Permission_Refrence {


    private String examID ;
    private String examName;

    public Permission_Refrence(String examID, String examName) {
        this.examID = examID;
        this.examName = examName;
    }

    public Permission_Refrence(){

    }

    public String getExamID() {
        return examID;
    }

    public String getExamName() {
        return examName;
    }
}
