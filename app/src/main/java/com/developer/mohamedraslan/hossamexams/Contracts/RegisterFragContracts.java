package com.developer.mohamedraslan.hossamexams.Contracts;


import android.content.SharedPreferences;

import com.developer.mohamedraslan.hossamexams.JsonModel.Resister_form;
import com.google.firebase.database.DatabaseReference;

public interface RegisterFragContracts {


    interface ViewRegister{

        void successDataSaved(String email, String password);
        void failedDataNotSaved();
        void updateUiAboutProblemAUTH(String E);
    }



    interface PresnterRegister{
        void authProblem(String E);
        void updatUISuccessfull(String email, String password);
        void updateUIFailed();
        void detailsForuserFromUI(SharedPreferences.Editor editor, String email, String password, DatabaseReference reference, Resister_form resister_form);

    }

    interface ModelRegister{

        void signUP(SharedPreferences.Editor editor, RegisterFragContracts.PresnterRegister presnterRegister, String email, String password, DatabaseReference reference, Resister_form resister_form);

    }


}
