package com.developer.mohamedraslan.hossamexams.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.developer.mohamedraslan.hossamexams.Contracts.MainActivityContract;
import com.developer.mohamedraslan.hossamexams.Fragment.First_Fragment;
import com.developer.mohamedraslan.hossamexams.Fragment.First_Register_Student;
import com.developer.mohamedraslan.hossamexams.Fragment.Parent_Register;
import com.developer.mohamedraslan.hossamexams.Fragment.Register_Fragment;
import com.developer.mohamedraslan.hossamexams.Fragment.Signin_Fragment;
import com.developer.mohamedraslan.hossamexams.Fragment.Student_Or_Parent;
import com.developer.mohamedraslan.hossamexams.Notifications.MyFirebaseMessagingService;
import com.developer.mohamedraslan.hossamexams.R;
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
    public void showFragmentRegister(String yearName,String parentYear) {
        // showing register fragment from Firt Fragment ..............//

        Register_Fragment register_fragment = new Register_Fragment();
        Bundle b = new Bundle();
        b.putString("yearName",yearName);
        b.putString("parentYear",parentYear);
        register_fragment.setArguments(b);
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Main_fragment,register_fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showLoginFragment() {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Main_fragment,new Signin_Fragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void openControlPanel(String email , String password,String depofStudent) {
        //  لما المستخدم يسجل ويدخل علي الصفحه بتاعته
        Intent intent = new Intent(this,ControlPanel.class);
        intent.putExtra("depofME",depofStudent);
        startActivity(intent);
        finish();
    }

    @Override
    public void showParentOrStudentFragment() {

        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Main_fragment,new Student_Or_Parent())
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void showParentRegister() {

        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Main_fragment,new Parent_Register())
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void showFirstRegisterStudent() {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Main_fragment,new First_Register_Student())
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void onBackPressed() {


            super.onBackPressed();



    }
}
