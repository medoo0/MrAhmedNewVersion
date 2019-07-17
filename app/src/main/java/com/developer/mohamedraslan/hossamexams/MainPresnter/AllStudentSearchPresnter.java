package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.AllDetailsStudentSearchContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.developer.mohamedraslan.hossamexams.MainModle.AllStudentSerchModel;

import java.util.List;

public class AllStudentSearchPresnter implements AllDetailsStudentSearchContract.AllStudentPresnter {



    private AllDetailsStudentSearchContract.AllStudentSerachUI allStudentSerachUI;
    private AllStudentSerchModel model;

    public AllStudentSearchPresnter(AllDetailsStudentSearchContract.AllStudentSerachUI allStudentSerachUI) {
        this.allStudentSerachUI = allStudentSerachUI;
        model                   = new AllStudentSerchModel(this);
    }

    @Override
    public void tellModeltoGetResult(String StudenID, String depName, String yearName) {
        model.getResultsinAllUnits(StudenID,depName,yearName);

    }

    @Override
    public void resultExisit(List<Result_Pojo> list) {

        allStudentSerachUI.ResultHere(list);

    }

    @Override
    public void thisStudentDosnthaveAnyResult() {

        allStudentSerachUI.notResultFound();

    }

    @Override
    public void probleming(String error) {
        allStudentSerachUI.problems(error);

    }
}
