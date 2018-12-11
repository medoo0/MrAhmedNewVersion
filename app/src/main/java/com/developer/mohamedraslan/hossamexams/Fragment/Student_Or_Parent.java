package com.developer.mohamedraslan.hossamexams.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Contracts.MainActivityContract;
import com.developer.mohamedraslan.hossamexams.R;

public class Student_Or_Parent extends Fragment implements View.OnClickListener {

    Spinner  spinnerparentorstudent;
    Button   completedetailsbutton;
    String   itemSelectedFromSpinner = "";
    RelativeLayout showSnack;
    TextView gotoLoginagain;
    String [] details;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity()!=null){

            details   =  getActivity().getResources().getStringArray(R.array.student_or_parent);

        }


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v                 = inflater.inflate(R.layout.parent_or_student,container,false);
        spinnerparentorstudent = v.findViewById(R.id.spinnerparentorstudent);
        completedetailsbutton  = v.findViewById(R.id.completedetailsbutton);
        gotoLoginagain         = v.findViewById(R.id.gotoLoginagain);
        showSnack              = v.findViewById(R.id.showSnack);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.spinner_style,details);
        spinnerparentorstudent.setAdapter(adapter);

        spinnerparentorstudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>0){

                    itemSelectedFromSpinner = details[i];

                }else {

                    itemSelectedFromSpinner = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                // noThing toDo

            }
        });

        completedetailsbutton.setOnClickListener(this);
        gotoLoginagain.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {

        if (view  == completedetailsbutton){


            if (itemSelectedFromSpinner.equals("")){

                Snackbar snackbar = Snackbar
                        .make(showSnack, " إختر موضعك حتي تستطيع التسجيل.", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView =  sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);

                if (getActivity()!=null){

                    Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
                    textView.setTypeface(font);
                    ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);


                }

                snackbar.show();
                //  اظهر snackBar


            }else {


                if (itemSelectedFromSpinner.equals("طالب")){


                    MainActivityContract.View view1 = (MainActivityContract.View) getActivity();

                    if (view1!=null){
                        view1.showFirstRegisterStudent();
                    }


                }else if (itemSelectedFromSpinner.equals("ولي أمر")){


                    MainActivityContract.View view1 = (MainActivityContract.View) getActivity();

                    if (view1!=null){
                        view1.showParentRegister();
                    }
                }


                //  هنا بقا حنشوف هةا اختار ايه الظبط

            }

        }
        if (view == gotoLoginagain){


            MainActivityContract.View view1 = (MainActivityContract.View) getActivity();

            if (view1!=null){
                view1.showLoginFragment();
            }

        }

    }
}
