package com.developer.mohamedraslan.hossamexams.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.R;

import java.util.List;

public class AddDepartmentDialog extends Dialog implements View.OnClickListener{



    private String []         educations;
    private Spinner           spinnerEd,spinner_years;
    private ProgressBar       progressBarindialog;
    private LinearLayout      Liner22;
    private String []         termsArray;
    private String            childitemSelected = "" , itemSelected = "";
    private ControlPanelContract.ControlUI controlUI;
    private ArrayAdapter      adapter;
    private Button            addDeps;

    public AddDepartmentDialog(@NonNull Context context, int themeResId,  ControlPanelContract.ControlUI controlUI) {
        super(context, themeResId);
        this.controlUI = controlUI;



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.add_department_dialog);
        addDeps             = findViewById(R.id.addDeps);
        spinnerEd           = findViewById(R.id.spinnerEducations);
        progressBarindialog = findViewById(R.id.progressBarindialog);
        Liner22             = findViewById(R.id.Liner22);
        spinner_years       = findViewById(R.id.spinner_years);
        educations          = getContext().getResources().getStringArray(R.array.educations);

        ArrayAdapter educationsadapter = new ArrayAdapter(getContext(),R.layout.spinner_style,educations);
        spinnerEd.setAdapter(educationsadapter);
        spinnerEd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i>0){

                    spinner_years.setVisibility(View.VISIBLE);
                    int arryid  = getContext().getResources().getIdentifier(educations[i], "array",
                            getContext().getPackageName());
                    termsArray  = getContext().getResources().getStringArray(arryid);
                    adapter     = new ArrayAdapter(getContext(),R.layout.spinner_style, termsArray);
                    spinner_years.setAdapter(adapter);
                    itemSelected = educations[i];


                }else{

                    spinner_years.setVisibility(View.GONE);
                    childitemSelected = "";
                    itemSelected      = "";


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                // noThing toDo


            }
        });
        spinner_years.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                if (i>0){

                    childitemSelected = termsArray[i];



                }else {


                    childitemSelected ="";


                }



                    }







            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addDeps.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        if (view == addDeps){


            if (itemSelected.equals("")){

                controlUI.showSnackBar("Please Select Item");

            }

            else if (childitemSelected.equals("")){


                controlUI.showSnackBar("Please Select childItem");
            }



            else if (!itemSelected.equals("")&& !childitemSelected.equals("")){

                progressBarindialog.setVisibility(View.VISIBLE);
                // storing years in database  .... listen inUIfirest
                controlUI.storeDeps(itemSelected,childitemSelected);
                addDeps.setEnabled(false);

            }



        }


    }

    public Button returnButton(){


        return addDeps;

    }

    public ProgressBar getProgress(){

        return progressBarindialog;

    }
}
