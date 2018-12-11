package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.NotificationdialogContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.developer.mohamedraslan.hossamexams.MainModle.NotificationModel;

import java.util.List;

public class Notificationpresnter implements NotificationdialogContract.NotificationDialogPresnter {


    NotificationdialogContract.NotificationdialogUI notificationdialogUI;
    NotificationModel notificationModel;

    public Notificationpresnter(NotificationdialogContract.NotificationdialogUI notificationdialogUI) {
        this.notificationdialogUI = notificationdialogUI;
        notificationModel         = new NotificationModel(this);
    }

    @Override
    public void tellModeltogetYearstoDep(String depName) {

        notificationModel.getYearsFromServer(depName);

    }

    @Override
    public void myYearHereGivetoSpineer(List<Year_modle_json> list) {

        notificationdialogUI.MyYearsHere(list);

    }

    @Override
    public void myYearNotFoundemptySpinner() {

        notificationdialogUI.MyyearNotFound();

    }
}
