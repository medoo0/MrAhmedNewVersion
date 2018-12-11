package com.developer.mohamedraslan.hossamexams.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MoreDetailsFromNav extends Fragment implements View.OnClickListener {





    private CardView press_on_studyTeams , press_on_complaints,press_on_sendNotification;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();

        if (controlUI!=null){

            controlUI.setElevationZero();


        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v              = inflater.inflate(R.layout.moredetails_layout,container,false);
        ControlPanel.Title.setText("الرئيسية");
        ControlPanel.SetNavChecked(0);
        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){
            controlUI.enableDrawer(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
        press_on_studyTeams = v.findViewById(R.id.press_on_studyTeams);
        press_on_sendNotification = v.findViewById(R.id.press_on_sendNotification);
        press_on_complaints = v.findViewById(R.id.press_on_complaints);
        press_on_studyTeams.setScaleX(.9f);
        press_on_studyTeams.setScaleY(.9f);
        press_on_studyTeams.animate().scaleX(1f).scaleY(1f).setDuration(500);

        press_on_sendNotification.setScaleX(.9f);
        press_on_sendNotification.setScaleY(.9f);
        press_on_sendNotification.animate().scaleX(1f).scaleY(1f).setDuration(500);

        press_on_complaints.setScaleX(.9f);
        press_on_complaints.setScaleY(.9f);
        press_on_complaints.animate().scaleX(1f).scaleY(1f).setDuration(500);
        press_on_studyTeams.setOnClickListener(this);
        press_on_complaints.setOnClickListener(this);
        press_on_sendNotification.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {


        if (view == press_on_studyTeams){

            // open Student_Departments .......
            ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.showStudentDepartmentFragment();


            }





        }



        if (view == press_on_complaints){


            Toast.makeText(getActivity(), "جاري التطوير .....", Toast.LENGTH_SHORT).show();


        }

        if (view == press_on_sendNotification){


            ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.showDialogNotification();


            }

        }


    }
}
