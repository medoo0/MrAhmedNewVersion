package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.Years_inDepsContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Year_Model implements Years_inDepsContract.MainModelYear {

    Years_inDepsContract.MainPYear mainPYear;
    List<Year_modle_json> years;
    Date date;


    public Year_Model(Years_inDepsContract.MainPYear mainPYear) {
        this.mainPYear = mainPYear;
        years          = new ArrayList<>();
        date           = new Date();
    }

    @Override
    public void getYearsDepartments(String parentOfYear) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Years");

        reference.child(parentOfYear).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){


                    HashMap<String, Year_modle_json> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Year_modle_json>>() {});
                    List<Year_modle_json> years              = new ArrayList<>(Objects.requireNonNull(results).values());
                    mainPYear.tellUIYearExisit(years);


                }else {


                   mainPYear.tellUIYearNotExisit();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                mainPYear.connectionPoooring(databaseError.toString());
            }
        });

    }
}
