package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.developer.mohamedraslan.hossamexams.JsonModel.Zone;

import java.util.List;
import java.util.Map;

/**
 * Created by microprocess on 2018-10-05.
 */

public interface addExamContract  {

    interface  model {

        void getQestionsToRecycleView(String depName , String yearName , String unitName);
        void ClearList(String depName , String yearName , String unitName);
        void storeExaminDatabase(int hour, int minute, int second,
                                 String oneQestionDegree, String NumberofQestion, String final_degree,
                                 List<Questions_Form> questions, String ExamName, String currentDateandTime, String questions_size, Integer timestamp,String depName , String yearName , String unitName);

        void getDateAndTime(Map<String, String> map);

    }

    interface presenter {


        void updateUItoDate(Zone zone);
        void problemwithTime(String E);
        void tellModelToGetDate(Map<String, String> map);
        void Problem(String Result);
        void ConfigRecyclerview(List<Questions_Form> Questions);
        void CallgetQestionsToRecycleView(String depName , String yearName , String unitName);  /// more Info.....
        void ClearList(String depName , String yearName , String unitName);
        void passQestionSizeToView(int i);
        void refreshAdapter();

        void storeExaminDatabase(int hour, int minute, int second,
                                 String oneQestionDegree, String NumberofQestion, String final_degree,
                                 List<Questions_Form> questions, String ExamName, String currentDateandTime, String Questions_size, Integer timestamp,String depName , String yearName , String unitName);
        void Successful_Storing();
    }

    interface view{
        void realtimehere(Zone zone);
        void cantgetRealTime(String E);
        void ConfigRecyclerview(List<Questions_Form> Questions);
        void Problem(String Result);
        void   Update_Questions_size(int lengh);
        void refreshAdapter();
        void Successful_Storing();
    }
}
