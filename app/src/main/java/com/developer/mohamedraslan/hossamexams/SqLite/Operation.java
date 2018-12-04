package com.developer.mohamedraslan.hossamexams.SqLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Operation {



    public static boolean storingEmails(SQLiteDatabase dp, String emails){

        ContentValues row = new ContentValues();
        row.put("COL1",emails);
        long result = dp.insert("Email",null,row);

        if(result==-1){

            return false;

        }else
            return true;

    }



    public static Cursor getData(SQLiteDatabase dp){


        return dp.rawQuery("SELECT * FROM  Email  " ,null);

    }


    public static boolean AreExisitEmail(SQLiteDatabase dp,String email){


       Cursor pointer =  dp.rawQuery("SELECT COL1 FROM Email where COL1 = '"+email+"' ",null);

       if (pointer.getCount()>0){

           return true;
       }else


           return false;


    }


}
