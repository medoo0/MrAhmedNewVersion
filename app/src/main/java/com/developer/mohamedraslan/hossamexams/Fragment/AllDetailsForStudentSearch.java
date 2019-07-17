package com.developer.mohamedraslan.hossamexams.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Adapter.AdapterResultFromSerch;
import com.developer.mohamedraslan.hossamexams.Contracts.AllDetailsStudentSearchContract;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.developer.mohamedraslan.hossamexams.MainPresnter.AllStudentSearchPresnter;
import com.developer.mohamedraslan.hossamexams.R;

import java.util.List;

public class AllDetailsForStudentSearch extends Fragment implements AllDetailsStudentSearchContract.AllStudentSerachUI {


    RelativeLayout background;
    TextView backgroundground;
    CardView studentDetailsforGround ,showRecfrombottom;
    FullRegisterForm fullRegisterForm;
    AnimatedDialog animatedDialog;
    RecyclerView showResult;
    AllStudentSearchPresnter allStudentSearchPresnter;
    TextView txNameStudent,country ,yearNametx;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view                = inflater.inflate(R.layout.alldetailsdorstudentsearch,container,false);
        background               = view.findViewById(R.id.background);
        studentDetailsforGround  = view.findViewById(R.id.studentDetailsforGround);
        showRecfrombottom        = view.findViewById(R.id.showRecfrombottom);
        animatedDialog           = new AnimatedDialog(getActivity());
        txNameStudent            = view.findViewById(R.id.txNameStudent);
        country                  = view.findViewById(R.id.country);
        yearNametx               = view.findViewById(R.id.yearName);
        backgroundground         = view.findViewById(R.id.backgroundground);
        showResult               = view.findViewById(R.id.showResult);
        allStudentSearchPresnter = new AllStudentSearchPresnter(this);

        background.setX(-1000);
        background.animate().translationXBy(1000).setDuration(1000);

        studentDetailsforGround.setX(1000);
        studentDetailsforGround.animate().translationXBy(-1000).setDuration(1000);

        showRecfrombottom.setY(1000);
        showRecfrombottom.animate().translationYBy(-1000).setDuration(1000);

        if (getArguments()!=null){

            fullRegisterForm = getArguments().getParcelable("fullRegister");

            if (fullRegisterForm!=null){

                animatedDialog.ShowDialog();
                allStudentSearchPresnter.tellModeltoGetResult(fullRegisterForm.getuID(),fullRegisterForm.getParentYear(),fullRegisterForm.getYear());
            }


        }




       return view;
    }

    @Override
    public void notResultFound() {

        txNameStudent.setText(fullRegisterForm.getNameStudent());
        country.setText(fullRegisterForm.getCountry());
        yearNametx.setText(fullRegisterForm.getYear());


        animatedDialog.Close_Dialog();

        if (!backgroundground.isShown()){

            backgroundground.setVisibility(View.VISIBLE);
            backgroundground.setY(1000);
            backgroundground.animate().translationYBy(-1000).setDuration(1000);

        }


    }

    @Override
    public void ResultHere(List<Result_Pojo> result) {
        animatedDialog.Close_Dialog();
        txNameStudent.setText(fullRegisterForm.getNameStudent());
        country.setText(fullRegisterForm.getCountry());
        yearNametx.setText(fullRegisterForm.getYear());
        if (backgroundground.isShown()){

            backgroundground.setVisibility(View.GONE);

        }




            AdapterResultFromSerch adapterResultFromSerch = new AdapterResultFromSerch(result,getActivity(),this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setStackFromEnd(true);
            layoutManager.setReverseLayout(true);
            showResult.setLayoutManager(layoutManager);
            showResult.setAdapter(adapterResultFromSerch);





    }

    @Override
    public void problems(String error) {
        animatedDialog.Close_Dialog();
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

    }
}
