package com.am.mohamedraslan.hossamexams.Contracts;

import android.database.sqlite.SQLiteDatabase;

import com.am.mohamedraslan.hossamexams.JsonModel.WorngQestion;

import java.util.ArrayList;

/**
 * Created by microprocess on 2018-10-16.
 */

public interface ResultContract {
    interface view {

        void TotalDegrees(int total);

        void WrongQuestions(ArrayList<WorngQestion> worngQestions);

        void UploadSuccessFull(String s);

        void ResultUploadFaild(String s);
    }
    interface presenter {

        void CountDegree(SQLiteDatabase db, String tableName);

        void TotalDegree(int total);

        void getWrongQestions(SQLiteDatabase db, String tableName);

        void WrongQuestions(ArrayList<WorngQestion> worngQestions);

        void UploadResult(String substring, String uid, String examDate, String examname, String finalDegree, String total, ArrayList<WorngQestion> worngQestions,String userName);

        void UploadSuccessFull(String s);

        void ResultUploadFaild(String s);
    }
    interface model {

        void CountDegree(SQLiteDatabase db, String tableName);

        void getWrongQestions(SQLiteDatabase db, String tableName);

        void UploadResult(String examID, String uid, String examDate, String examname, String finalDegree, String total, ArrayList<WorngQestion> worngQestions,String userName);
    }
}
