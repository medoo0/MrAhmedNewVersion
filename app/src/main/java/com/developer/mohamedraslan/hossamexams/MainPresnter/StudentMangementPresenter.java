package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.StudentManagementContract;
import com.developer.mohamedraslan.hossamexams.Fragment.StudentManagement;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.MainModle.StudentManagementModel;

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
    public void callStudentData(String depName , String yearName) {
        model.getstudentData(depName,yearName);
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
