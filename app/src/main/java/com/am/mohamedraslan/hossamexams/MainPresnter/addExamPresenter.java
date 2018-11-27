package com.am.mohamedraslan.hossamexams.MainPresnter;

import com.am.mohamedraslan.hossamexams.Contracts.addExamContract;
import com.am.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.am.mohamedraslan.hossamexams.JsonModel.Zone;
import com.am.mohamedraslan.hossamexams.MainModle.addExamModel;

import java.util.List;
import java.util.Map;

/**
 * Created by microprocess on 2018-10-05.
 */

public class addExamPresenter implements addExamContract.presenter {

    addExamContract.model model;
    addExamContract.view  view ;

    public addExamPresenter(addExamContract.view view){

        this.view = view ;
        model     = new addExamModel(this);

    }

    @Override
    public void updateUItoDate(Zone zone) {
        view.realtimehere(zone);
    }

    @Override
    public void problemwithTime(String E) {

        view.cantgetRealTime(E);

    }

    @Override
    public void tellModelToGetDate(Map<String, String> map) {
        model.getDateAndTime(map);
    }

    @Override
    public void Problem(String Result) {

        view.Problem(Result);
    }

    @Override
    public void ConfigRecyclerview(List<Questions_Form> Questions) {
        view.ConfigRecyclerview(Questions);
    }

    @Override
    public void CallgetQestionsToRecycleView() {
        model.getQestionsToRecycleView();
    }

    @Override
    public void ClearList() {
        model.ClearList();
    }

    public void passQestionSizeToView(int i){
        view.Update_Questions_size(i);
    }

    @Override
    public void refreshAdapter() {
        view.refreshAdapter();
    }

    @Override
    public void storeExaminDatabase(int hour, int minute, int second, String oneQestionDegree,
                                    String NumberofQestion, String final_degree, List<Questions_Form> questions,
                                    String ExamName, String currentDateandTime, String Questions_size, Integer timestamp) {

        model.storeExaminDatabase(hour,minute,second,oneQestionDegree,
                NumberofQestion,final_degree,questions,ExamName,currentDateandTime,Questions_size,timestamp);

    }

    @Override
    public void Successful_Storing() {
        view.Successful_Storing();
    }
}
