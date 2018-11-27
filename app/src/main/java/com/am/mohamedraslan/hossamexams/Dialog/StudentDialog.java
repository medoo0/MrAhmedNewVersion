package com.am.mohamedraslan.hossamexams.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.am.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.am.mohamedraslan.hossamexams.R;

public class StudentDialog extends Dialog implements View.OnClickListener{

    Button allStudents , myStudents;
    ControlPanelContract.ControlUI controlUI;

    public StudentDialog(@NonNull Context context, int themeResId, ControlPanelContract.ControlUI controlUI) {
        super(context, themeResId);
        this.controlUI = controlUI;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.student_dialog);
        allStudents = findViewById(R.id.allStudent);
        myStudents  = findViewById(R.id.myStudent);
        allStudents.setOnClickListener(this);
        myStudents.setOnClickListener(this);






    }

    @Override
    public void onClick(View v) {

        if (v == allStudents){

            controlUI.showDialogStudent("allStudents");

        }


        if (v == myStudents){

            controlUI.showDialogStudent("myStudents");

        }

    }
}
