package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.PermissionUserEntering;

import java.util.List;

public interface RequestFromStudentToExamWhatContract  {


    interface MainView{

        void Studenssssss(List<PermissionUserEntering> list);
        void notFoundAnythings();

        void refreshFragment(String nameExam);


        void numberStu(int number);

    }


    interface MainModel{

        void getStudents(String examID,String depName , String yearName , String unitName);
    }

    interface  MainPresnter{
        void tellModeltoGetStudents(String examID,String depName , String yearName , String unitName);
        void studentExisits(List<PermissionUserEntering>list);
        void studentNotExisits();

    }
}
