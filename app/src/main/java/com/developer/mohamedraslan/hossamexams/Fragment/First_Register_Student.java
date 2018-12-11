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

import com.developer.mohamedraslan.hossamexams.Contracts.First_Register_Contract;
import com.developer.mohamedraslan.hossamexams.Contracts.MainActivityContract;
import com.developer.mohamedraslan.hossamexams.Contracts.MySalaryComp;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.developer.mohamedraslan.hossamexams.MainPresnter.FirstRegisterPresnter;
import com.developer.mohamedraslan.hossamexams.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class First_Register_Student extends Fragment implements
                                            First_Register_Contract.ParentFirstRegisterView
                                            ,View.OnClickListener {



    Button gotonext;
    Spinner spinnerAllDeps,spinner_yearss;
    FirstRegisterPresnter firstRegisterPresnter;
    RelativeLayout snackBarr;
    String selectedItemFromSpinner = "" , parentItem = "";
    AnimatedDialog animatedDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstRegisterPresnter = new FirstRegisterPresnter(this);
        animatedDialog        = new AnimatedDialog(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v         = inflater.inflate(R.layout.firest_register_student,container,false);
        animatedDialog.ShowDialog();
        firstRegisterPresnter.tellModeltogetdeps();
        gotonext       = v.findViewById(R.id.gotonext);
        snackBarr      = v.findViewById(R.id.snackBarr);
        spinnerAllDeps = v.findViewById(R.id.spinnerAllDeps);
        spinner_yearss = v.findViewById(R.id.spinner_yearss);




        return v;
    }

    @Override
    public void depsHereshowInSpinner(List<String> list) {
        gotonext.setOnClickListener(this);
        final List<String> list1  = new ArrayList<>();

        list1.add("أختر قسم دراسي");

        for (int i=0 ; i<list.size();i++){

            list1.add(list.get(i));

        }


        animatedDialog.Close_Dialog();
        ArrayAdapter educationsadapter = new ArrayAdapter(getContext(),R.layout.spinner_style,list1);
        spinnerAllDeps.setAdapter(educationsadapter);
        spinnerAllDeps.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>0){


                    //  حنجيب كل الصفوف الي موجوده في القسم
                    parentItem  = list1.get(i);
                    animatedDialog.ShowDialog();
                    firstRegisterPresnter.tellmodeltoGetyearsInDeps(list1.get(i));


                }else {
                    spinner_yearss.setAdapter(null);
                    selectedItemFromSpinner = "";
                    parentItem              = "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //  حنعرض الاقسام هنا ومنها حنجيب الاقسام الي بداخلها بقااااا


    }



    @Override
    public void depsNotFound() {
        animatedDialog.Close_Dialog();
        Snackbar snackbar = Snackbar
                .make(snackBarr, "لا يوجد نتائج.", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
        if (getActivity()!=null){

            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
            textView.setTypeface(font);
            ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);

        }


        snackbar.show();

    }

    @Override
    public void problemsssssss(String error) {

        animatedDialog.Close_Dialog();
        Snackbar snackbar = Snackbar
                .make(snackBarr, "لقد حدثت مشكله بالإتصال.", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
        if (getActivity()!=null){

            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
            textView.setTypeface(font);
            ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);

        }


        snackbar.show();

    }


    @Override
    public void onClick(View view) {
        if (view == gotonext){


            if (selectedItemFromSpinner.equals("")|| parentItem.equals("")){

                //    هنا لسه ما البيانات ناقصه

                Snackbar snackbar = Snackbar
                        .make(snackBarr, "قم بملئ البيانات من فضلك.", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
                if (getActivity()!=null){

                    Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
                    textView.setTypeface(font);
                    ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);

                }


                snackbar.show();

            }




            else if (!selectedItemFromSpinner.equals("") && !parentItem.equals("")){


                //  open registerstudentFrag to store data ..............
                MainActivityContract.View view1 = (MainActivityContract.View) getActivity();

                if (view1!=null){

                    view1.showFragmentRegister(selectedItemFromSpinner,parentItem);
                }


                //   حنكمل تسجيل للمستخدم  ....

            }


        }
    }






    @Override
    public void showinSpinneryearFromDeps(List<Year_modle_json> list) {
        animatedDialog.Close_Dialog();
        final List<String>          finalList  = new ArrayList<>();
        Collections.sort(list, new MySalaryComp());
        finalList.add("الصفوف المتواجده بالقسم");
        for (int i=0;i<list.size();i++){

            finalList.add(list.get(i).getYearName());

        }
        ArrayAdapter educationsadapter = new ArrayAdapter(getContext(),R.layout.spinner_style,finalList);
        spinner_yearss.setAdapter(educationsadapter);
        spinner_yearss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>0){


                    selectedItemFromSpinner = finalList.get(i);


                }else {


                    selectedItemFromSpinner = "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void noYearsorry() {

        animatedDialog.Close_Dialog();
        selectedItemFromSpinner = "";
        spinner_yearss.setAdapter(null);
        Snackbar snackbar = Snackbar
                .make(snackBarr, "لا يوجد فصول بهذا القسم.", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
        if (getActivity()!=null){

            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
            textView.setTypeface(font);
            ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);

        }


        snackbar.show();
    }

    @Override
    public void withthisDepProblems(String problems) {
        animatedDialog.Close_Dialog();
        selectedItemFromSpinner = "";
        spinner_yearss.setAdapter(null);
        Snackbar snackbar = Snackbar
                .make(snackBarr, "لقد حدثت مشكله بالإتصال.", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
        if (getActivity()!=null){

            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
            textView.setTypeface(font);
            ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);

        }


        snackbar.show();

    }


}
