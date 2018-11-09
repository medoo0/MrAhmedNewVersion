package com.example.mohamedraslan.hossamexams.MainModle;

import com.example.mohamedraslan.hossamexams.Contracts.StudentManagementContract;
import com.example.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.example.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.example.mohamedraslan.hossamexams.MainPresnter.StudentMangementPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        FullRegisterForm g1 = data.getValue(FullRegisterForm.class);
                        StudentsDetails.add(g1);
                        presenter.SendListToView(StudentsDetails);
                    }
                }
                else {

                    //not exist
                    presenter.problem("No Student");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //problem
                presenter.problem(databaseError.getMessage());


            }
        });

    }
}
