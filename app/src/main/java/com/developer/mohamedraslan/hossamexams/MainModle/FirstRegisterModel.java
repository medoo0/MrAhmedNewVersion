package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.First_Register_Contract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
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

public class FirstRegisterModel implements First_Register_Contract.ParentFirstRegisterModel {

    private First_Register_Contract.ParentFirstRegisterPresnter parentFirstRegisterPresnter;

    public FirstRegisterModel(First_Register_Contract.ParentFirstRegisterPresnter parentFirstRegisterPresnter) {
        this.parentFirstRegisterPresnter = parentFirstRegisterPresnter;
    }

    @Override
    public void getAlldepsFromFirbase() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Study_Deps_Refrence");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    HashMap<String, String> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, String>>() {});
                    List<String> Deps               = new ArrayList<>(Objects.requireNonNull(results).values());
                    parentFirstRegisterPresnter.tellUIDepsAreHere(Deps);

                }else {

                    parentFirstRegisterPresnter.tellUInotFoundDeps();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

               parentFirstRegisterPresnter.tellUiprobleming(databaseError.toString());
            }
        });



    }

    @Override
    public void getyearsinDeps(String nameOfDpe) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Years");
        reference.child(nameOfDpe).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){



                    HashMap<String, Year_modle_json> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Year_modle_json>>() {});
                    List<Year_modle_json> years              = new ArrayList<>(Objects.requireNonNull(results).values());

                    parentFirstRegisterPresnter.yearsinDepshere(years);

                }else {

                   parentFirstRegisterPresnter.noYearinThisDeps();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                parentFirstRegisterPresnter.tellUiProblemConnection(databaseError.toString());
            }
        });


    }
}
