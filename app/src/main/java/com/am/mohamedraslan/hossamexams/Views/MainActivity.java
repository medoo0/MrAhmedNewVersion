package com.am.mohamedraslan.hossamexams.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.am.mohamedraslan.hossamexams.Contracts.MainActivityContract;
import com.am.mohamedraslan.hossamexams.Fragment.First_Fragment;
import com.am.mohamedraslan.hossamexams.Fragment.Register_Fragment;
import com.am.mohamedraslan.hossamexams.Fragment.Signin_Fragment;
import com.am.mohamedraslan.hossamexams.Notifications.MyFirebaseMessagingService;
import com.am.mohamedraslan.hossamexams.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{


    FirebaseAuth auth;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onStart() {
        super.onStart();

        //Enable collection for selected users by initializing Crashlytics from one of your app's activities
        Fabric.with(this, new Crashlytics());
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        if (auth.getCurrentUser()!=null){

            Crashlytics.setUserIdentifier(auth.getUid());
            startActivity(new Intent(this,ControlPanel.class));
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth  = FirebaseAuth.getInstance();
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.Main_fragment,new First_Fragment(),"firestFrag")
                .commit();


        stopService(new Intent(this, MyFirebaseMessagingService.class));

    }

    @Override
    public void showFragmentRegister() {
        // showing register fragment from Firt Fragment ..............//


        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Main_fragment,new Register_Fragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showLoginFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Main_fragment,new Signin_Fragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void openControlPanel(String email , String password) {
        //  لما المستخدم يسجل ويدخل علي الصفحه بتاعته
        Intent intent = new Intent(this,ControlPanel.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {


            super.onBackPressed();



    }
}
