package com.am.mohamedraslan.hossamexams.MainPresnter;

import android.database.sqlite.SQLiteDatabase;


import com.am.mohamedraslan.hossamexams.Contracts.ResultContract;
import com.am.mohamedraslan.hossamexams.JsonModel.WorngQestion;
import com.am.mohamedraslan.hossamexams.MainModle.ResultModel;

import java.util.ArrayList;

/**
 * Created by microprocess on 2018-10-16.
 */

public class ResultPresenter implements ResultContract.presenter {
    ResultContract.view view;
    ResultContract.model model;
    public ResultPresenter(ResultContract.view view) {
        this.view = view;
        model = new ResultModel(this);
    }

    @Override
    public void CountDegree(SQLiteDatabase db, String tableName) {
        model.CountDegree(db,tableName);
    }

    @Override
    public void TotalDegree(int total) {
        view.TotalDegrees(total);
    }

    @Override
    public void getWrongQestions(SQLiteDatabase db, String tableName) {
        model.getWrongQestions(db,tableName);
    }

    @Override
    public void WrongQuestions(ArrayList<WorngQestion> worngQestions) {
        view.WrongQuestions(worngQestions);
    }

    @Override
    public void UploadResult(String examID, String uid, String examDate, String examname, String finalDegree, String total, ArrayList<WorngQestion> worngQestions,String userName) {
        model.UploadResult(examID,uid,examDate,examname,finalDegree,total,worngQestions,userName);
    }

    @Override
    public void UploadSuccessFull(String s) {
            view.UploadSuccessFull(s);
    }

    @Override
    public void ResultUploadFaild(String s) {
        view.ResultUploadFaild(s);
    }
}
