package com.example.mohamedraslan.hossamexams.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mohamedraslan.hossamexams.Adapter.MyResult_Rec_Adapter;
import com.example.mohamedraslan.hossamexams.Contracts.MyResultContract;
import com.example.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.example.mohamedraslan.hossamexams.MainPresnter.MyResultPresenter;
import com.example.mohamedraslan.hossamexams.R;
import com.example.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyResults extends Fragment implements MyResultContract.view {


    @BindView(R.id.MyResult_rec)
    RecyclerView recyclerView;
    @BindView(R.id.backgroundground)
    ImageView backgroundground;
    PublisherAdView mPublisherAdView;


    MyResultContract.presenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment .
         View v = inflater.inflate(R.layout.fragment_my_results, container, false);
        ButterKnife.bind(this , v);

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
        ControlPanel.Title.setText(R.string.MyResult);
        ControlPanel.SetNavChecked(1);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        //initialize Presenter .
        presenter = new MyResultPresenter(this);

        if(getArguments() != null) {

            String uid = getArguments().getString("uid");
            presenter.getMyResults(uid);

        }
        else {

            presenter.getMyResults(FirebaseAuth.getInstance().getCurrentUser().getUid());

        }

        return v;
    }

    @Override
    public void ConfigRecycler(List<Result_Pojo> result) {
        //Stop progress .
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);

        MyResult_Rec_Adapter adapter = new MyResult_Rec_Adapter(result);

        //**// reverse Recycler view .
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        //**\\
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void Problem(String s) {
        //Stop progress .
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        backgroundground.setVisibility(View.VISIBLE);


    }

}
