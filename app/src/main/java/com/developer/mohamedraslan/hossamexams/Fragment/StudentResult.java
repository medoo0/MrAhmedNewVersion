package com.developer.mohamedraslan.hossamexams.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
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

import com.developer.mohamedraslan.hossamexams.Adapter.StudentResult_Rec_Adapter;
import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Contracts.StudentResultContract;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.developer.mohamedraslan.hossamexams.JsonModel.WorngQestion;
import com.developer.mohamedraslan.hossamexams.MainPresnter.StudentResultPresnter;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class StudentResult extends Fragment implements StudentResultContract.MainView {

    @BindView(R.id.Student_Result_Rec)
    RecyclerView recyclerView;
    TextView marking;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    SearchView searchresult;
    @BindView(R.id.background)
    ImageView background;
    StudentResult_Rec_Adapter adapter;
    String ExamID , deName , yearNamee  , unitName ;
    StudentResultPresnter studentResultPresnter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase        = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference(DataBase_Refrences.RESULT.getRef());
        studentResultPresnter  = new StudentResultPresnter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){

            controlUI.enableDisableDrawer(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        View v = inflater.inflate(R.layout.fragment_student_result, container, false);
        ButterKnife.bind(this, v);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);
        marking = v.findViewById(R.id.marking);
        setHasOptionsMenu(true);
        searchresult = v.findViewById(R.id.searchresult);
        ControlPanel.Title.setText(R.string.results);

         if (getArguments() != null) {

            ExamID    = getArguments().getString("ExamID");
            deName    = getArguments().getString("studentResultinDepname");
            yearNamee = getArguments().getString("studentResultinYearName");
            unitName  = getArguments().getString("studentResultinUnitName");

            studentResultPresnter.tellModeltoGetDataResults(reference,ExamID,background,deName,yearNamee,unitName);


         }





        searchresult.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        inflater.inflate(R.menu.search, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.Search:

                searchresult.setIconified(false); //Expand the search view

                if (searchresult.isShown()){
                    searchresult.setVisibility(View.GONE);

                }else {
                    searchresult.setVisibility(View.VISIBLE);

                }

                // Do Fragment menu item stuff here
                return true;

            default:
                break;
        }

        return false;
        }



    @Override
    public void myResultResult(List<Result_Pojo> list, ImageView imageView) {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        adapter = new StudentResult_Rec_Adapter(list,getActivity(),this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void noDataHere(ImageView imageView) {

        if (!imageView.isShown()){

            imageView.setVisibility(View.VISIBLE);
            ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void problemss(String Error) {

    }

    @Override
    public void numberResult(int number) {

            marking.setText(number + "\n" + "نتيجه");

    }

    @Override
    public void showErrorsFragment(String name, String finalDegree, String total, String examID, ArrayList<WorngQestion> arrayList, Integer imageTag, String uID, CircleImageView imageView) {


        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();


        if (controlUI!= null){

            controlUI.showFragmentWrongs(name,finalDegree,total,examID,arrayList,imageTag,uID,imageView,deName,yearNamee,unitName);

        }

    }
}
