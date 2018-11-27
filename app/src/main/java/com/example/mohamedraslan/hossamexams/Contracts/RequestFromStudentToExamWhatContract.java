package com.example.mohamedraslan.hossamexams.Contracts;

import com.example.mohamedraslan.hossamexams.JsonModel.PermissionUserEntering;

import java.util.List;

public interface RequestFromStudentToExamWhatContract  {


    interface MainView{

        void Studenssssss(List<PermissionUserEntering> list);
        void notFoundAnythings();

        void refreshFragment(String nameExam);


        void numberStu(int number);

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
