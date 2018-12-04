package com.developer.mohamedraslan.hossamexams.Views;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.R;

public class Splach_Activity extends AppCompatActivity {

    AnimatedDialog dialog;
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_);
        dialog = new AnimatedDialog(this);
        dialog.ShowDialog();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splach_Activity.this,MainActivity.class);
                Splach_Activity.this.startActivity(mainIntent);
                finish();

            }
        }, SPLASH_DISPLAY_LENGTH);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dialog.Close_Dialog();


    }
}
