package com.developer.mohamedraslan.hossamexams.MainPresnter;

import android.content.SharedPreferences;

import com.developer.mohamedraslan.hossamexams.Contracts.RegisterFragContracts;
import com.developer.mohamedraslan.hossamexams.JsonModel.Resister_form;
import com.developer.mohamedraslan.hossamexams.MainModle.RegisterMode;
import com.google.firebase.database.DatabaseReference;

public class RegisterPresnter implements RegisterFragContracts.PresnterRegister {

    private RegisterFragContracts.ViewRegister view;
    RegisterMode registerMode ;

    public RegisterPresnter(RegisterFragContracts.ViewRegister view) {

        this.view = view;
        this.registerMode = new RegisterMode();
    }

    @Override
    public void authProblem(String E) {
        view.updateUiAboutProblemAUTH(E);
    }

    @Override
    public void updatUISuccessfull(String email , String password) {
        view.successDataSaved(email,password);
    }

    @Override
    public void updateUIFailed() {
        view.failedDataNotSaved();
    }

    @Override
    public void detailsForuserFromUI(SharedPreferences.Editor editor, String email, String password, DatabaseReference reference, Resister_form resister_form) {

        registerMode.signUP(editor,this,email,password,reference,resister_form);
    }
}
