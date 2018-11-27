package com.am.mohamedraslan.hossamexams.MainPresnter;

import com.am.mohamedraslan.hossamexams.Contracts.RequestFromStudentToExamWhatContract;
import com.am.mohamedraslan.hossamexams.JsonModel.PermissionUserEntering;
import com.am.mohamedraslan.hossamexams.MainModle.RequestFromStudentToExamWhatModel;

import java.util.List;

public class RequestFromStudentToExamWhatPresnter implements RequestFromStudentToExamWhatContract.MainPresnter {

    RequestFromStudentToExamWhatModel requestFromStudentToExamWhatModel;
    RequestFromStudentToExamWhatContract.MainView views;

    public RequestFromStudentToExamWhatPresnter(RequestFromStudentToExamWhatContract.MainView views) {
        this.views = views;
        requestFromStudentToExamWhatModel = new RequestFromStudentToExamWhatModel(this);
    }

    @Override
    public void tellModeltoGetStudents(String examID) {
        requestFromStudentToExamWhatModel.getStudents(examID);
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
