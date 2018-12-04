package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.PermissionExamsContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Permission_Refrence;
import com.developer.mohamedraslan.hossamexams.MainModle.PermissionExamModel;

import java.util.List;

public class PermissionExamPresnter implements PermissionExamsContract.PermissionPresnter {

    PermissionExamsContract.viewMain viewMain;
    PermissionExamModel permissionExamModel;


    public PermissionExamPresnter(PermissionExamsContract.viewMain viewMain) {
        this.viewMain = viewMain;
        permissionExamModel = new PermissionExamModel(this);
    }

    @Override
    public void tellModeltoGetRefrence() {
        permissionExamModel.getRequestsExams();
    }

    @Override
    public void refrenceExisit(List<Permission_Refrence> list) {
        viewMain.allRefrenceHere(list);
    }

    @Override
    public void refrenceNotExisit() {
        viewMain.noRefrenceHere();
    }
}
