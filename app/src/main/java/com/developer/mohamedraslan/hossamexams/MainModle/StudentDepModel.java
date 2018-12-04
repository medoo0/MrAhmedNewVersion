package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.Student_Department_Contract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Permission_Refrence;
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

public class StudentDepModel implements Student_Department_Contract.ModelStudentDep {




    Student_Department_Contract.PresnterStudentDep presnterStudentDep;


    public StudentDepModel(Student_Department_Contract.PresnterStudentDep presnterStudentDep) {
        this.presnterStudentDep = presnterStudentDep;
    }

    @Override
    public void getDepsRefrences() {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Study_Deps_Refrence");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    HashMap<String, String> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, String>>() {});
                    List<String> deps         = new ArrayList<>(Objects.requireNonNull(results).values());
                    presnterStudentDep.updateUIbyArrayDeps(deps);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                presnterStudentDep.problemswithRefrence(databaseError.toString());

            }
        });


    }
}
