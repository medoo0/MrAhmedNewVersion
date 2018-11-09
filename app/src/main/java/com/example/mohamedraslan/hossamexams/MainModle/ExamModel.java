package com.example.mohamedraslan.hossamexams.MainModle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mohamedraslan.hossamexams.Contracts.ExamContract;
import com.example.mohamedraslan.hossamexams.SqLite.SQlHelper;

/**
 * Created by microprocess on 2018-10-14.
 */

public class ExamModel implements ExamContract.model {

    ExamContract.presenter presenter;

    public ExamModel(ExamContract.presenter examPresenter) {
        presenter = examPresenter;
    }

    @Override
    public void getQuestion(SQLiteDatabase db, String sqlTableName) {

        String[] Cols = {SQlHelper.ID_Qestion, SQlHelper.question, SQlHelper.answerOne
                , SQlHelper.answerTwo, SQlHelper.answerThree
                , SQlHelper.answerFour, SQlHelper.correctAnswer, SQlHelper.Student_Answer};

        //Check if table Exists .
        if (CheckifTableExsist(db, sqlTableName)) {
            Cursor Pointer = db.query(sqlTableName, Cols, SQlHelper.Student_Answer + " = ?", new String[]{""}, null, null, null);

            if (Pointer.moveToNext()) {
                presenter.quetionIs(Pointer.getString(0), Pointer.getString(1), Pointer.getString(2), Pointer.getString(3)
                        , Pointer.getString(4), Pointer.getString(5), Pointer.getString(6));
            } else {

                presenter.ExamEnd(" لقد انهيت الاختبار . ");

            }
        } else {
            presenter.BlockScreen("لقد بدأت اختبارك من هاتف اخر او انك قمت بحذف بيانات البرنامج يرجي مراسلة المختص . ");
        }

    }

    @Override
    public void insertAnswerInSql(SQLiteDatabase db, String sqlTableName, String ID_Qestion, String selectAnswer, String oneQestionDegree) {

        ContentValues row = new ContentValues();
        row.put(SQlHelper.Student_Answer, selectAnswer);

        int Updated = db.update(sqlTableName, row, SQlHelper.ID_Qestion + " = ?", new String[]{ID_Qestion});
        if (Updated > 0) { // successful put answer.


            //Add Degree .
            ContentValues row2 = new ContentValues();
            if (getCorrectAnswer(db, sqlTableName, ID_Qestion).equals(selectAnswer)) {
                row2.put(SQlHelper.Degree, oneQestionDegree);
            } else {

                row2.put(SQlHelper.Degree, "0");
            }

            int addDegree = db.update(sqlTableName, row2, SQlHelper.ID_Qestion + " = ?", new String[]{ID_Qestion});

            if (addDegree > 0) {

                presenter.AnswerInserted();
            }

        } else {
            presenter.Problem("لم يتم اضافة الاجابة السابقة");
        }

    }

    @Override
    public String getCorrectAnswer(SQLiteDatabase db, String sqlTableName, String ID_Qestion) {
        String CorrectAnswer = "";
        String[] Cols = {SQlHelper.correctAnswer};

        Cursor Pointer = db.query(sqlTableName, Cols, SQlHelper.ID_Qestion + " = ?", new String[]{ID_Qestion}, null, null, null);

        if (Pointer.moveToNext()) {

            CorrectAnswer = Pointer.getString(0);

        } else {
            // impossible to run as I think .
            presenter.Problem(" الاجابة الصحيحة غير مرفقة . ");

        }

        return CorrectAnswer;
    }

    @Override
    public void skip(SQLiteDatabase db, String sqlTableName, String ID_Qestion) {
        String s0, s1, s2, s3, s4, s5, s6, s7, s8;
        String[] Cols = {SQlHelper.ID_Qestion, SQlHelper.question, SQlHelper.answerOne
                , SQlHelper.answerTwo, SQlHelper.answerThree
                , SQlHelper.answerFour, SQlHelper.correctAnswer, SQlHelper.Student_Answer, SQlHelper.Degree};

        Cursor Pointer = db.query(sqlTableName, Cols, SQlHelper.ID_Qestion + " = ?", new String[]{ID_Qestion}, null, null, null);

        if (Pointer.moveToNext()) {

            s0 = Pointer.getString(0);
            s1 = Pointer.getString(1);
            s2 = Pointer.getString(2);
            s3 = Pointer.getString(3);
            s4 = Pointer.getString(4);
            s5 = Pointer.getString(5);
            s6 = Pointer.getString(6);
            s7 = Pointer.getString(7);
            s8 = Pointer.getString(8);
            if (deleteRow(db, sqlTableName, ID_Qestion)) {

                //successful Skip.
                ContentValues row = new ContentValues();
                row.put(SQlHelper.ID_Qestion, s0);
                row.put(SQlHelper.question, s1);
                row.put(SQlHelper.answerOne, s2);
                row.put(SQlHelper.answerTwo, s3);
                row.put(SQlHelper.answerThree, s4);
                row.put(SQlHelper.answerFour, s5);
                row.put(SQlHelper.correctAnswer, s6);
                row.put(SQlHelper.Student_Answer, s7);
                row.put(SQlHelper.Degree, s8);

                long x = db.insert(sqlTableName, null, row);
                if (x > 0) {

                    presenter.Skiped();

                }

            }
        }

    }

    @Override
    public boolean deleteRow(SQLiteDatabase db, String sqlTableName, String ID_Qestion) {


        int deleted = db.delete(sqlTableName, SQlHelper.ID_Qestion + " = ? ", new String[]{ID_Qestion});
        if (deleted > 0) {

            return true;
        } else {

            return false;
        }


    }

    boolean CheckifTableExsist(SQLiteDatabase db, String TableName) {

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + TableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }




}