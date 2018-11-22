package com.example.mohamedraslan.hossamexams.Enums;

public enum DataBase_Refrences {


    USERREF("Users"),Permissions("Permissions"),BLOCKUSER("Blocked_User"),BANKQUESTIONS("Banck_Questions"),CHOSENQUESTIONID("Chosen_questions_ID")
    ,EXAMS("Exams"),ADMIN("Admins"),TimeApiKey("KXG6INZZU6EO"),Format("json"),STARTEDEXAM("ExamStarted"),CountryNum("335")
    ,RESULT("Result"),ResultsRef("Results_References");



    private String val;

    private DataBase_Refrences(String val) {

        this.val = val;
    }

    public String getRef(){

        return this.val;

    }

    public int getINTref(){

        return Integer.parseInt(this.val);

    }
}
