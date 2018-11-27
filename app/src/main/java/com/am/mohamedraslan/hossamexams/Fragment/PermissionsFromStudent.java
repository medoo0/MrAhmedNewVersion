package com.am.mohamedraslan.hossamexams.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.am.mohamedraslan.hossamexams.Adapter.PermissionExamsAdapter;
import com.am.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.am.mohamedraslan.hossamexams.Contracts.PermissionExamsContract;
import com.am.mohamedraslan.hossamexams.JsonModel.Permission_Refrence;
import com.am.mohamedraslan.hossamexams.MainPresnter.PermissionExamPresnter;
import com.am.mohamedraslan.hossamexams.R;
import com.am.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

import java.util.List;

public class PermissionsFromStudent extends Fragment implements PermissionExamsContract.viewMain {

    PublisherAdView mPublisherAdView;
    RecyclerView permission;
    PermissionExamPresnter presnter;
    SearchView searching;
    TextView markedd;
    ImageView backgroundgroundempty;
    PermissionExamsAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presnter  = new PermissionExamPresnter(this);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);



    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.permiisions_student, container, false);
        presnter.tellModeltoGetRefrence();
        ControlPanel.Title.setText("جميع الطلبات");
        permission       = v.findViewById(R.id.permission);
        mPublisherAdView = v.findViewById(R.id.publisherAdView);
        backgroundgroundempty = v.findViewById(R.id.backgroundgroundempty);
        markedd          = v.findViewById(R.id.markedd);
        searching        = v.findViewById(R.id.searching);
        setHasOptionsMenu(true);
        searching.setQueryHint("قم بالبحث عن طريق الاسم ");

        searching.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                }
                return false;
            }
        });

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

        return  v;

    }

    @Override
    public void allRefrenceHere(List<Permission_Refrence> list) {


        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        adapter = new PermissionExamsAdapter(list,getActivity(),this);
        permission.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        permission.setLayoutManager(mLayoutManager);
        permission.setAdapter(adapter);


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

                searching.setIconified(false); //Expand the search view

                if (searching.isShown()){
                    searching.setVisibility(View.GONE);

                }else {
                    searching.setVisibility(View.VISIBLE);

                }

                // Do Fragment menu item stuff here
                return true;

            default:
                break;
        }

        return false;
    }
    @Override
    public void noRefrenceHere() {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        backgroundgroundempty.setVisibility(View.VISIBLE);
    }

    @Override
    public void ApplicationForExams(String examID,String examName) {

        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

        if (controlUI!=null){

            controlUI.showRequestsFromStudent(examID,"0",examName);

        }



    }

    @Override
    public void numberExams(int numbers) {
        markedd.setText(numbers + "\n" + "أمتحان");
    }
}
