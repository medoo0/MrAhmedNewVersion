package com.developer.mohamedraslan.hossamexams.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Adapter.QuestionBankAdapter;
import com.developer.mohamedraslan.hossamexams.Adapter.RecyclerItemTouchHelper;
import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Contracts.QuestionsBankContract;
import com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.developer.mohamedraslan.hossamexams.MainPresnter.Question_BankPresenter;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by microprocess on 2018-10-03.
 */

public class Question_Bank_Frag extends Fragment
                                implements View.OnClickListener
                                         , QuestionsBankContract.view
                                         ,RecyclerItemTouchHelper.RecyclerItemTouchHelperListener
{

    private FloatingActionButton show_addQ_frag;
    private RecyclerView recyclerView;
    SearchView searchaboutquestion;
    QuestionsBankContract.presenter presenter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    AnimatedDialog dialog;
    TextView mark;
    Bundle b;

    String depNameforUnite = "";
    String yearNameforUnit = "";
    String unitName        = "";
        //   public static List<Questions_Form> qestions ;
            QuestionBankAdapter adapter;
            TextView view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference        = firebaseDatabase.getReference(DataBase_Refrences.BANKQUESTIONS.getRef());
         b = getArguments();


        if (b!=null){


            depNameforUnite = b.getString("depName","");
            yearNameforUnit = b.getString("yearName","");
            unitName        = b.getString("unitName","");

        }


       // qestions = new ArrayList<>();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.question_bank_layout,container,false);
        if (b!=null){


            depNameforUnite = b.getString("depName","");
            yearNameforUnit = b.getString("yearName","");
            unitName        = b.getString("unitName","");

        }
        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){

            controlUI.enableDisableDrawer(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        ControlPanel.Title.setText("question bank \n  in this Unit");
        show_addQ_frag = v.findViewById(R.id.show_addQ_frag);
        recyclerView   = v.findViewById(R.id.rec);
        presenter      = new Question_BankPresenter(this);
        mark           = v.findViewById(R.id.mark);
        setHasOptionsMenu(true);
        show_addQ_frag.setOnClickListener(this);
        searchaboutquestion = v.findViewById(R.id.searchaboutquestion);
        //Dialog
        dialog = new AnimatedDialog(getActivity());
        dialog.ShowDialog();
        //call data from firebase .
        presenter.callQuestionData(depNameforUnite,yearNameforUnit,unitName);

        searchaboutquestion.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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



        return  v;
    }

    @Override
    public void onClick(View view) {

        if (view == show_addQ_frag){

            ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.whenClickFAB_showFrag(depNameforUnite,yearNameforUnit,unitName);

            }

        }

    }


            @Override
            public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

                super.onCreateOptionsMenu(menu, inflater);
                inflater.inflate(R.menu.questionbankmenu, menu);



            }
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.Search:

                        searchaboutquestion.setIconified(false); //Expand the search view

                        if (searchaboutquestion.isShown()){
                            searchaboutquestion.setVisibility(View.GONE);

                        }else {
                            searchaboutquestion.setVisibility(View.VISIBLE);

                        }

                        // Do Fragment menu item stuff here
                        return true;


                       case R.id.deletingthisquetion :

                           if (adapter!=null){

                               if (adapter.getItemCount()>0){

                                   if (getActivity()!=null){


                                       final AlertDialog alert  = new AlertDialog(getActivity(),
                                               getActivity().getString(R.string.title),
                                               "هل انت متاكد من حذف جميع الاسئله الخاصه بهذه الوحده.");
                                       alert.show();

                                       alert.btnYes.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

                                               dialog.ShowDialog();
                                               presenter.tellModeltoRemoveAllQuestions(depNameforUnite,yearNameforUnit,unitName);


                                           }
                                       });
                                       alert.btnNo.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               alert.dismiss();
                                           }
                                       });


                                   }




                               }

                           }
                           //  حنمسح كل الاسئله الي موجوده في الوحده
                           break;
                    default:
                        break;
                }

                return false;
            }

                    @Override
            public void RecyclerConfig(final List<Questions_Form> Result) {


                    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
                    new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
                    LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setStackFromEnd(true);
                    linearLayoutManager.setReverseLayout(true);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter     = new QuestionBankAdapter(Result,getActivity(),this,depNameforUnite,yearNameforUnit,unitName);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    dialog.Close_Dialog();
                  //  qestions = Result ;




            }

            @Override
            public void problem(String problem) {
                //close
                dialog.Close_Dialog();
                Toast.makeText(getActivity(), problem + "", Toast.LENGTH_LONG).show();

            }



            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {



                 view    = viewHolder.itemView.findViewById(R.id.tx);
                 presenter.addQuestionToAddTestRecycler(QuestionBankAdapter.qestions
                        .get(viewHolder.getAdapterPosition()).getQuestionID(),depNameforUnite,yearNameforUnit,unitName);


            }

            @Override
            public void sentSuccessfully(String Result) {


                view.setText("Sent");

            }

            @Override
            public void updateFragbyValuesTogoEditFrag(String questionID,String depName , String yearname , String unitName) {

                ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

                if (controlUI!=null){

                    controlUI.editQuestions(questionID,"Editing",depName,yearname,unitName);

                }


            }

            @Override
            public void removingQuestion(final String questionID, final int position, final String depName , final String yearName , final String unitName) {

                 final Question_BankPresenter presenter = new Question_BankPresenter(this);
                    if(getActivity()!= null) {

                        final AlertDialog alertDialog = new AlertDialog(getActivity(), "Warning", " Are you sure you want to delete the question?");
                        alertDialog.show();
                        alertDialog.btnYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                //delete
                                presenter.tellModletoDeleteQuestion(reference, questionID, position,depName,yearName,unitName);
                                alertDialog.dismiss();

                                dialog.ShowDialog();

                            }
                        });

                    }
                }




            @Override
            public void Q_Removed_InUI(int position, String depName , String yearName , String unitName) {


                dialog.Close_Dialog();



                ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();

                if (controlUI!=null){


                    controlUI.removedsussesswewillupdateQuestionBankFragment(depName,yearName,unitName,"");

                }


//                if(getActivity() != null) {
//
//                    Toast.makeText(getActivity(), "question deleted .", Toast.LENGTH_SHORT).show();
//                    if (adapter != null) {
//
//                        //adapter.remove(position);
//
//                        getActivity().getSupportFragmentManager().popBackStack();
//                        getActivity().getSupportFragmentManager()
//                                .beginTransaction()
//                                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
//                                .replace(R.id.Exam_Frame,new Question_Bank_Frag())
//                                .addToBackStack(null)
//                                .commit();
//
//
//                    }
//                }
            }

            @Override
            public void Q_notRemoved_InUI (String depName , String yearName , String unitName) {
                dialog.Close_Dialog();


                ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();

                if (controlUI!=null){


                    controlUI.removedsussesswewillupdateQuestionBankFragment(depName,yearName,unitName,"");

                }


//
//                if(getActivity() != null) {
//                    Toast.makeText(getActivity(), "problem", Toast.LENGTH_SHORT).show();
//
//                    getActivity().getSupportFragmentManager().popBackStack();
//                    getActivity().getSupportFragmentManager()
//                            .beginTransaction()
//                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
//                            .replace(R.id.Exam_Frame,new Question_Bank_Frag())
//                            .addToBackStack(null)
//                            .commit();
//
//                }


            }

    @Override
    public void allQuestioninUnitRemoved(String depName, String yearName, String unitName) {

        dialog.Close_Dialog();

        ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();

        if (controlUI!=null){

            controlUI.removedsussesswewillupdateQuestionBankFragment(depName,yearName,unitName,"allQRemoved");

        }


    }

    @Override
    public void allQuestioninUnitNotRemoved(String depName, String yearName, String unitName) {

        dialog.Close_Dialog();

        ControlPanelContract.ControlUI controlUI  = (ControlPanelContract.ControlUI) getActivity();

        if (controlUI!=null){

            controlUI.removedsussesswewillupdateQuestionBankFragment(depName,yearName,unitName,"NoRemoved");

        }



    }

    @Override
    public void nuberQuestions(int number) {

        mark.setText(number + "\n" + "سؤال");


    }


}
