package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.NotificationdialogContract;
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

public class NotificationModel implements NotificationdialogContract.NotificationDialogModel {



    NotificationdialogContract.NotificationDialogPresnter presnter;


    public NotificationModel(NotificationdialogContract.NotificationDialogPresnter presnter) {
        this.presnter = presnter;
    }

    @Override
    public void getYearsFromServer(String depName) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Years");

        reference.child(depName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){



                    HashMap<String, Year_modle_json> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Year_modle_json>>() {});
                    List<Year_modle_json> years              = new ArrayList<>(Objects.requireNonNull(results).values());

                    presnter.myYearHereGivetoSpineer(years);

                }else {

                   presnter.myYearNotFoundemptySpinner();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
