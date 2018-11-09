package com.example.mohamedraslan.hossamexams.SqLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by microprocess on 2018-10-13.
 */

public class SQlHelper extends SQLiteOpenHelper {
    public static String DataBase_Name = "Exams";
    private String ExamID = ""; //dynamic
    public static String ID_Qestion = "ID_Qestion";
    public static String question = "question";
    public static String answerOne = "answerOne";
    public static String answerTwo = "answerTwo";
    public static String answerThree = "answerThree";
    public static String answerFour = "answerFour";
    public static String correctAnswer = "correctAnswer";
    public static String Student_Answer = "StudentAnswer";
    public static String Degree = "Degree";

    public SQlHelper(Context context  ) {
        super(context,DataBase_Name,null,4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        sqLiteDatabase.execSQL("CREATE TABLE " + ExamID + " ( "
//                + ID_Qestion + " TEXT ,"
//                + question + " TEXT ,"
//                + answerOne + " TEXT ,"
//                + answerTwo + " TEXT ,"
//                + answerThree + " TEXT ,"
//                + answerFour + " TEXT ,"
//                + correctAnswer + " TEXT ,"
//                + Student_Answer + " TEXT "
//                +" ) "
//        );
    }

    public void createExamTable(String ExamID) {
        final SQLiteDatabase db = getWritableDatabase();

        db.execSQL("create table if not exists " + ExamID + " ( "
                + ID_Qestion + " TEXT ,"
                + question + " TEXT ,"
                + answerOne + " TEXT ,"
                + answerTwo + " TEXT ,"
                + answerThree + " TEXT ,"
                + answerFour + " TEXT ,"
                + correctAnswer + " TEXT ,"
                + Student_Answer + " TEXT ,"
                + Degree + " TEXT "
                + " ) "
        );


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ ExamID + "");
        onCreate(sqLiteDatabase);
    }
}