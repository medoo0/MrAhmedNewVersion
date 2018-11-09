package com.example.mohamedraslan.hossamexams.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mohamedraslan.hossamexams.Adapter.StudentResult_Rec_Adapter;
import com.example.mohamedraslan.hossamexams.Adapter.ViewHolder3;
import com.example.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.example.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.example.mohamedraslan.hossamexams.R;
import com.example.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentResult extends Fragment {

    @BindView(R.id.Student_Result_Rec)
    RecyclerView recyclerView;

    @BindView(R.id.background)
    ImageView background;

    String ExamID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_student_result, container, false);
        ButterKnife.bind(this, v);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        ControlPanel.Title.setText(R.string.results);
         if (getArguments() != null) {
            ExamID = getArguments().getString("ExamID");
         }

         Query query = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.RESULT.getRef())
                .orderByChild("examID").equalTo(ExamID);

         StudentResult_Rec_Adapter adapter = new StudentResult_Rec_Adapter(Result_Pojo.class,
                R.layout.student_result_rec_layout,ViewHolder3.class,query,getActivity().getSupportFragmentManager());

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
                if(dataSnapshot.exists()){
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

        return v;
    }


}
