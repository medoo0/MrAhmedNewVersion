package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.StudentManagementContract;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.MainPresnter.StudentMangementPresenter;
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

/**
 * Created by microprocess on 2018-10-01.
 */

public class StudentManagementModel implements StudentManagementContract.model {

    private StudentManagementContract.presenter presenter;
    private List<FullRegisterForm> StudentsDetails ;


    public StudentManagementModel(StudentMangementPresenter studentMangementPresenter) {

        this.presenter = studentMangementPresenter;
        StudentsDetails = new ArrayList<>();

    }

    @Override
    public void getstudentData() {

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child(DataBase_Refrences.USERREF.getRef());

        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {

                    HashMap<String, FullRegisterForm> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, FullRegisterForm>>() {});
                    List<FullRegisterForm> toutourial = new ArrayList<>(Objects.requireNonNull(results).values());
                    presenter.SendListToView(toutourial);
//                    for (DataSnapshot data : dataSnapshot.getChildren()) {
//                        FullRegisterForm g1 = data.getValue(FullRegisterForm.class);
//                        StudentsDetails.add(g1);
//                        presenter.SendListToView(StudentsDetails);
//                    }
                }
                else {

                    //not exist
                    presenter.problem("No Student");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                //problem
                presenter.problem(databaseError.getMessage());


            }
        });

    }
}
