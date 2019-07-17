package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.ParentContract;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ParentMyModel implements ParentContract.ParentModel {


    private ParentContract.ParentPresnter presnter;


    public ParentMyModel(ParentContract.ParentPresnter presnter) {
        this.presnter = presnter;
    }

    @Override
    public void getDetailsForStudentFromFirebase(final String codeStudent) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        Query query = firebaseDatabase.getReference().child(DataBase_Refrences.USERREF.getRef());
        query.orderByChild("studentCode").equalTo(codeStudent).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){


                    HashMap<String, FullRegisterForm> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, FullRegisterForm>>() {});
                    List<FullRegisterForm> toutourial         = new ArrayList<>(Objects.requireNonNull(results).values());
                    presnter.detaisUserHere(toutourial.get(0));







//                    presnter.detaisUserHere(fullRegisterForm);


                }else {


                    presnter.StudentNotFound();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                presnter.getProblemFromModel(databaseError.getMessage());

            }
        });


    }
}
