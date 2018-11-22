package com.example.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.mohamedraslan.hossamexams.Contracts.StudentResultContract;
import com.example.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class StudentResultModel implements StudentResultContract.MainModel {

    StudentResultContract.MainPresnter presnter;

    public StudentResultModel(StudentResultContract.MainPresnter presnter) {
        this.presnter = presnter;
    }

    @Override
    public void getResultsFromFirebase(DatabaseReference reference, String ExamID, final ImageView imageView) {

        reference.orderByChild("examID").equalTo(ExamID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.exists()){


                            HashMap<String, Result_Pojo> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Result_Pojo>>() {});
                            List<Result_Pojo> toutourial = new ArrayList<>(Objects.requireNonNull(results).values());
                            presnter.resulthereupdateview(toutourial,imageView);


                        }else {

                            presnter.noDataExisit(imageView);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        presnter.ProblemInternet(databaseError.getMessage());

                    }
                });


    }
}
