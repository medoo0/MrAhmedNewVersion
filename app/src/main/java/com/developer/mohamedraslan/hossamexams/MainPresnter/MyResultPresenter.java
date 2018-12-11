package com.developer.mohamedraslan.hossamexams.MainPresnter;


import com.developer.mohamedraslan.hossamexams.Contracts.MyResultContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.developer.mohamedraslan.hossamexams.MainModle.MyResultModel;

import java.util.List;

/**
 * Created by microprocess on 2018-10-18.
 */

public class MyResultPresenter implements MyResultContract.presenter {
    MyResultContract.view view;
    MyResultContract.model model;
    public MyResultPresenter(MyResultContract.view view) {
        this.view = view;
        model = new MyResultModel(this);
    }

    @Override
    public void getMyResults(String uid,String depName , String yearName , String unitName) {


        model.getMyResults(uid,depName,yearName,unitName);

    }

    @Override
    public void Problem(String s) {
        view.Problem(s);
    }

    @Override
    public void ConfigRecycler(List<Result_Pojo> result) {
        view.ConfigRecycler(result);
    }
}
