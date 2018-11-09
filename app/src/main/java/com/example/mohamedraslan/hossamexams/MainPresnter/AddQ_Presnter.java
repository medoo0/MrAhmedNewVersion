package com.example.mohamedraslan.hossamexams.MainPresnter;

import com.example.mohamedraslan.hossamexams.Contracts.AddQuestionContract;
import com.example.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.example.mohamedraslan.hossamexams.MainModle.AddQ_Model;
import com.google.firebase.database.DatabaseReference;

public class AddQ_Presnter implements AddQuestionContract.AddQPresnter {


    AddQuestionContract.AddQUI addQUI;
    AddQ_Model addQ_model ;


    public AddQ_Presnter(AddQuestionContract.AddQUI addQUI) {
        this.addQUI = addQUI;
        addQ_model  = new AddQ_Model();
    }


    @Override
    public void tellModletoGetQuestion(DatabaseReference reference, AddQuestionContract.AddQUI addQUI, String qID) {
        addQ_model.getQuestionDetails(reference,this,qID);
    }

    @Override
    public void updateUIDataSavedSuccessfull() {

        addQUI.dataSaved();
    }

    @Override
    public void updatUiDataNotSaved(String E) {
        addQUI.problem(E);

    }

    @Override
    public void update_modle_to_editQuestion(DatabaseReference reference, AddQuestionContract.AddQUI addQUI, String qID, Questions_Form questions_form) {
        addQ_model.editingQuestion(reference,this,qID,questions_form);
    }

    @Override
    public void dataedited() {
        addQUI.dataEditedsussess();
    }

    @Override
    public void dataNotEdited() {

        addQUI.datanotEditedfailed("Error");
    }


    @Override
    public void updateModelToSaveData(DatabaseReference reference, Questions_Form questions_form) {

        addQ_model.upload_Questions_toServer(reference,this,questions_form);

    }

    @Override
    public void updateUitoQuestion(Questions_Form questions_form) {

        addQUI.questionHere(questions_form);

    }

    @Override
    public void problemOcurrwhengetQ(String E) {

        addQUI.problem_notHere(E);

    }


}
