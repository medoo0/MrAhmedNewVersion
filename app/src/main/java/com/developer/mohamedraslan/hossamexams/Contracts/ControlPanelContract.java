package com.developer.mohamedraslan.hossamexams.Contracts;


import android.widget.ProgressBar;

import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.developer.mohamedraslan.hossamexams.JsonModel.WorngQestion;

import java.util.ArrayList;

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

        void UserTools();


        void showRequestsFromStudent(String examID,String what,String nameExam);







        // this method to hide setElevation to actionbar

        void setElevationZero();
        void showStudentDepartmentFragment();
        void showDialogToAddDeps();

        void showSnackBar(String message);

        void storeDeps(String parent , String child);

         //  هذا الجزء خاص باضافه الفرق الدراسيه



        void showYearsDetails(String depsYear);


        void DoneYearAddesSussessfully();
        void NotDoneThereAreProblemWithConnection();



        void yearAleradyExisitNoAdding(String parent , String childYear);
        void yearNotExistAddThisYearNow(String parent , String childYear);
        void yearP();



        void notificationMessages(String message, ProgressBar p1,ProgressBar p2);


        void showingresults();



        void tokenSussessfullystored();
        void problemwithtoken();
        void tokenisExisitinFirebase();
        void tokennotExisitinFirebase();


        void userAreDeletedSussess();
        void problemwithDeleteUser();
    }



    interface ControlPresnterUI{

        void updateUitoViews();
        void CheckifUserBanned(String Uid);
        void CheckifUserBannedResult(String Result);
        void CheckifAdmin(String uid);
        void HeIsAdmin();
        void HeIsUser();
        void getuserName(String uid);
        void SetUsername(String nameStudent);



        //  هذا الجزء خاص باضافه الفرق الدراسيه

        void tellModeltoCheckIfYearExisitOrNot(String parent , String childYear);
        void tellUI_yearisAlreadyExisit(String parent , String childYear);
        void tellUI_yearNotExisit      (String parent , String childYear);
        void probYearsP();


        void tellModeltoAddYearQuickly(String parent , String childYear);
        void tellUIYearDoneAdded();
        void tellUIYearNotAdded();








        void tellModeltoDeleteUser(String uID);
        void userDeleted();
        void problemUserNotDeleted();


        void tellModeltostoreToken(String token);

        void tellUitokenStored();

        void tellUitokenDosentStore();


        void tellmodelAretokenExisitorNot();
        void tellUItokenisExisit();
        void tellUItokennotExisit();








    }



    interface ControlModelUI{


        void CheckifUserBanned(String Uid);
        void CheckifAdmin(String Uid);
        void getuserName(String uid);
        void removeUserFromAuth(String uID);
        void removedetailsforUser(String uID);



        void storingTokentoDatabase(String token);

        void checkifTokenExisitorNot();



        //  هذا الجزء خاص باضافه الفرق الدراسيه

        void checkIFYearExisitOrNot(String parent , String childYear);
        void addYearTodatabase(String parent , String childYear);


    }

}
