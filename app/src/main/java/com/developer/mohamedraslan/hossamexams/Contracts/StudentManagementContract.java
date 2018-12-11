package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;

import java.util.List;

/**
 * Created by microprocess on 2018-10-01.
 */

public interface StudentManagementContract {
    interface model{

        void getstudentData(String depName , String yearName);


    }
    interface presenter{
        void callStudentData(String depName , String yearName);
        void SendListToView(List<FullRegisterForm> Result);
        void problem(String problem);

    }
    interface view {
        void RecyclerConfig(List<FullRegisterForm> Result);
        void problem(String problem);
        void numberStudent(int number);

    }
}
