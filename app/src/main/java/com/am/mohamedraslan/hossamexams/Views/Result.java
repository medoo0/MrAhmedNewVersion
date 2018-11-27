package com.am.mohamedraslan.hossamexams.Views;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.am.mohamedraslan.hossamexams.Contracts.ResultContract;
import com.am.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.am.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.am.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.am.mohamedraslan.hossamexams.JsonModel.Resister_form;
import com.am.mohamedraslan.hossamexams.JsonModel.WorngQestion;
import com.am.mohamedraslan.hossamexams.MainPresnter.ResultPresenter;
import com.am.mohamedraslan.hossamexams.Notifications.MyFirebaseMessagingService;
import com.am.mohamedraslan.hossamexams.R;
import com.am.mohamedraslan.hossamexams.SqLite.SQlHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Result extends AppCompatActivity implements ResultContract.view {

    @BindView(R.id.txDegree)
    TextView txDegree;

    @BindView(R.id.txFinalDegree)
    TextView txFinalDegree;

    @BindView(R.id.home)
    Button home ;

    SQlHelper helper;
    SQLiteDatabase db;
    String TableName = "", finalDegree = "";
    ResultContract.presenter presenter;
    AnimatedDialog dialog ;

    String total;
    private String Examname;
    private String ExamDate , Message;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        startService(new Intent(this, MyFirebaseMessagingService.class));

        intial();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            TableName      = bundle.getString("SqlTableName");
            finalDegree    = bundle.getString("final_degree");
            Examname = bundle.getString("Examname");
            ExamDate = bundle.getString("ExamDate");
            Message  = bundle.getString("Message");
            presenter.CountDegree(db,TableName);

        }

            home.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      Intent intent = new Intent(Result.this,ControlPanel.class);
                      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      startActivity(intent);
                      finish();

                      //ads
                      if (mInterstitialAd.isLoaded()) {
                          mInterstitialAd.show();
                      } else {
                          Log.d("TAG", "The interstitial wasn't loaded yet.");
                      }

                      mInterstitialAd.setAdListener(new AdListener() {
                          @Override
                          public void onAdLoaded() {
                              // Code to be executed when an ad finishes loading.

                          }

                          @Override
                          public void onAdFailedToLoad(int errorCode) {
                              // Code to be executed when an ad request fails.

                          }

                          @Override
                          public void onAdOpened() {
                              // Code to be executed when the ad is displayed.
                          }

                          @Override
                          public void onAdLeftApplication() {
                              // Code to be executed when the user has left the app.
                          }

                          @Override
                          public void onAdClosed() {
                              // Code to be executed when when the interstitial ad is closed.
                          }
                      });

                 }
             });


            // just show message .
            AlertDialog alertDialog = new AlertDialog(this,Message);
            alertDialog.show();





    }

    void intial(){
        ButterKnife.bind(this);
        presenter = new ResultPresenter(this);
         helper = new SQlHelper(this);
         db = helper.getWritableDatabase();
        dialog = new AnimatedDialog(this);
        dialog.ShowDialog();



        //Ads
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4214877267260040/2304303404");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


    }

    @Override
    public void TotalDegrees(int total) {
        this.total = String.valueOf(total);
        txFinalDegree.setText(finalDegree);
        txDegree.setText(this.total);

        //get Wrong Questions .
        presenter.getWrongQestions(db,TableName);
    }

    @Override
    public void WrongQuestions(final ArrayList<WorngQestion> worngQestions) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.USERREF.getRef())
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //Get Student Name .
                    Resister_form form = dataSnapshot.getValue(Resister_form.class);


                    presenter.UploadResult(TableName.replace(FirebaseAuth.getInstance().getCurrentUser().getUid(),"").substring(1)
                            , FirebaseAuth.getInstance().getCurrentUser().getUid(),
                            ExamDate,Examname,finalDegree, total ,worngQestions, Objects.requireNonNull(form).getNameStudent());

                    dialog.Close_Dialog();

                }
                else {


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void UploadSuccessFull(String s) {
        if(!isFinishing()) {
            dialog.Close_Dialog();
            AlertDialog alertDialog = new AlertDialog(this, s);
            alertDialog.show();
        }
    }

    @Override
    public void ResultUploadFaild(String s) {
        if(!isFinishing()) {
        dialog.Close_Dialog();
        AlertDialog alertDialog = new AlertDialog(this,s);
        alertDialog.show();
        }
    }

    @Override
    public void onBackPressed() {

        //To avoid Back To Exam again . (if Time Finished Only ) .
        Intent intent = new Intent(Result.this,ControlPanel.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
