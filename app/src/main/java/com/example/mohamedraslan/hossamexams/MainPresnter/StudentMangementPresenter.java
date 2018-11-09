package com.example.mohamedraslan.hossamexams.MainPresnter;

import com.example.mohamedraslan.hossamexams.Contracts.StudentManagementContract;
import com.example.mohamedraslan.hossamexams.Fragment.StudentManagement;
import com.example.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.example.mohamedraslan.hossamexams.MainModle.StudentManagementModel;

import java.util.List;

/**
 * Created by microprocess on 2018-10-01.
 */

public class StudentMangementPresenter implements StudentManagementContract.presenter {

    private StudentManagementContract.view view;
    private StudentManagementContract.model model;

    public StudentMangementPresenter(StudentManagement studentManagement) {
        this.view = studentManagement;
        model = new StudentManagementModel(this);
    }

    @Override
    public void callStudentData() {
        model.getstudentData();
    }

    @Override
    public void SendListToView(List<FullRegisterForm> Result) {

        view.RecyclerConfig(Result);

    }

    @Override
    public void problem(String problem) {
        view.problem(problem);
    }
}
