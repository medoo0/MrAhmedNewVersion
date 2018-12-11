package com.developer.mohamedraslan.hossamexams.MainModle;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.RegisterFragContracts;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.JsonModel.Resister_form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class RegisterMode implements RegisterFragContracts.ModelRegister {

    private FirebaseAuth auth ;
    private String UID;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referenceBlock;

    @Override
    public void signUP(final SharedPreferences.Editor editor, final RegisterFragContracts.PresnterRegister presnterRegister , final String Email , final String passord, final DatabaseReference reference, final Resister_form resister_form) {

        auth             = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(Email,passord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()){

                    // added to authentication  //
                    int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
                    UID              =  auth.getUid();
                    assert UID != null;
                    final FullRegisterForm fullRegisterForm = new FullRegisterForm(resister_form.getNameStudent(),resister_form.getEmail(),resister_form.getPhone(),UID,resister_form.getCountry(),"no",resister_form.getYear(),String.valueOf(m),resister_form.getParentYear());
                    reference.child(UID).setValue(fullRegisterForm).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                presnterRegister.updatUISuccessfull(Email,passord,resister_form.getParentYear());


                            }

                        }
                    });


                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                presnterRegister.authProblem(e.toString());

            }
        });


    }
}
