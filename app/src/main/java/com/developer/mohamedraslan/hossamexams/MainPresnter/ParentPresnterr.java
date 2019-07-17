package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.ParentContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.MainModle.ParentMyModel;

public class ParentPresnterr implements ParentContract.ParentPresnter {

    ParentContract.ParentView parentView;
    ParentMyModel parentMyModel;

    public ParentPresnterr(ParentContract.ParentView parentView) {
        this.parentView = parentView;
        parentMyModel   = new ParentMyModel(this);
    }

    @Override
    public void tellModeltoGetAllDetailsForstudent(String studentCode) {

        parentMyModel.getDetailsForStudentFromFirebase(studentCode);

    }

    @Override
    public void detaisUserHere(FullRegisterForm fullRegisterForm) {
        parentView.StudentdetailsHere(fullRegisterForm);

    }

    @Override
    public void StudentNotFound() {
        parentView.noDetailsFound();

    }

    @Override
    public void getProblemFromModel(String error) {
        parentView.problemOcurr(error);

    }
}
