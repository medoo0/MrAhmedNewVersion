package com.am.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.am.mohamedraslan.hossamexams.Contracts.PermissionExamsContract;
import com.am.mohamedraslan.hossamexams.JsonModel.Permission_Refrence;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PermissionExamModel implements PermissionExamsContract.PermissionModel {


    PermissionExamsContract.PermissionPresnter presnter;


    public PermissionExamModel(PermissionExamsContract.PermissionPresnter presnter) {
        this.presnter = presnter;
    }

    @Override
    public void getRequestsExams() {


        FirebaseDatabase  firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference reference        = firebaseDatabase.getReference("PermissionRefrence");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    HashMap<String, Permission_Refrence> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Permission_Refrence>>() {});
                    List<Permission_Refrence> toutourial         = new ArrayList<>(Objects.requireNonNull(results).values());

                    presnter.refrenceExisit(toutourial);

                }
                else {

                    presnter.refrenceNotExisit();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
