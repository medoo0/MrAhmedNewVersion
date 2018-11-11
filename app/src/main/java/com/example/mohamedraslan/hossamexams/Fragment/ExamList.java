package com.example.mohamedraslan.hossamexams.Fragment;


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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mohamedraslan.hossamexams.Adapter.ExamList_Rec_Adapter;
import com.example.mohamedraslan.hossamexams.Adapter.ViewHolder;
import com.example.mohamedraslan.hossamexams.Contracts.ExamListContract;
import com.example.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.example.mohamedraslan.hossamexams.JsonModel.AddExam_pojo;
import com.example.mohamedraslan.hossamexams.MainPresnter.ExamListPresenter;
import com.example.mohamedraslan.hossamexams.R;
import com.example.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExamList extends Fragment implements ExamListContract.view{
    @BindView(R.id.Exam_List_rec)
    RecyclerView recyclerView;

    @BindView(R.id.FloatActionbutton)
    FloatingActionButton actionButton;

    @BindView(R.id.backgroundground)
    ImageView background;
    PublisherAdView mPublisherAdView;

    private FirebaseAuth auth;
    ExamListContract.presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth  = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_exam_list, container, false);

         ButterKnife.bind(this,v);
         ControlPanel.Title.setText(R.string.examList);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        ControlPanel.SetNavChecked(0);

        mPublisherAdView = v.findViewById(R.id.publisherAdView);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        mPublisherAdView.loadAd(adRequest);
        mPublisherAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Toast.makeText(getActivity(),errorCode + "Fauilure", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });



        //Get Time From Server Then run Recycler .
        presenter = new ExamListPresenter(this);
        presenter.GetTime();

        //Admin Check .
        presenter.CheckifAdmin(auth.getCurrentUser().getUid());



         return v;
    }

    @Override
    public void ConfigRecyceler(String date ){

        Query reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.EXAMS.getRef());
        recyclerView.setHasFixedSize(true);
        //**// reverse Recycler view .
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        //**\\
        ExamList_Rec_Adapter adapter = new ExamList_Rec_Adapter(AddExam_pojo.class,R.layout.examlist_rec_layout,
               ViewHolder.class,reference,date,getActivity());

        recyclerView.setAdapter(adapter);

        //BACKGROUND
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    ControlPanel.progressBar.setVisibility(View.INVISIBLE);
                    background.setVisibility(View.GONE);

                }
                else {
                    background.setVisibility(View.VISIBLE);
                    ControlPanel.progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @OnClick(R.id.FloatActionbutton)
    void OpenAddExam(View view){

        getActivity().getSupportFragmentManager().popBackStack();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Exam_Frame,new addExam())
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void ShowAdminTools() {

        if(getActivity() != null)
            if(!getActivity().isFinishing())
                actionButton.setVisibility(View.VISIBLE);

    }
}
