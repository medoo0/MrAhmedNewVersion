package com.example.mohamedraslan.hossamexams.Contracts;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by microprocess on 2018-10-14.
 */

public interface ExamContract {
    interface view {

        void quetionIs(String ID_Qestion, String question, String answerOne, String answerTwo, String answerThree, String answerFour, String correctAnswer);

        void Problem(String s);

        void AnswerInserted();

        void Skipped();

        void ExamEnd(String s);

        void BlockScreen(String s);
    }
    interface presenter{

        void getQuestion(SQLiteDatabase db, String sqlTableName);

        void quetionIs(String ID_Qestion, String question, String answerOne, String answerTwo, String answerThree, String answerFour, String correctAnswer);

        void insertAnswerInSql(SQLiteDatabase db, String sqlTableName, String ID_Qestion, String selectAnswer, String oneQestionDegree);

        void AnswerInserted();

        void Problem(String s);

        void Skiped();

        void Skip(SQLiteDatabase db, String tableName, String id_qestion);

        void ExamEnd(String s);

        void BlockScreen(String s);
    }
    interface model{

        void getQuestion(SQLiteDatabase db, String sqlTableName);

        void insertAnswerInSql(SQLiteDatabase db, String sqlTableName, String ID_Qestion, String selectAnswer, String oneQestionDegree);
        String getCorrectAnswer(SQLiteDatabase db, String sqlTableName, String ID_Qestion);
        boolean deleteRow(SQLiteDatabase db, String sqlTableName, String ID_Qestion);
        void skip(SQLiteDatabase db, String sqlTableName, String ID_Qestion);
    }
}
