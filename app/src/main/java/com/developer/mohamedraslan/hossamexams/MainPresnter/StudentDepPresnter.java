package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.Student_Department_Contract;
import com.developer.mohamedraslan.hossamexams.MainModle.StudentDepModel;

import java.util.List;

public class StudentDepPresnter implements Student_Department_Contract.PresnterStudentDep {


    Student_Department_Contract.mainView mainView;
    StudentDepModel studentDepModel;

    public StudentDepPresnter(Student_Department_Contract.mainView mainView) {
        this.mainView = mainView;
        studentDepModel = new StudentDepModel(this);
    }

    @Override
    public void tellModeltoGetDeps() {

        studentDepModel.getDepsRefrences();

    }

    @Override
    public void updateUIbyArrayDeps(List<String> list) {
        mainView.depsHere(list);

    }

    @Override
    public void problemswithRefrence(String error) {

        mainView.problems(error);

    }
}
