package com.developer.mohamedraslan.hossamexams.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.developer.mohamedraslan.hossamexams.Adapter.AdapterShowUnits;
import com.developer.mohamedraslan.hossamexams.Adapter.ItemTouchHelperStudentMangment;
import com.developer.mohamedraslan.hossamexams.Adapter.RecyclerItemTouchHelperUnit;
import com.developer.mohamedraslan.hossamexams.Adapter.StudentManagementAdapter;
import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Contracts.StudentManagementContract;
import com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.JsonModel.Unites_Model_Json;
import com.developer.mohamedraslan.hossamexams.MainPresnter.StudentMangementPresenter;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StudentManagement extends Fragment implements StudentManagementContract.view , ItemTouchHelperStudentMangment.RecyclerItemTouchHelperListenerStudent {

    @BindView(R.id.Management_Recycler)
    RecyclerView recyclerView;
    SearchView searchview;
    StudentManagementAdapter adapter;
    StudentManagementContract.presenter presenter ;
    AnimatedDialog dialog;
    TextView marked;
    @BindView(R.id.background1)
    ImageView background1;

    String depName = "";
    String yearName = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.student_mangment, container, false);
        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){

            controlUI.enableDisableDrawer(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

        Bundle b = getArguments();
        if (b!=null){

            depName    = b.getString("depName","");
            yearName   = b.getString("yearName","");

        }



        ButterKnife.bind(this,v);
        ControlPanel.Title.setText(R.string.mangeStudent);
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
        if (!depName.equals("") && !yearName.equals("")){

            presenter.callStudentData(depName,yearName);

        }



        //for menu (Search icon)
        setHasOptionsMenu(true);

        return v ;
    }

    @Override
    public void RecyclerConfig(List<FullRegisterForm> Result) {



            adapter = new StudentManagementAdapter(getActivity(),Result,getActivity().getSupportFragmentManager(),"",this);
            recyclerView.setAdapter(adapter);

            ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelperStudentMangment(0, ItemTouchHelper.LEFT, this,Result);
            new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


            //close
            dialog.Close_Dialog();



//        if (!AreInGroup.equals("")&& AreInGroup.equals("myStudents")){
//
//            List<FullRegisterForm>list = new ArrayList<>();
//
//
//                for (int i=0 ; i<Result.size();i++){
//
//
//                    if (Result.get(i).getAreINGroup().equals("Yes")){
//
//                        list.add(Result.get(i));
//
//                    }
//
//            }
//
//
//            if (!list.isEmpty()){
//
//                adapter = new StudentManagementAdapter(getActivity(),list,getActivity().getSupportFragmentManager(),"myStudents",this);
//                recyclerView.setAdapter(adapter);
//                //close
//                dialog.Close_Dialog();
//            }else {
//
//
//                background1.setVisibility(View.VISIBLE);
//                dialog.Close_Dialog();
//
//            }
//
//
//
//
//
//
//
//
//
//
//        }




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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, final int position, final List<FullRegisterForm> units) {

        if (viewHolder instanceof StudentManagementAdapter.ViewHolder) {




            final FullRegisterForm deletedItem = units.get(viewHolder.getAdapterPosition());
            final int deletedIndex             = viewHolder.getAdapterPosition();




            // remove unitFrom database and refresh fragment (::)

            final String name = units.get(viewHolder.getAdapterPosition()).getuID();




            if (getActivity()!=null){


                final AlertDialog aleartDialog = new AlertDialog(getActivity(),getString(R.string.title),"اذا قمت بحذف الطالب فسوف يتم حذف جميع خصوصية الطالب (النتائج ) ولم يتمكن الطالب مره أخري من الدخول هل تريد الحذف؟");
                aleartDialog.show();
                aleartDialog.setCancelable(false);
                aleartDialog.btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aleartDialog.dismiss();



                        //  حنحذف اليوزر من البرنامج خاااااااااااااااااااالص بواسطه الادمن

//                        FirebaseAuth auth = FirebaseAuth.getInstance();
//
//                        auth.deleteUser(name).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//
//                                if (task.isSuccessful()){
//
//                                    Toast.makeText(getActivity(), "تم المسح", Toast.LENGTH_SHORT).show();
//
//                                }
//
//                            }
//                        }).addOnFailureListener( new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//
//                            }
//                        });



//                        //  Removing all details of user //
//                        unitesPresnter.tellModeltoDeleteUnit(depName,yearName,name);


                    }
                });
                aleartDialog.btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (adapter!=null){

                            aleartDialog.dismiss();
                            adapter.removeItem(deletedIndex);
                            adapter.restoreItem(deletedItem, deletedIndex);

                        }





                    }
                });


            }






        }





    }
}
