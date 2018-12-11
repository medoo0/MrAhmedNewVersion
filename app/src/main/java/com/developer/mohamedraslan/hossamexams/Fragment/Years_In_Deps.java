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
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Adapter.Adapter_show_YearsInDeps;
import com.developer.mohamedraslan.hossamexams.Adapter.Department_Refrence_Adapter;
import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Contracts.MySalaryComp;
import com.developer.mohamedraslan.hossamexams.Contracts.Years_inDepsContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.developer.mohamedraslan.hossamexams.MainPresnter.Year_Presnter;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;

import java.util.Collections;
import java.util.List;

public class Years_In_Deps extends Fragment implements Years_inDepsContract.ViewMainYear {

    private  String depsYear = "" ;
    private  RecyclerView    yearsindep;
    private  TextView        numofYears;
    private  ImageView       resultYear;
    private  Year_Presnter   year_presnter;
    private RelativeLayout   snackBBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v     = inflater.inflate(R.layout.years_in_deps,container,false);
        ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){
            controlUI.enableDrawer(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
        Bundle b = getArguments();

        if (b!=null){

            depsYear      = b.getString("depsYear","");
            year_presnter = new Year_Presnter(this);

        }



        yearsindep = v.findViewById(R.id.yearsindep);
        numofYears = v.findViewById(R.id.numofYears);
        snackBBar  = v.findViewById(R.id.snackBBar);
        resultYear = v.findViewById(R.id.resultYear);
        ControlPanel.SetNavChecked(0);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);

        if (!depsYear.equals("")){
            ControlPanel.Title.setText(depsYear);
            year_presnter.tellModeltogetYears(depsYear);
        }

        return v;
    }

    @Override
    public void exisityear(List<Year_modle_json> years) {


        Collections.sort(years,new MySalaryComp());

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        Adapter_show_YearsInDeps department_refrence_adapter = new Adapter_show_YearsInDeps(getActivity(),years,this,depsYear);
        yearsindep.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        department_refrence_adapter.notifyDataSetChanged();
        yearsindep.setLayoutManager(mLayoutManager);
        //**\\

        yearsindep.setAdapter(department_refrence_adapter);

        // الداتا حتتعرض بقاااا بتاعه القسم كلها

    }

    @Override
    public void problemsyearnotFound() {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        resultYear.setVisibility(View.VISIBLE);
    }

    @Override
    public void connectionPoor(String error) {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
//        Snackbar snackbar = Snackbar
//                .make(snackBBar, "لقد حدث مشكله في الاتصال.", Snackbar.LENGTH_LONG);
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
       //  حنعرض حاجه لان النت حيكون ضعيف

    }

    @Override
    public void getSizeofarray(int size) {
        numofYears.setText(size + "\n" + "عام ");
    }

    @Override
    public void getValuesofdepandyear(String depName, String yearName) {


        ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){

            controlUI.showStudentMangmentFragmenttt(depName,yearName);

        }

        Toast.makeText(getActivity(), "انا هنااااااااااااااااا", Toast.LENGTH_SHORT).show();
        //
    }


}
