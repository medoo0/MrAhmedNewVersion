package com.developer.mohamedraslan.hossamexams.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

public class Students_Departments extends Fragment implements View.OnClickListener,Student_Department_Contract.mainView{


    FloatingActionButton add_departments;
    StudentDepPresnter studentDepPresnter;
    RecyclerView recdep;
    TextView numberOflistDep;

    //      هنا مخصص لجميع الفرق الدراسيه المتواجده في البرنامج  ....


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentDepPresnter = new StudentDepPresnter(this);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v          = inflater.inflate(R.layout.student_department_layout,container,false);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        ControlPanel.Title.setText("Classrooms ");
        studentDepPresnter.tellModeltoGetDeps();
        add_departments = v.findViewById(R.id.add_departments);
        recdep          = v.findViewById(R.id.recdep);
        numberOflistDep = v.findViewById(R.id.numberOflistDep);
        add_departments.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {



        // show dialog to adding ... (   :) (:  )


        if (view == add_departments){


            ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.showDialogToAddDeps();


            }

        }

    }

    @Override
    public void depsHere(List<String> deps) {


        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        Department_Refrence_Adapter department_refrence_adapter = new Department_Refrence_Adapter(deps,getActivity(),this);
        recdep.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recdep.setLayoutManager(mLayoutManager);
        //**\\
        recdep.setAdapter(department_refrence_adapter);
    }

    @Override
    public void problems(String Errors) {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        if (Errors.equals(R.string.netword_error))
        Toast.makeText(getActivity(), "حنشوف مشكله الانترنت", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setSizeOfList(int size) {

        numberOflistDep.setText(size + "\n" + "قسم");

    }

    @Override
    public void getDetailsForDeps(String depsName) {

        ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();

        if (controlUI!=null){

            controlUI.showYearsDetails(depsName);


        }



    }
}
