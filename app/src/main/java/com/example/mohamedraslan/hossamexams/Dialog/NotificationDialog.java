package com.example.mohamedraslan.hossamexams.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.example.mohamedraslan.hossamexams.R;

public class NotificationDialog extends Dialog implements View.OnClickListener {

    Button   sendfeedback;
    private EditText  edTextNot;
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

                controlUI.notificationMessages(edTextNot.getText().toString());
                this.dismiss();
            }

        }

    }
}
