package com.developer.mohamedraslan.hossamexams.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Contracts.MySalaryComp;
import com.developer.mohamedraslan.hossamexams.Contracts.NotificationdialogContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.developer.mohamedraslan.hossamexams.MainPresnter.Notificationpresnter;
import com.developer.mohamedraslan.hossamexams.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationDialog extends Dialog implements View.OnClickListener, NotificationdialogContract.NotificationdialogUI {

    public static Button   sendfeedback;
    public  static EditText  edTextNot;
    ProgressBar P1,p2;
    private String []         educations;
    private String            childitemSelected = "" , itemSelected = "";
    AnimatedDialog animatedDialog;
    ControlPanelContract.ControlUI controlUI;
    Spinner spinnerEducationsnot,spinner_yearsnot;
    Notificationpresnter notificationpresnter;


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
        animatedDialog                 = new AnimatedDialog(getContext());
        notificationpresnter           = new Notificationpresnter(this);
        educations                     = getContext().getResources().getStringArray(R.array.educations);
        ArrayAdapter educationsadapter = new ArrayAdapter(getContext(),R.layout.spinner_style,educations);


        edTextNot    = findViewById(R.id.edTextNot);
        p2           = findViewById(R.id.p2);
        P1           = findViewById(R.id.P1);
        sendfeedback = findViewById(R.id.sendfeedback);
        spinner_yearsnot     = findViewById(R.id.spinner_yearsnot);
        spinnerEducationsnot = findViewById(R.id.spinnerEducationsnot);
        spinnerEducationsnot.setAdapter(educationsadapter);

        spinnerEducationsnot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i>0){

                    itemSelected    = educations[i];
                    spinner_yearsnot.setVisibility(View.VISIBLE);
                    animatedDialog.ShowDialog();
                    notificationpresnter.tellModeltogetYearstoDep(itemSelected);

                    //  هنا حنجيب بيانات الصفوف بتاعه القسم


                }else{

                    spinner_yearsnot.setVisibility(View.GONE);
                    spinner_yearsnot.setAdapter(null);
                    childitemSelected = "";
                    itemSelected      = "";


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        sendfeedback.setOnClickListener(this);







    }

    @Override
    public void onClick(View view) {

        if (view == sendfeedback){

            if (edTextNot.getText().toString().isEmpty()){
                edTextNot.setError("الحقل فارغ.");

            }
            else {

                if (!itemSelected.equals("") && !childitemSelected.equals("")){


                    P1.setVisibility(View.VISIBLE);
                    p2.setVisibility(View.VISIBLE);
                    controlUI.notificationMessages(edTextNot.getText().toString(),p2,P1,itemSelected,childitemSelected);
                    sendfeedback.setEnabled(false);




                }else {

                    Toast.makeText(getContext(), "قم بملئ البيانات كامله.", Toast.LENGTH_SHORT).show();

                }



            }

        }

    }

    @Override
    public void MyYearsHere(List<Year_modle_json> list) {

        animatedDialog.Close_Dialog();
        final List<String>          finalList  = new ArrayList<>();
        Collections.sort(list, new MySalaryComp());
        finalList.add("الصفوف المتواجده بالقسم");
        for (int i=0;i<list.size();i++){

            finalList.add(list.get(i).getYearName());

        }
        ArrayAdapter year = new ArrayAdapter(getContext(),R.layout.spinner_style,finalList);
        spinner_yearsnot.setAdapter(year);
        spinner_yearsnot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>0){


                    childitemSelected = finalList.get(i);


                }else {


                    childitemSelected = "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void MyyearNotFound() {
        animatedDialog.Close_Dialog();
        spinner_yearsnot.setVisibility(View.GONE);
        spinner_yearsnot.setAdapter(null);
        childitemSelected = "";

        Toast.makeText(getContext(), "لا يوجد فصول دراسيه بهذا القسم", Toast.LENGTH_LONG).show();


    }
}
