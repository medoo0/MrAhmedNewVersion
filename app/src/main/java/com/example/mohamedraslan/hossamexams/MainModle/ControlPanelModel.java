package com.example.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.example.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.example.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.example.mohamedraslan.hossamexams.JsonModel.Resister_form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ControlPanelModel implements ControlPanelContract.ControlModelUI {


    ControlPanelContract.ControlPresnterUI presnterUI;

    public ControlPanelModel (ControlPanelContract.ControlPresnterUI presnterUI){

        this.presnterUI = presnterUI;
    }


    @Override
    public void CheckifUserBanned(String Uid) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                                    .getReference(DataBase_Refrences.BLOCKUSER.getRef())
                                    .child(Uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    presnterUI.CheckifUserBannedResult("Successful");

                }
                else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void CheckifAdmin(String Uid) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(DataBase_Refrences.ADMIN.getRef())
                .child(Uid);



        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    presnterUI.HeIsAdmin();

                }
                else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void getuserName(String uid) {

        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(DataBase_Refrences.USERREF.getRef())
                .child(uid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    Resister_form form = dataSnapshot.getValue(Resister_form.class);
                    presnterUI.SetUsername(form.getNameStudent()+"");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void removeUserFromAuth(final String uID) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference(DataBase_Refrences.USERREF.getRef()).child(uID);
        reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){


                    removedetailsforUser("");

                }else {


                     presnterUI.problemUserNotDeleted();

                }

            }
        });



    }

    @Override
    public void removedetailsforUser(String uID) {





    }

    @Override
    public void storingTokentoDatabase(String token) {


        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uID        = auth.getCurrentUser().getUid();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Tokens_Device");
        reference.child(uID).setValue(token).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    presnterUI.tellUitokenStored();


                }else {

                    presnterUI.tellUitokenDosentStore();

                }

            }
        });




    }

    @Override
    public void checkifTokenExisitorNot() {

        String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Tokens_Device");
        reference.child(uID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    presnterUI.tellUItokenisExisit();

                }else {

                    presnterUI.tellUItokennotExisit();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
