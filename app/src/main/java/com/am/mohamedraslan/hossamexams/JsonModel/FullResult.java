package com.am.mohamedraslan.hossamexams.JsonModel;

import java.util.ArrayList;

public class FullResult {

    String examID, nameStudent,  examDate,  examName,  finalDegree, total;
    ArrayList<WorngQestion> wrongQuestions;


    public FullResult(String examID, String nameStudent, String examDate, String examName, String finalDegree, String total, ArrayList<WorngQestion> wrongQuestions) {
        this.examID = examID;
        this.nameStudent = nameStudent;
        this.examDate = examDate;
        this.examName = examName;
        this.finalDegree = finalDegree;
        this.total = total;
        this.wrongQuestions = wrongQuestions;
    }


    public String getExamID() {
        return examID;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getExamName() {
        return examName;
    }

    public String getFinalDegree() {
        return finalDegree;
    }

    public String getTotal() {
        return total;
    }

    public ArrayList<WorngQestion> getWrongQuestions() {
        return wrongQuestions;
    }
}
