package com.example.mohamedraslan.hossamexams.Contracts;

import com.example.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;

import java.util.List;

/**
 * Created by microprocess on 2018-10-01.
 */

public interface StudentManagementContract {
    interface model{

        void getstudentData();


    }
    interface presenter{
        void callStudentData();
        void SendListToView(List<FullRegisterForm> Result);
        void problem(String problem);

    }
    interface view {
        void RecyclerConfig(List<FullRegisterForm> Result);
        void problem(String problem);
        void numberStudent(int number);

    }
}
