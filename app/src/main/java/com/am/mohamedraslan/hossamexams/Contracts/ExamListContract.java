package com.am.mohamedraslan.hossamexams.Contracts;


import com.am.mohamedraslan.hossamexams.JsonModel.Zone;

/**
 * Created by microprocess on 2018-10-08.
 */

public interface  ExamListContract  {
    interface view{

        void ShowAdminTools();
        void ConfigRecyceler(String date);

    }
    interface presenter{

        void CheckifAdmin(String Uid);
        void ShowAdminTools();
        void PassRealTimeFromServerToView(String date);

        void GetTime();
    }
    interface model {
        void CheckifAdmin(String Uid);
        void getDateAndTime();
        String getDate(long time_stamp_server);
        void realtimehere(Zone zone);
    }

}
