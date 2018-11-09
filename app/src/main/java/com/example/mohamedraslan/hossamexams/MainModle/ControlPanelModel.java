package com.example.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.example.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.example.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.example.mohamedraslan.hossamexams.JsonModel.Resister_form;
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
}
