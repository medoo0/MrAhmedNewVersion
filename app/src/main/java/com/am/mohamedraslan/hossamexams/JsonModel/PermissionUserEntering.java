package com.am.mohamedraslan.hossamexams.JsonModel;

public class PermissionUserEntering {


    String uID;
    String studentNanme;

    public PermissionUserEntering(String uID, String studentNanme) {
        this.uID = uID;
        this.studentNanme = studentNanme;

    }


    public PermissionUserEntering(){

    }
    public String getuID() {
        return uID;
    }

    public String getStudentNanme() {
        return studentNanme;
    }

}
