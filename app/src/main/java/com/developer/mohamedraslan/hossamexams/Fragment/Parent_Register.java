package com.developer.mohamedraslan.hossamexams.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Contracts.MainActivityContract;
import com.developer.mohamedraslan.hossamexams.Contracts.ParentContract;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.MainPresnter.ParentPresnterr;
import com.developer.mohamedraslan.hossamexams.R;

public class Parent_Register extends Fragment implements View.OnClickListener ,ParentContract.ParentView{



Button searching;
EditText codeStudent;
ParentPresnterr parentPresnterr;
AnimatedDialog animatedDialog;


    //    الفراج دا بتاع الاب لما بيخزن بياناته




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v      = inflater.inflate(R.layout.parent_register,container,false);
        codeStudent = v.findViewById(R.id.codeStudent);
        searching   = v.findViewById(R.id.searching);
        animatedDialog = new AnimatedDialog(getActivity());
        parentPresnterr = new ParentPresnterr(this);
        searching.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {


        if (view == searching){



            if (codeStudent.getText().toString().isEmpty()){

                codeStudent.setError("من فضلك أدخل الكود");

            }
            else {
                animatedDialog.ShowDialog();
               parentPresnterr.tellModeltoGetAllDetailsForstudent(codeStudent.getText().toString());

            }


        }

    }

    @Override
    public void StudentdetailsHere(FullRegisterForm fullRegisterForm) {

        animatedDialog.Close_Dialog();

        MainActivityContract.View mainActivityContract = (MainActivityContract.View) getActivity();


        if (mainActivityContract!=null){


            mainActivityContract.fullRegisterForStudent(fullRegisterForm);

        }

    }

    @Override
    public void noDetailsFound() {
        animatedDialog.Close_Dialog();

        codeStudent.setError("من فضلك أدخل كود صحيح.");



    }

    @Override
    public void problemOcurr(String p) {
        animatedDialog.Close_Dialog();

        Toast.makeText(getActivity(), p, Toast.LENGTH_SHORT).show();

    }
}
