package com.example.mohamedraslan.hossamexams.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohamedraslan.hossamexams.Adapter.MyResult_Rec_Adapter;
import com.example.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyResults extends Fragment implements MyResultContract.view {


    @BindView(R.id.MyResult_rec)
    RecyclerView recyclerView;
    @BindView(R.id.backgroundground)
    ImageView backgroundground;
    MyResult_Rec_Adapter adapter;
    SearchView myresultsearch;
    TextView markresult;
    PublisherAdView mPublisherAdView;


    MyResultContract.presenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment .
         View v = inflater.inflate(R.layout.fragment_my_results, container, false);
        ButterKnife.bind(this , v);
        markresult = v.findViewById(R.id.markresult);
        setHasOptionsMenu(true);
        myresultsearch = v.findViewById(R.id.myresultsearch);
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
        ControlPanel.Title.setText("Results Of Student");
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
        myresultsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (adapter!=null){

                    adapter.getFilter().filter(query);


                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter!=null){

                    adapter.getFilter().filter(newText);

//                    if (adapter.getItemCount()<1){
//
//                        markresult.setText(0 + "\n" + "نتيجه");
//
//                    }
                }
                return false;
            }


        });


        return v;
    }

    @Override
    public void ConfigRecycler(List<Result_Pojo> result) {
        //Stop progress .
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);

            adapter = new MyResult_Rec_Adapter(result, this);
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
    public void showAtherFragment(Result_Pojo result_pojo,String FinalD , String TotalDegree) {

        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

        if (controlUI!=null){


            controlUI.showWrongsforStudent(result_pojo,FinalD , TotalDegree);


        }


    }

    @Override
    public void Problem(String s) {
        //Stop progress .
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        backgroundground.setVisibility(View.VISIBLE);
//        List<Result_Pojo>list1 = new ArrayList<>();
//        backgroundground.setVisibility(View.VISIBLE);
//        adapter = new MyResult_Rec_Adapter(list1,this);
//        //**// reverse Recycler view .
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        mLayoutManager.setReverseLayout(true);
//        mLayoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(mLayoutManager);
//        //**\\
//        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Search:

                myresultsearch.setIconified(false); //Expand the search view

                if (myresultsearch.isShown()){
                    myresultsearch.setVisibility(View.GONE);

                }else {
                    myresultsearch.setVisibility(View.VISIBLE);

                }

                // Do Fragment menu item stuff here
                return true;

            default:
                break;
        }

        return false;
    }
    @Override
    public void numberofExamsForMe(int number) {

        markresult.setText(number + "\n" + "نتيجه");





    }

}
