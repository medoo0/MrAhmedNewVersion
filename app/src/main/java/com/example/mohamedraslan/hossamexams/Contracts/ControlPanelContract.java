package com.example.mohamedraslan.hossamexams.Contracts;


public interface ControlPanelContract {

    interface ControlUI{
        void initializeViews();
        void whenClickFAB_showFrag();
        void CheckifUserBannedResult(String Result);
        void editQuestions(String questionID, String val);
        void editSuccessopenBank();
        void SetUsername(String nameStudent);
        void AdminTools();
    }



    interface ControlPresnterUI{

        void updateUitoViews();
        void CheckifUserBanned(String Uid);
        void CheckifUserBannedResult(String Result);
        void CheckifAdmin(String uid);
        void HeIsAdmin();
        void getuserName(String uid);
        void SetUsername(String nameStudent);
    }



    interface ControlModelUI{


        void CheckifUserBanned(String Uid);
        void CheckifAdmin(String Uid);
        void getuserName(String uid);
    }

}
