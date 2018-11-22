package com.example.mohamedraslan.hossamexams.Contracts;


import com.example.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.example.mohamedraslan.hossamexams.JsonModel.WorngQestion;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public interface ControlPanelContract {

    interface ControlUI{

        void showWrongsforStudent(Result_Pojo result_pojo,String finalD , String TotalDegree);
        void showFragmentWrongs(String name , String finalDegree , String total, String examID , ArrayList<WorngQestion> arrayList, Integer imageTag, String uID, CircleImageView imageView);
        void showDialogStudent(String what);
        void initializeViews();
        void whenClickFAB_showFrag();
        void CheckifUserBannedResult(String Result);
        void editQuestions(String questionID, String val);
        void editSuccessopenBank();
        void SetUsername(String nameStudent);
        void AdminTools();


        void showRequestsFromStudent(String examID,String what);

        void showingresults();



        void userAreDeletedSussess();
        void problemwithDeleteUser();
    }



    interface ControlPresnterUI{

        void updateUitoViews();
        void CheckifUserBanned(String Uid);
        void CheckifUserBannedResult(String Result);
        void CheckifAdmin(String uid);
        void HeIsAdmin();
        void getuserName(String uid);
        void SetUsername(String nameStudent);



        void tellModeltoDeleteUser(String uID);
        void userDeleted();
        void problemUserNotDeleted();




    }



    interface ControlModelUI{


        void CheckifUserBanned(String Uid);
        void CheckifAdmin(String Uid);
        void getuserName(String uid);
        void removeUserFromAuth(String uID);
        void removedetailsforUser(String uID);

    }

}
