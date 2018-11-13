package com.example.mohamedraslan.hossamexams.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

import com.example.mohamedraslan.hossamexams.Adapter.QuestionBankAdapter;
import com.example.mohamedraslan.hossamexams.Adapter.RecyclerItemTouchHelper;
import com.example.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.example.mohamedraslan.hossamexams.Contracts.QuestionsBankContract;
import com.example.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.example.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.example.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.example.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.example.mohamedraslan.hossamexams.MainPresnter.Question_BankPresenter;
import com.example.mohamedraslan.hossamexams.R;
import com.example.mohamedraslan.hossamexams.Views.ControlPanel;
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
        //   public static List<Questions_Form> qestions ;
            QuestionBankAdapter adapter;
            TextView view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference        = firebaseDatabase.getReference(DataBase_Refrences.BANKQUESTIONS.getRef());
       // qestions = new ArrayList<>();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.question_bank_layout,container,false);
        ControlPanel.Title.setText(R.string.questions);
        ControlPanel.SetNavChecked(3);
        show_addQ_frag = v.findViewById(R.id.show_addQ_frag);
        recyclerView   = v.findViewById(R.id.rec);
        presenter      = new Question_BankPresenter(this);
        setHasOptionsMenu(true);
        show_addQ_frag.setOnClickListener(this);
        searchaboutquestion = v.findViewById(R.id.searchaboutquestion);
        //Dialog
        dialog = new AnimatedDialog(getActivity());
        dialog.ShowDialog();
        //call data from firebase .
        presenter.callQuestionData();

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

                controlUI.whenClickFAB_showFrag();

            }

        }

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

                        searchaboutquestion.setIconified(false); //Expand the search view

                        if (searchaboutquestion.isShown()){
                            searchaboutquestion.setVisibility(View.GONE);

                        }else {
                            searchaboutquestion.setVisibility(View.VISIBLE);

                        }

                        // Do Fragment menu item stuff here
                        return true;

                    default:
                        break;
                }

                return false;
            }

                    @Override
            public void RecyclerConfig(final List<Questions_Form> Result) {


                    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
                    new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter     = new QuestionBankAdapter(Result,getActivity(),this);
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
                        .get(viewHolder.getAdapterPosition()).getQuestionID());


            }

            @Override
            public void sentSuccessfully(String Result) {


                view.setText("Sent");

            }

            @Override
            public void updateFragbyValuesTogoEditFrag(String questionID) {

                ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

                if (controlUI!=null){

                    controlUI.editQuestions(questionID,"Editing");

                }


            }

            @Override
            public void removingQuestion(final String questionID, final int position) {

                 final Question_BankPresenter presenter = new Question_BankPresenter(this);
                    if(getActivity()!= null) {

                        final AlertDialog alertDialog = new AlertDialog(getActivity(), "Warning", " Are you sure you want to delete the question");
                        alertDialog.show();
                        alertDialog.btnYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                //delete
                                presenter.tellModletoDeleteQuestion(reference, questionID, position);
                                alertDialog.dismiss();

                                dialog.ShowDialog();

                            }
                        });

                    }
                }




            @Override
            public void Q_Removed_InUI(int position) {
                dialog.Close_Dialog();
                if(getActivity() != null) {

                    Toast.makeText(getActivity(), "question deleted .", Toast.LENGTH_SHORT).show();
                    if (adapter != null) {

                        //adapter.remove(position);

                        getActivity().getSupportFragmentManager().popBackStack();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                                .replace(R.id.Exam_Frame,new Question_Bank_Frag())
                                .addToBackStack(null)
                                .commit();


                    }
                }
            }

            @Override
            public void Q_notRemoved_InUI() {
                dialog.Close_Dialog();
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), "problem", Toast.LENGTH_SHORT).show();

                    getActivity().getSupportFragmentManager().popBackStack();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                            .replace(R.id.Exam_Frame,new Question_Bank_Frag())
                            .addToBackStack(null)
                            .commit();

                }
            }


        }
