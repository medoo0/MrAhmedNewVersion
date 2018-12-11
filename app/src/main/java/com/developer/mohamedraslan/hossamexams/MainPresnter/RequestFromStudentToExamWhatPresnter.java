package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.RequestFromStudentToExamWhatContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.PermissionUserEntering;
import com.developer.mohamedraslan.hossamexams.MainModle.RequestFromStudentToExamWhatModel;

import java.util.List;

public class RequestFromStudentToExamWhatPresnter implements RequestFromStudentToExamWhatContract.MainPresnter {

    RequestFromStudentToExamWhatModel requestFromStudentToExamWhatModel;
    RequestFromStudentToExamWhatContract.MainView views;

    public RequestFromStudentToExamWhatPresnter(RequestFromStudentToExamWhatContract.MainView views) {
        this.views = views;
        requestFromStudentToExamWhatModel = new RequestFromStudentToExamWhatModel(this);
    }

    @Override
    public void tellModeltoGetStudents(String examID,String depName , String yearName , String unitName) {
        requestFromStudentToExamWhatModel.getStudents(examID,depName,yearName,unitName);
    }

    @Override
    public void studentExisits(List<PermissionUserEntering> list) {

        views.Studenssssss(list);

    }

    @Override
    public void studentNotExisits() {

        views.notFoundAnythings();

    }
}
