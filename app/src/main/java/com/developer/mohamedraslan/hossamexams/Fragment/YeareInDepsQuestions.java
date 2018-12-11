package com.developer.mohamedraslan.hossamexams.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.developer.mohamedraslan.hossamexams.Adapter.Adapter_show_YearsInDeps;
import com.developer.mohamedraslan.hossamexams.Adapter.Adapter_show_YearsInDepsQ;
import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Contracts.MySalaryComp;
import com.developer.mohamedraslan.hossamexams.Contracts.Years_inDepsContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.developer.mohamedraslan.hossamexams.MainPresnter.Year_Presnter;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;

import java.util.Collections;
import java.util.List;

public class YeareInDepsQuestions extends Fragment implements Years_inDepsContract.ViewMainYear {

    RecyclerView yearsindepquestion;
    TextView     numofYearsquestion;
    ImageView    resultYearQ;
    RelativeLayout snackBBarquestion;
    private  String depsYear = "" ;
    private Year_Presnter year_presnter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();

        if (b!=null){

            depsYear      = b.getString("depsYear","");
            year_presnter = new Year_Presnter(this);


        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.year_question,container,false);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        ControlPanel.SetNavChecked(2);
        ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){
            controlUI.enableDrawer(DrawerLayout.LOCK_MODE_UNLOCKED);
        }


        ControlPanel.Title.setText(depsYear);
        yearsindepquestion = v.findViewById(R.id.yearsindepquestion);
        if (!depsYear.equals("")){

            year_presnter.tellModeltogetYears(depsYear);

        }

        numofYearsquestion = v.findViewById(R.id.numofYearsquestion);
        resultYearQ        = v.findViewById(R.id.resultYearQ);
        snackBBarquestion  = v.findViewById(R.id.snackBBarquestion);
        return v;
    }

    @Override
    public void exisityear(List<Year_modle_json> years) {


        //  حنعمل sort بقا لل array
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        Collections.sort(years,new MySalaryComp());

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        // حنعمل ادابتر تاني بقا عشان نعرف نميز الحاجات عن بعضها  .....
        Adapter_show_YearsInDepsQ department_refrence_adapter = new Adapter_show_YearsInDepsQ(getActivity(),years,this,depsYear);
        yearsindepquestion.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        yearsindepquestion.setLayoutManager(linearLayoutManager);
        yearsindepquestion.setAdapter(department_refrence_adapter);











    }

    @Override
    public void problemsyearnotFound() {
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        resultYearQ.setVisibility(View.VISIBLE);

//        Snackbar snackbar = Snackbar
//                .make(snackBBarquestion, "لا يوجد فصول بهذا القسم.", Snackbar.LENGTH_LONG);
//        View sbView = snackbar.getView();
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.YELLOW);
//        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
//        if (getActivity()!=null){
//
//            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
//            textView.setTypeface(font);
//
//        }
//
//        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
//        snackbar.show();
    }

    @Override
    public void connectionPoor(String error) {
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
//        Snackbar snackbar = Snackbar
//                .make(snackBBarquestion, "لقد حدث مشكله في الاتصال.", Snackbar.LENGTH_LONG);
//        View sbView = snackbar.getView();
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.YELLOW);
//        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
//        if (getActivity()!=null){
//
//            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
//            textView.setTypeface(font);
//
//        }
//        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
//        snackbar.show();
    }

    @Override
    public void getSizeofarray(int size) {
        numofYearsquestion.setText(size + "\n" + "عام ");
    }

    @Override
    public void getValuesofdepandyear(String depName, String yearName) {

        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

        if (controlUI!=null){

            controlUI.showfragUnites(depName,yearName,0);

        }

    }
}
