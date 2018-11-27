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

import com.am.mohamedraslan.hossamexams.Adapter.AdapterExamsStudents;
import com.am.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.am.mohamedraslan.hossamexams.Contracts.RequestFromStudentToExamWhatContract;
import com.am.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.am.mohamedraslan.hossamexams.JsonModel.PermissionUserEntering;
import com.am.mohamedraslan.hossamexams.MainPresnter.RequestFromStudentToExamWhatPresnter;
import com.am.mohamedraslan.hossamexams.R;
import com.am.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

import java.util.ArrayList;
import java.util.List;

public class RequestFromStudentToExamWhat extends Fragment implements RequestFromStudentToExamWhatContract.MainView{


    RecyclerView permissionstudentrec;
    SearchView searchingstudent;
    ImageView background111;
    TextView txmarks;
    PublisherAdView mPublisherAdView;
    RequestFromStudentToExamWhatPresnter presnter;
    String examID ,examName;
    AdapterExamsStudents adapter;
    AnimatedDialog animatedDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animatedDialog = new AnimatedDialog(getActivity());
        Bundle b = getArguments();
        if (b!=null){

            examName = b.getString("name"    ,"");
            examID   = b.getString("examid"  ,"");
            presnter = new RequestFromStudentToExamWhatPresnter(this);
            presnter.tellModeltoGetStudents(examID);


        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.requests_from_student_for_anyexam,container,false);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        ControlPanel.Title.setText("طلبه قد إنتهي عليهم الوقت.");
        ControlPanel.SetNavChecked(6);
        setHasOptionsMenu(true);
        permissionstudentrec = v.findViewById(R.id.permissionstudentrec);
        searchingstudent     = v.findViewById(R.id.searchingstudent);
        mPublisherAdView     = v.findViewById(R.id.publisherAdView);
        background111        = v.findViewById(R.id.background111);
        txmarks              = v.findViewById(R.id.txmarks);
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
        searchingstudent.setQueryHint("قم بالبحث عن طريق الاسم ");

        searchingstudent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        return v;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.searchone, menu);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.Search:

                searchingstudent.setIconified(false); //Expand the search view

                if (searchingstudent.isShown()){
                    searchingstudent.setVisibility(View.GONE);

                }else {
                    searchingstudent.setVisibility(View.VISIBLE);

                }
                // Do Fragment menu item stuff here
                return true;

            case R.id.allowall:

                if (adapter!=null){

                    if (adapter.getItemCount()>0){

                        animatedDialog.ShowDialog();
                        adapter.allowAllusers(examID,animatedDialog);

                    }


                }

                break;

            default:
                break;
        }

        return false;
    }

    @Override
    public void Studenssssss(List <PermissionUserEntering> list) {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        adapter = new AdapterExamsStudents(list,getActivity(),examID,this,examName);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        permissionstudentrec.setLayoutManager(mLayoutManager);
        permissionstudentrec.setAdapter(adapter);


    }

    @Override
    public void notFoundAnythings() {

        List<PermissionUserEntering>listempty = new ArrayList<>();
        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        background111.setVisibility(View.VISIBLE);
        adapter = new AdapterExamsStudents(listempty,getActivity(),examID,this,examName);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        permissionstudentrec.setLayoutManager(mLayoutManager);
        permissionstudentrec.setAdapter(adapter);

    }


    @Override
    public void refreshFragment(String nameee) {


        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){


            controlUI.showRequestsFromStudent(examID,"1",examName);

        }



    }

    @Override
    public void numberStu(int number) {
        txmarks.setText(number + "\n" + "طالب");
    }
}
