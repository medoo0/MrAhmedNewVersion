package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;

import java.util.List;

/**
 * Created by microprocess on 2018-10-18.
 */

public interface MyResultContract {
    interface view {

        void ConfigRecycler(List<Result_Pojo> result);
        void showAtherFragment(Result_Pojo result_pojo,String FinalD , String TotalD);

        void Problem(String s);


        void numberofExamsForMe(int number);
    }
    interface presenter {

        void getMyResults(String uid,String depName , String yearName , String unitName);

        void Problem(String s);

        void ConfigRecycler(List<Result_Pojo> result);
    }
    interface model {

        void getMyResults(String uid,String depName , String yearName , String unitName);
    }
}
