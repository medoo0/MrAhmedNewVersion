package com.developer.mohamedraslan.hossamexams.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Adapter.Department_Refrence_Adapter;
import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Contracts.Student_Department_Contract;
import com.developer.mohamedraslan.hossamexams.MainPresnter.StudentDepPresnter;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;

import java.util.List;

public class Department_Questions extends Fragment implements Student_Department_Contract.mainView {
    StudentDepPresnter studentDepPresnter;
    RecyclerView recdepQ;
    TextView numberOflistDep_Q;


    //  هذا الفراج حنعرض الاقسام الموجوده وبعد كدا حنعرض بعدها الفصول ومن الفصول حنعرض الوحدات ومن الوحدات حنعرض الاسئله يبقي كدا زي الفل


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentDepPresnter = new StudentDepPresnter(this);




    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v            = inflater.inflate(R.layout.department_questions,container,false);

        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){
            controlUI.enableDrawer(DrawerLayout.LOCK_MODE_UNLOCKED);
        }


        recdepQ           = v.findViewById(R.id.recdepQ);
        ControlPanel.SetNavChecked(2);
        numberOflistDep_Q = v.findViewById(R.id.numberOflistDep_Q);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        ControlPanel.Title.setText("Classrooms ");
        studentDepPresnter.tellModeltoGetDeps();
        return v;
    }

    @Override
    public void depsHere(List<String> deps) {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        Department_Refrence_Adapter department_refrence_adapter = new Department_Refrence_Adapter(deps,getActivity(),this);
        recdepQ.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recdepQ.setLayoutManager(mLayoutManager);
        //**\\
        recdepQ.setAdapter(department_refrence_adapter);


    }

    @Override
    public void problems(String Errors) {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        if (Errors.equals(R.string.netword_error))
            Toast.makeText(getActivity(), "حنشوف مشكله الانترنت", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setSizeOfList(int size) {

        numberOflistDep_Q.setText(size + "\n" + "قسم");

    }

    @Override
    public void getDetailsForDeps(String depsName) {

        ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();

        if (controlUI!=null){

            controlUI.showYearsDetails(depsName,"Department_Questions");

        }

    }
}
