package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.SigninContract;
import com.developer.mohamedraslan.hossamexams.Utils.ViewsEmpty;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by microprocess on 2018-09-30.
 */

public class SigninModel implements SigninContract.model {
    SigninContract.presenter presenter;
    ViewsEmpty empty ;
    FirebaseAuth auth ;
    public SigninModel(SigninContract.presenter presenter) {
        this.presenter = presenter ;
        empty = new ViewsEmpty();
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public String CheckisEmpty(String email, String password) {


        if (email.isEmpty()){

            return "الرجاء كتابة البريد الإلكتروني";
        }
        else if (!isEmailValid(email)){

            return "الرجاء كتابة البريد الإلكتروني بطريقة صحيحة";

        }
        else if (password.isEmpty()|| password.length() < 8 ) {
            if (password.isEmpty()) {


                return "قم بادخال الرقم السري من فضلك";

            } else {

                return "لابد ان يكون اكثر من 8 حرف للامان";
            }
        }
        else {

            return "Successful";
        }



    }

    @Override
    public void logIn(final String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    // successful
                    presenter.updatelogInResult("Successful",email);


                }
                else {


                    presenter.updatelogInResult("Failure",email);
                }

            }
        });
    }

    public boolean isEmailValid(CharSequence email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(); //return false if not ok //return true if ok
    }

}
