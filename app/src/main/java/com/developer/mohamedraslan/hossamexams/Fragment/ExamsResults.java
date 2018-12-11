package com.developer.mohamedraslan.hossamexams.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.developer.mohamedraslan.hossamexams.Adapter.ExamsResults_Rec_Adapter;
import com.developer.mohamedraslan.hossamexams.Adapter.ViewHolder2;
import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.Results_References;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExamsResults extends Fragment {

    @BindView(R.id.Exam_Result_rec)
    RecyclerView recyclerView ;
    @BindView(R.id.background)
    ImageView background;


    String depName = "" , yearname = ""  , unitName = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){

            controlUI.enableDisableDrawer(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

        if (getArguments()!=null){


            depName    = getArguments().getString("depNameExamResult" ,"");
            yearname   = getArguments().getString("yearNameExamResult","");
            unitName   = getArguments().getString("unitNameExamResult","");


        }


        ControlPanel.Title.setText("Result Of Students in " + unitName);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment .
         View v =  inflater.inflate(R.layout.fragment_exams_results, container, false);
        ButterKnife.bind(this,v);

        if (!depName.equals("") && !yearname.equals("") && ! unitName.equals("")){

            Query query = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.ResultsRef.getRef()).child(depName).child(yearname).child(unitName);
            ExamsResults_Rec_Adapter adapter = new ExamsResults_Rec_Adapter(Results_References.class,R.layout.examresult_rec_layout,
                    ViewHolder2.class,query,getActivity().getSupportFragmentManager(),depName,yearname,unitName,getActivity());
            //**// reverse Recycler view .
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(mLayoutManager);
            //**\\

            recyclerView.setAdapter(adapter);

            query.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
                        background.setVisibility(View.GONE);
                    }
                    else {
                        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
                        background.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        }


        return v ;
    }

}
