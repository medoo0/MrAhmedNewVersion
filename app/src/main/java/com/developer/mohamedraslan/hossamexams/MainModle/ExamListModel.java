package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;
import android.text.format.DateFormat;

import com.developer.mohamedraslan.hossamexams.Contracts.ExamListContract;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.Zone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by microprocess on 2018-10-09.
 */

public class ExamListModel implements ExamListContract.model {

    ExamListContract.presenter presenter ;

    public ExamListModel(ExamListContract.presenter examListPresenter) {
        presenter = examListPresenter;
    }

    @Override
    public void CheckifAdmin(String Uid) {

            DatabaseReference reference = FirebaseDatabase.getInstance()
                    .getReference(DataBase_Refrences.ADMIN.getRef())
                    .child(Uid);



            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){

                        presenter.ShowAdminTools();

                    }
                    else {

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    }


    public void getDateAndTime() {

//        Map<String , String> map = new HashMap<>();
//        map.put("key", DataBase_Refrences.TimeApiKey.getRef());
//        map.put("format",DataBase_Refrences.Format.getRef());
//        ApiMethod apiMethod =  Retrofit_Body.getRetrofit().create(ApiMethod.class);
//        Call<All_Country_Details> connection = apiMethod.getTiming(map);
//        connection.enqueue(new Callback<All_Country_Details>() {
//            @Override
//            public void onResponse(@NonNull Call<All_Country_Details> call, @NonNull Response<All_Country_Details> response) {
//
//                if (response.isSuccessful()){
//
//                    Zone zone = Objects.requireNonNull(response.body()).getZones().get(DataBase_Refrences.CountryNum.getINTref());
//                    realtimehere(zone);
//
//                }
//
//
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<All_Country_Details> call, @NonNull Throwable t) {
//
//
//
//            }
//        });

        ///////without Server .
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime = sdf.format(new Date());
        presenter.PassRealTimeFromServerToView(currentDateandTime);

    }



    public String getDate(long time_stamp_server) {

//        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
//        return formatter.format(time_stamp_server);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time_stamp_server * 1000L);
        return DateFormat.format("dd-MM-yyyy", cal).toString();

    }

    public void realtimehere(Zone zone) {

        final String date  = getDate(zone.getTimestamp());
        presenter.PassRealTimeFromServerToView(date);
    }

}
