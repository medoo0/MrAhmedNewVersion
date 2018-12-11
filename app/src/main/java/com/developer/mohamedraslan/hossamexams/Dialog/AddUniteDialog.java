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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Contracts.Unites_Contract;
import com.developer.mohamedraslan.hossamexams.R;

public class AddUniteDialog extends Dialog implements View.OnClickListener {

    ProgressBar loadingaddUnite;
    Spinner     spinnerUnites;
    Button      addUnites;
    String []  unitesArray;
    String     selectedItems  = "";
    Unites_Contract.ParentUnitesUI parentUnitesUI;

    public AddUniteDialog(@NonNull Context context, int themeResId, Unites_Contract.ParentUnitesUI parentUnitesUI) {
        super(context, themeResId);
        this.parentUnitesUI = parentUnitesUI;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.add_unite_dialog);
        unitesArray     = getContext().getResources().getStringArray(R.array.unites);
        loadingaddUnite = findViewById(R.id.loadingaddUnite);
        spinnerUnites   = findViewById(R.id.spinnerUnites);
        addUnites       = findViewById(R.id.addUnites);
        ArrayAdapter educationsadapter = new ArrayAdapter(getContext(),R.layout.spinner_style,unitesArray);
        spinnerUnites.setAdapter(educationsadapter);
        addUnites.setOnClickListener(this);


        spinnerUnites.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i>0){

                    selectedItems = unitesArray[i];

                }else {

                    selectedItems="";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    @Override
    public void onClick(View view) {

        if (view == addUnites){


            if (selectedItems.equals("")){

                Toast.makeText(getContext(), "Select unit", Toast.LENGTH_SHORT).show();

            }else {
                loadingaddUnite.setVisibility(View.VISIBLE);
                //  حنشوف الوحده موجوده ولا لا الاول  .... قبل ما نضيفها
                parentUnitesUI.nameUnite(selectedItems);
                addUnites.setEnabled(false);

            }

        }

    }

    public Button getButtom(){

        return addUnites;

    }


    public ProgressBar getProgress(){

        return loadingaddUnite;
    }
}
