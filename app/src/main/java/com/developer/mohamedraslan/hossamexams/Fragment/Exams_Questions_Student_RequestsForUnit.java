package com.developer.mohamedraslan.hossamexams.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Contracts.Exam_Question_Request_forUnitInterface;
import com.developer.mohamedraslan.hossamexams.MainPresnter.Exams_Question_Student_RequestsForUnitsssPresnter;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.firebase.auth.FirebaseAuth;

public class Exams_Questions_Student_RequestsForUnit extends Fragment implements View.OnClickListener , Exam_Question_Request_forUnitInterface.ExamQuestionRequestUI{


    CardView presstoshowexams ,presstoshowQuestions,presstoShowResults,presstoShowstudentRequest,presstoshowMyResult,CardContainsyearName;

    String depName ="" , yearName ="" , unitName ="" ;

    TextView unitNameeeeTv , yearNameeeTv;
    FirebaseAuth auth;
    Exams_Question_Student_RequestsForUnitsssPresnter exams_question_student_requestsForUnitsssPresnter;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ControlPanel.progressBar.setVisibility(View.VISIBLE);







        exams_question_student_requestsForUnitsssPresnter = new Exams_Question_Student_RequestsForUnitsssPresnter(this);
        auth                                              = FirebaseAuth.getInstance();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v                    = inflater.inflate(R.layout.exams_questions_student_for_unit,container,false);

        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();
        if (controlUI!=null){

            controlUI.enableDisableDrawer(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        if (auth!=null){

            exams_question_student_requestsForUnitsssPresnter.tellModeltoCheckNowbeforeanyThing(auth.getCurrentUser().getUid());

        }


        ControlPanel.Title.setText("Contents of unity");


        if (getArguments()!=null){

            depName   = getArguments().getString("depName","");
            yearName  = getArguments().getString("yearName","");
            unitName  = getArguments().getString("unitName","");

        }
        presstoshowexams          = v.findViewById(R.id.presstoshowexams);
        presstoshowQuestions      = v.findViewById(R.id.presstoshowQuestions);
        presstoShowResults        = v.findViewById(R.id.presstoShowResults);
        presstoShowstudentRequest = v.findViewById(R.id.presstoShowstudentRequest);
        presstoshowMyResult       = v.findViewById(R.id.presstoshowMyResult);
        CardContainsyearName      = v.findViewById(R.id.CardContainsyearName);
        yearNameeeTv              = v.findViewById(R.id.yearNameeeTv);
        unitNameeeeTv             = v.findViewById(R.id.unitNameeeeTv);
        unitNameeeeTv.setX(1000);
        unitNameeeeTv.animate().translationXBy(-1000).setDuration(800);
        unitNameeeeTv.setScaleX(.9f);
        unitNameeeeTv.setScaleY(.9f);
        yearNameeeTv.animate().scaleX(1f).scaleY(1f).setDuration(500);
        yearNameeeTv.setX(-1000);
        yearNameeeTv.animate().translationXBy(1000).setDuration(800);
        yearNameeeTv.setScaleX(.9f);
        yearNameeeTv.setScaleY(.9f);
        yearNameeeTv.animate().scaleX(1f).scaleY(1f).setDuration(500);
        yearNameeeTv.setText(yearName);
        unitNameeeeTv.setText(unitName);
        animateCards(presstoshowexams,presstoshowQuestions,presstoShowResults,presstoShowstudentRequest,presstoshowMyResult,CardContainsyearName);
        presstoshowexams.setOnClickListener(this);
        presstoshowQuestions.setOnClickListener(this);
        presstoShowResults.setOnClickListener(this);
        presstoShowstudentRequest.setOnClickListener(this);
        presstoshowMyResult.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View view) {



        if (view == presstoshowexams){

            ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.showExamListForUnit(depName,yearName,unitName);

            }

        }



        if (view  ==  presstoshowMyResult){

            ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.showMyresultFragment(depName,yearName,unitName);

            }


        }



        if (view == presstoshowQuestions){

            ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.showQforUnitiRefere(depName,yearName,unitName);

            }




        }

        if (view == presstoShowResults){

            ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.showResultOfStudentInThisUnitFragment(depName,yearName,unitName);

            }

        }



        if (view == presstoShowstudentRequest){


            ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

            if (controlUI!=null){

                controlUI.showRequestStudentFragment(depName,yearName,unitName);

            }



        }



    }




    public void animateCards(CardView presstoshowexams,CardView presstoshowQuestions,CardView presstoShowResults,
                             CardView presstoShowstudentRequest,CardView presstoshowMyResult,CardView CardContainsyearName){




        CardContainsyearName.setScaleX(.9f);
        CardContainsyearName.setScaleY(.9f);
        CardContainsyearName.animate().scaleX(1f).scaleY(1f).setDuration(500);



        presstoshowexams.setScaleX(.9f);
        presstoshowexams.setScaleY(.9f);
        presstoshowexams.animate().scaleX(1f).scaleY(1f).setDuration(500);



        presstoshowMyResult.setScaleX(.9f);
        presstoshowMyResult.setScaleY(.9f);
        presstoshowMyResult.animate().scaleX(1f).scaleY(1f).setDuration(500);




        presstoshowQuestions.setScaleX(.9f);
        presstoshowQuestions.setScaleY(.9f);
        presstoshowQuestions.animate().scaleX(1f).scaleY(1f).setDuration(500);




        presstoShowResults.setScaleX(.9f);
        presstoShowResults.setScaleY(.9f);
        presstoShowResults.animate().scaleX(1f).scaleY(1f).setDuration(500);



        presstoShowstudentRequest.setScaleX(.9f);
        presstoShowstudentRequest.setScaleY(.9f);
        presstoShowstudentRequest.animate().scaleX(1f).scaleY(1f).setDuration(500);




    }

    @Override
    public void yesIsAdminShowButton() {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        presstoshowexams.setVisibility(View.VISIBLE);
        presstoshowMyResult.setVisibility(View.VISIBLE);
        presstoshowQuestions.setVisibility(View.VISIBLE);
        presstoShowResults.setVisibility(View.VISIBLE);
        presstoShowstudentRequest.setVisibility(View.VISIBLE);

    }

    @Override
    public void yesThisUserHideButton() {

        ControlPanel.progressBar.setVisibility(View.INVISIBLE);
        presstoshowexams.setVisibility(View.VISIBLE);
        presstoshowMyResult.setVisibility(View.VISIBLE);
        presstoshowQuestions.setVisibility(View.GONE);
        presstoShowResults.setVisibility(View.GONE);
        presstoShowstudentRequest.setVisibility(View.GONE);

    }
}
