package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.google.firebase.database.DatabaseReference;

public interface AddQuestionContract {


    interface AddQUI{

        void dataSaved();
        void problem(String E);
        void questionHere(Questions_Form questions_form);
        void problem_notHere(String error);
        void dataEditedsussess(String depName , String yearName , String unitName);
        void datanotEditedfailed(String E);


    }
    interface AddQPresnter{
        void tellModletoGetQuestion(DatabaseReference reference, AddQuestionContract.AddQUI addQUI, String qID,String depName , String yearName, String unitName);
        void updateUIDataSavedSuccessfull();
        void updatUiDataNotSaved(String E);
        void update_modle_to_editQuestion(DatabaseReference reference, AddQuestionContract.AddQUI addQUI, String qID, Questions_Form questions_form,String depName , String yearName, String unitName);

        void dataedited(String depName , String yearName , String unitName);
        void dataNotEdited();

        void updateModelToSaveData(DatabaseReference reference, Questions_Form questions_form,String depName , String yearName, String unitName);
        void updateUitoQuestion(Questions_Form questions_form);
        void problemOcurrwhengetQ(String E);

    }
    interface AddQModel{

        void upload_Questions_toServer(DatabaseReference reference, AddQuestionContract.AddQPresnter addQPresnter, Questions_Form questions_form,String depName , String yearName, String unitName);
        void getQuestionDetails(DatabaseReference reference, AddQuestionContract.AddQPresnter addQPresnter, String qID,String depName , String yearName, String unitName);
        void editingQuestion(DatabaseReference reference, final AddQuestionContract.AddQPresnter addQPresnter, String QID, Questions_Form questions_form,String depName , String yearName, String unitName);

    }

}
