package com.developer.mohamedraslan.hossamexams.MainModle;


import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.MyResultContract;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by microprocess on 2018-10-18.
 */

public class MyResultModel implements MyResultContract.model {

    MyResultContract.presenter presenter;
    List<Result_Pojo> Result;
    public MyResultModel(MyResultContract.presenter presenter) {
        this.presenter = presenter;
        Result = new ArrayList<>();
    }

    @Override
    public void getMyResults(String uid) {

        Query query = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.RESULT.getRef()).orderByChild("uid")
                .equalTo(uid);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                            Result_Pojo pojo = snapshot.getValue(Result_Pojo.class);
                            Result.add(pojo);

                        }
                        presenter.ConfigRecycler(Result);
                    }
                    else {

                        presenter.Problem("لا توجد نتائج سابقة . ");
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}
