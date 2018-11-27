package com.am.mohamedraslan.hossamexams.MainPresnter;

import com.am.mohamedraslan.hossamexams.Contracts.ExamListContract;
import com.am.mohamedraslan.hossamexams.MainModle.ExamListModel;

/**
 * Created by microprocess on 2018-10-09.
 */

public class ExamListPresenter implements ExamListContract.presenter {
    ExamListContract.view View;
    ExamListContract.model model;
    public ExamListPresenter(ExamListContract.view view) {
        this.View = view;
        model = new ExamListModel(this);
    }

    @Override
    public void CheckifAdmin(String Uid) {

        model.CheckifAdmin(Uid);

    }

    @Override
    public void ShowAdminTools() {

        View.ShowAdminTools();

    }

    @Override
    public void PassRealTimeFromServerToView(String date) {
        View.ConfigRecyceler(date);
    }

    @Override
    public void GetTime() {
        model.getDateAndTime();
    }
}
