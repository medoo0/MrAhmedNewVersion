package com.developer.mohamedraslan.hossamexams.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.R;

public class NotificationDialog extends Dialog implements View.OnClickListener {

    public static Button   sendfeedback;
    public  static EditText  edTextNot;
    ProgressBar P1,p2;
    ControlPanelContract.ControlUI controlUI;


    public NotificationDialog(@NonNull Context context, int themeResId, ControlPanelContract.ControlUI controlUI) {
        super(context, themeResId);
        this.controlUI = controlUI;



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.notification_dialog);
        edTextNot = findViewById(R.id.edTextNot);
        p2        = findViewById(R.id.p2);
        P1        = findViewById(R.id.P1);
        sendfeedback = findViewById(R.id.sendfeedback);
        sendfeedback.setOnClickListener(this);







    }

    @Override
    public void onClick(View view) {

        if (view == sendfeedback){

            if (edTextNot.getText().toString().isEmpty()){
                edTextNot.setError("الحقل فارغ.");

            }
            else {
                P1.setVisibility(View.VISIBLE);
                p2.setVisibility(View.VISIBLE);
                controlUI.notificationMessages(edTextNot.getText().toString(),p2,P1);
                sendfeedback.setEnabled(false);

            }

        }

    }
}
