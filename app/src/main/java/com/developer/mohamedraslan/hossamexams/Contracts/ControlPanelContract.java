package com.developer.mohamedraslan.hossamexams.Contracts;


import android.widget.ProgressBar;

import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.developer.mohamedraslan.hossamexams.JsonModel.WorngQestion;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public interface ControlPanelContract {

    interface ControlUI{



        void showDialogNotification();





        //  هذا الجزء خاااص بال studentMangment



        void showStudentMangmentFragmenttt(String depName , String yearName);




        ////////////////////////

//  هذا الجزء خااااص بالطلبااااااات بتاعه الطلااااب


      void showRequestStudentFragment(String depName , String yearName , String unitName);






        /////////////////////////////














        ///                 هذا الجزء خاااااااااااااااااص بال  ResultOfStudentOf Units

        void showResultOfStudentInThisUnitFragment (String depName , String yearName  , String unitName) ;






        ///////////////////////////////


void enableDisableDrawer(int mode);
void enableDrawer(int mode);

        //  هذا الجزء خاص بال Myresult

        void showMyresultFragment(String depName , String yearName  , String unitName);




        //////////////////////

        //  هذا الجزء خااص بال   examlist

        void showExamListForUnit(String depName  , String yearname  , String unitName);
        void showAddExamFromExamListWhenClickonFloatActionButton(String depName , String yearname  , String unitName);











        ///////////////////


        void showWrongsforStudent(Result_Pojo result_pojo,String finalD , String TotalDegree);
        void showFragmentWrongs(String name , String finalDegree , String total, String examID , ArrayList<WorngQestion> arrayList, Integer imageTag, String uID, CircleImageView imageView
                ,String depName , String yearname , String unitName);
        void showDialogStudent(String what,String depName , String yearName);
        void initializeViews();
        void whenClickFAB_showFrag(String depName , String yearName , String unitName);
        void CheckifUserBannedResult(String Result);
        void editQuestions(String questionID, String val,String depName , String yearName , String unitName);
        void editSuccessopenBank(String depName , String yearName , String unitName);
        void SetUsername(String nameStudent);
        void AdminTools();

        void UserTools(FullRegisterForm fullRegisterForm);


        void showRequestsFromStudent(String examID,String what,String nameExam,String depName , String yearName , String unitName);




        void showQforUnitiRefere(String depName , String yearName , String unitName);





        void showunitBankQuestion(String depName , String yearName , String unitName);




        // this method to hide setElevation to actionbar

        void setElevationZero();
        void showStudentDepartmentFragment();
        void showDialogToAddDeps();

        void showSnackBar(String message);

        void storeDeps(String parent , String child);

         //  هذا الجزء خاص باضافه الفرق الدراسيه



        void showfragUnites(String depName , String yearName,int what);


        void showYearsDetails(String depsYear,String fragName);


        void DoneYearAddesSussessfully();
        void NotDoneThereAreProblemWithConnection();



        void yearAleradyExisitNoAdding(String parent , String childYear);
        void yearNotExistAddThisYearNow(String parent , String childYear);
        void yearP();



        void notificationMessages(String message, ProgressBar p1,ProgressBar p2,String depNamefromAdmin , String yearNameFromAdmin);


        void showingresults();



        void tokenSussessfullystored();
        void problemwithtoken();
        void tokenisExisitinFirebase();
        void tokennotExisitinFirebase();


        void userAreDeletedSussess();
        void problemwithDeleteUser();




        //  عند مسح السوال من الداتا بيز


        void removedsussesswewillupdateQuestionBankFragment(String depName , String yearName  , String unitName,String what);

    }



    interface ControlPresnterUI{

        void updateUitoViews();
        void CheckifUserBanned(String Uid);
        void CheckifUserBannedResult(String Result);
        void CheckifAdmin(String uid);
        void HeIsAdmin();
        void HeIsUser(FullRegisterForm fullRegisterForm);
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
