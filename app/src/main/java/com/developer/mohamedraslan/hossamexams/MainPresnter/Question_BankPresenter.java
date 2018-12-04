package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.QuestionsBankContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.developer.mohamedraslan.hossamexams.MainModle.Question_BankModel;
import com.google.firebase.database.DatabaseReference;

import java.util.List;


public class Question_BankPresenter implements QuestionsBankContract.presenter {
    QuestionsBankContract.view view;
    QuestionsBankContract.model model;
    public Question_BankPresenter(QuestionsBankContract.view  view){
        this.view = view ;
        model = new Question_BankModel(this);
    }



    @Override
    public void Qremoved(int position) {

        view.Q_Removed_InUI(position);

    }

    @Override
    public void Q_notRemoved_checking() {
        view.Q_notRemoved_InUI();

    }

    @Override
    public void tellModletoDeleteQuestion(DatabaseReference reference, String Qid, int position) {

        model.removingQuestionFromDatabase(reference,Qid,this, position);

    }

    @Override
    public void callQuestionData() {

        model.getQuestionData();
    }

    @Override
    public void SendListToView(List<Questions_Form> Result) {
        view.RecyclerConfig(Result);
    }

    @Override
    public void problem(String problem) {
        view.problem(problem);
    }



    @Override
    public void addQuestionToAddTestRecycler(String questionID) {

        model.addQuestionToAddTestRecycler(questionID);

    }

    @Override
    public void sentSuccessfully(String Result) {
            view.sentSuccessfully(Result);
    }
}
