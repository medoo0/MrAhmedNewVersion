package com.example.mohamedraslan.hossamexams.Contracts;

import android.widget.ImageView;

import com.example.mohamedraslan.hossamexams.JsonModel.Permission_Refrence;

import java.util.List;

public interface PermissionExamsContract {


    interface  viewMain{

        void allRefrenceHere(List<Permission_Refrence>list);
        void noRefrenceHere();
        void ApplicationForExams(String examID,String nameExam);


    }


    interface PermissionPresnter{
        void tellModeltoGetRefrence();
        void refrenceExisit(List<Permission_Refrence>list);
        void refrenceNotExisit();


    }




    interface PermissionModel{

        void getRequestsExams();

    }

}
