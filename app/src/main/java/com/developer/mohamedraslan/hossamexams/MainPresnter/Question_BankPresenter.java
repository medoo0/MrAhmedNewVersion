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
    public void Qremoved(int position, String depName , String yearName , String unitName) {

        view.Q_Removed_InUI(position,depName,yearName,unitName);

    }

    @Override
    public void Q_notRemoved_checking( String depName , String yearName , String unitName) {
        view.Q_notRemoved_InUI(depName,yearName,unitName);

    }



    @Override
    public void tellModletoDeleteQuestion(DatabaseReference reference, String Qid, int position, String depName , String yearName , String unitName) {

        model.removingQuestionFromDatabase(reference,Qid,this, position,depName,yearName,unitName);

    }

    @Override
    public void callQuestionData(String depName , String yearName , String unitName) {

        model.getQuestionData(depName,yearName,unitName);
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
    public void addQuestionToAddTestRecycler(String questionID,String depName , String yearName ,String unitName) {      //  add depNAME and yearName and unitName

        model.addQuestionToAddTestRecycler(questionID,depName,yearName,unitName);

    }

    @Override
    public void sentSuccessfully(String Result) {
            view.sentSuccessfully(Result);
    }







    @Override
    public void tellModeltoRemoveAllQuestions(String depName, String yearName, String unitName) {

        model.removallQinTheUnit(depName,yearName,unitName);
    }

    @Override
    public void tellUIallQuestionRemoved(String depName, String yearName, String unitName) {

        view.allQuestioninUnitRemoved(depName,yearName,unitName);

    }

    @Override
    public void tellUiallQuestionNotRemoved(String depName, String yearName, String unitName) {

        view.allQuestioninUnitNotRemoved(depName,yearName,unitName);
    }




}
