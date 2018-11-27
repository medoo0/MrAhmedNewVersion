package com.am.mohamedraslan.hossamexams.MainPresnter;

import android.widget.ImageView;

import com.am.mohamedraslan.hossamexams.Contracts.StudentResultContract;
import com.am.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.am.mohamedraslan.hossamexams.MainModle.StudentResultModel;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class StudentResultPresnter implements StudentResultContract.MainPresnter{

    StudentResultContract.MainView view;
    StudentResultModel studentResultModel;

    public StudentResultPresnter(StudentResultContract.MainView view) {

        this.view = view;
        studentResultModel       =  new StudentResultModel(this);
    }

    @Override
    public void tellModeltoGetDataResults(DatabaseReference reference, String ExamID, ImageView imageView) {

        studentResultModel.getResultsFromFirebase(reference,ExamID,imageView);

    }

    @Override
    public void resulthereupdateview(List<Result_Pojo> list, ImageView imageView) {

        view.myResultResult(list,imageView);

    }

    @Override
    public void noDataExisit(ImageView imageView) {
        view.noDataHere(imageView);

    }

    @Override
    public void ProblemInternet(String E) {

    }
}
