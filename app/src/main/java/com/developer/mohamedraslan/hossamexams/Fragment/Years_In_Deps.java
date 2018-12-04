package com.developer.mohamedraslan.hossamexams.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
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
import com.developer.mohamedraslan.hossamexams.Contracts.Years_inDepsContract;
import com.developer.mohamedraslan.hossamexams.MainPresnter.Year_Presnter;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;

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

        Bundle b = getArguments();

        if (b!=null){

            depsYear      = b.getString("depsYear","");
            year_presnter = new Year_Presnter(this);

        }



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v     = inflater.inflate(R.layout.years_in_deps,container,false);
        yearsindep = v.findViewById(R.id.yearsindep);
        numofYears = v.findViewById(R.id.numofYears);
        snackBBar  = v.findViewById(R.id.snackBBar);
        resultYear = v.findViewById(R.id.resultYear);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);

        if (!depsYear.equals("")){
            ControlPanel.Title.setText(depsYear);
            year_presnter.tellModeltogetYears(depsYear);
        }

        return v;
    }

    @Override
    public void exisityear(List<String> years) {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);

        Snackbar snackbar = Snackbar
                .make(snackBBar, "لقد تم العثور علي " + years.size() + "عنصر" , Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
        if (getActivity()!=null){

            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
            textView.setTypeface(font);

        }

        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
        snackbar.show();


        Adapter_show_YearsInDeps department_refrence_adapter = new Adapter_show_YearsInDeps(getActivity(),years,this);
        yearsindep.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        yearsindep.setLayoutManager(mLayoutManager);
        //**\\
        yearsindep.setAdapter(department_refrence_adapter);

        // الداتا حتتعرض بقاااا بتاعه القسم كلها

    }

    @Override
    public void problemsyearnotFound() {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        resultYear.setVisibility(View.VISIBLE);

        Snackbar snackbar = Snackbar
                .make(snackBBar, "لا يوجد فصول بهذا القسم.", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
        if (getActivity()!=null){

            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
            textView.setTypeface(font);

        }

        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
        snackbar.show();

        //  حنعرض صوره بتدل ان مفيش حاجه جات من علي السرفر من السنين

    }

    @Override
    public void connectionPoor(String error) {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        Snackbar snackbar = Snackbar
                .make(snackBBar, "لقد حدث مشكله في الاتصال.", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
        if (getActivity()!=null){

            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"atherfont.ttf");
            textView.setTypeface(font);

        }
        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
        snackbar.show();
       //  حنعرض حاجه لان النت حيكون ضعيف

    }

    @Override
    public void getSizeofarray(int size) {
        numofYears.setText(size + "\n" + "عام ");
    }
}
