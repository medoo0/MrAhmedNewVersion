package com.example.mohamedraslan.hossamexams.Contracts;

import com.example.mohamedraslan.hossamexams.JsonModel.PermissionUserEntering;

import java.util.List;

public interface RequestFromStudentToExamWhatContract  {


    interface MainView{

        void Studenssssss(List<PermissionUserEntering> list);
        void notFoundAnythings();

        void refreshFragment();

    }


    interface MainModel{

        void getStudents(String examID);
    }

    interface  MainPresnter{
        void tellModeltoGetStudents(String examID);
        void studentExisits(List<PermissionUserEntering>list);
        void studentNotExisits();

    }
}
