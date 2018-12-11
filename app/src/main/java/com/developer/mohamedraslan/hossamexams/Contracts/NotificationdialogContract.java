package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;

import java.util.List;

public interface NotificationdialogContract {


    interface NotificationDialogPresnter{


        void tellModeltogetYearstoDep(String depName);

        void myYearHereGivetoSpineer(List<Year_modle_json>list);
        void myYearNotFoundemptySpinner();

    }

    interface NotificationDialogModel{

        void getYearsFromServer(String depName);


    }


    interface  NotificationdialogUI{


        void MyYearsHere(List<Year_modle_json>list);
        void MyyearNotFound();

    }


}
