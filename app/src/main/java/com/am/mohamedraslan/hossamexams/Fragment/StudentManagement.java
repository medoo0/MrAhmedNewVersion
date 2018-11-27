package com.am.mohamedraslan.hossamexams.Fragment;


import android.os.Bundle;
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
import android.widget.Toast;


import com.am.mohamedraslan.hossamexams.Adapter.StudentManagementAdapter;
import com.am.mohamedraslan.hossamexams.Contracts.StudentManagementContract;
import com.am.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.am.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.am.mohamedraslan.hossamexams.MainPresnter.StudentMangementPresenter;
import com.am.mohamedraslan.hossamexams.R;
import com.am.mohamedraslan.hossamexams.Views.ControlPanel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StudentManagement extends Fragment implements StudentManagementContract.view {

    @BindView(R.id.Management_Recycler)
    RecyclerView recyclerView;
    SearchView searchview;
    StudentManagementAdapter adapter;
    StudentManagementContract.presenter presenter ;
    AnimatedDialog dialog;
    TextView marked;
    @BindView(R.id.background1)
    ImageView background1;
    String AreInGroup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b!=null)
        AreInGroup = getArguments().getString("what","");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.student_mangment, container, false);
        ButterKnife.bind(this,v);
        ControlPanel.Title.setText(R.string.mangeStudent);
        ControlPanel.SetNavChecked(5);
        marked = v.findViewById(R.id.marked);
        searchview = v.findViewById(R.id.search);

        searchview.setQueryHint("قم بالبحث عن طريق الاسم ");

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        presenter = new StudentMangementPresenter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Dialog
        dialog = new AnimatedDialog(getActivity());
        dialog.ShowDialog();

        //call data from firebase .
        presenter.callStudentData();

        //for menu (Search icon)
        setHasOptionsMenu(true);

        return v ;
    }

    @Override
    public void RecyclerConfig(List<FullRegisterForm> Result) {


        if (!AreInGroup.equals("")&& AreInGroup.equals("allStudents")){



            adapter = new StudentManagementAdapter(getActivity(),Result,getActivity().getSupportFragmentManager(),"",this);
            recyclerView.setAdapter(adapter);
            //close
            dialog.Close_Dialog();

        }

        if (!AreInGroup.equals("")&& AreInGroup.equals("myStudents")){

            List<FullRegisterForm>list = new ArrayList<>();


                for (int i=0 ; i<Result.size();i++){


                    if (Result.get(i).getAreINGroup().equals("Yes")){

                        list.add(Result.get(i));

                    }

            }


            if (!list.isEmpty()){

                adapter = new StudentManagementAdapter(getActivity(),list,getActivity().getSupportFragmentManager(),"myStudents",this);
                recyclerView.setAdapter(adapter);
                //close
                dialog.Close_Dialog();
            }else {


                background1.setVisibility(View.VISIBLE);
                dialog.Close_Dialog();

            }










        }




    }

    @Override
    public void problem(String problem) {
        //close
        dialog.Close_Dialog();
        Toast.makeText(getActivity(), problem + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void numberStudent(int number) {
        marked.setText(number + "\n" + "طالب");
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

                searchview.setIconified(false); //Expand the search view

                if (searchview.isShown()){
                    searchview.setVisibility(View.GONE);
                }else {
                    searchview.setVisibility(View.VISIBLE);
                }

                // Do Fragment menu item stuff here
                return true;

            default:
                break;
        }

        return false;
    }
}
