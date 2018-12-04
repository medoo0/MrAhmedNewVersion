package com.developer.mohamedraslan.hossamexams.MainPresnter;

import android.database.sqlite.SQLiteDatabase;

import com.developer.mohamedraslan.hossamexams.Contracts.ExamContract;
import com.developer.mohamedraslan.hossamexams.MainModle.ExamModel;


/**
 * Created by microprocess on 2018-10-14.
 */

public class ExamPresenter implements ExamContract.presenter {
    ExamContract.view view;
    ExamContract.model model;
    public ExamPresenter(ExamContract.view view){
        this.view = view;
        model = new ExamModel(this);
    }


    @Override
    public void getQuestion(SQLiteDatabase db, String sqlTableName) {
        model.getQuestion(db,sqlTableName);
    }

    @Override
    public void quetionIs(String string, String string1, String string2, String string3, String string4, String string5, String string6) {
        view.quetionIs(string,string1,string2,string3,string4,string5,string6);
    }

    @Override
    public void insertAnswerInSql(SQLiteDatabase db, String sqlTableName, String ID_Qestion, String selectAnswer , String oneQestionDegree) {
        model.insertAnswerInSql(db,sqlTableName,ID_Qestion,selectAnswer , oneQestionDegree);
    }

    @Override
    public void AnswerInserted() {
        view.AnswerInserted();
    }

    @Override
    public void Problem(String s) {
        view.Problem(s);
    }

    @Override
    public void Skiped() {

        view.Skipped();

    }

    @Override
    public void Skip(SQLiteDatabase db, String tableName, String id_qestion) {

        model.skip(db,tableName,id_qestion);

    }

    @Override
    public void ExamEnd(String s) {
        view.ExamEnd(s);
    }

    @Override
    public void BlockScreen(String s) {
        view.BlockScreen(s);
    }
}
