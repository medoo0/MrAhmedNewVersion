package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.AllDetailsStudentSearchContract;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
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

public class AllStudentSerchModel implements AllDetailsStudentSearchContract.AllstudentModel {


    private AllDetailsStudentSearchContract.AllStudentPresnter presnter;


    public AllStudentSerchModel(AllDetailsStudentSearchContract.AllStudentPresnter presnter) {
        this.presnter = presnter;
    }

    @Override
    public void getResultsinAllUnits(String StudentID, String depName, String yearName) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference(DataBase_Refrences.RESULT.getRef());

        reference.child(depName).child(yearName).orderByChild("uid").equalTo(StudentID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){


                    HashMap<String, Result_Pojo> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Result_Pojo>>() {});
                    List<Result_Pojo> toutourial         = new ArrayList<>(Objects.requireNonNull(results).values());
                    presnter.resultExisit(toutourial);

                }else {


                    presnter.thisStudentDosnthaveAnyResult();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presnter.probleming(databaseError.getDetails());

            }
        });

    }
}
