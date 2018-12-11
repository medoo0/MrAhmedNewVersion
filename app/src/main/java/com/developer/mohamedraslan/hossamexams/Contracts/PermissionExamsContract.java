package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Permission_Refrence;

import java.util.List;

public interface PermissionExamsContract {


    interface  viewMain{

        void allRefrenceHere(List<Permission_Refrence>list);
        void noRefrenceHere();
        void ApplicationForExams(String examID,String nameExam,String depName , String yearName , String unitName);


        void numberExams(int numbers);


    }


    interface PermissionPresnter{

        void tellModeltoGetRefrence(String depName , String yearName , String unitName);
        void refrenceExisit(List<Permission_Refrence>list);
        void refrenceNotExisit();


    }




    interface PermissionModel{

        void getRequestsExams(String depName , String yearName , String unitName);

    }

}
