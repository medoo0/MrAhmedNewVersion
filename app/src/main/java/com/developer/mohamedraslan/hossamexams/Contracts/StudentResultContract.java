package com.developer.mohamedraslan.hossamexams.Contracts;

import android.widget.ImageView;

import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.developer.mohamedraslan.hossamexams.JsonModel.WorngQestion;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public interface StudentResultContract {



    interface MainView{

        void myResultResult(List<Result_Pojo> list,ImageView imageView);
        void noDataHere(ImageView imageView);
        void problemss(String Error);

        void  numberResult(int number);

        void showErrorsFragment(String name , String finalDegree , String total, String examID , ArrayList<WorngQestion> arrayList, Integer imageTag, String uID, CircleImageView imageView);


    }


    interface MainPresnter{

        void tellModeltoGetDataResults(DatabaseReference reference , String ExamID , ImageView imageView,String depName , String yearName  , String unitName);
        void resulthereupdateview(List<Result_Pojo>list,ImageView imageView);
        void noDataExisit(ImageView imageView);
        void ProblemInternet(String E);

    }


    interface MainModel{


        void getResultsFromFirebase(DatabaseReference reference, String ExamID,ImageView background1,String depName , String yearName  , String unitName);

    }
}
