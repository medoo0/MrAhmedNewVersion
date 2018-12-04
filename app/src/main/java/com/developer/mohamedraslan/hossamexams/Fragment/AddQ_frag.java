package com.developer.mohamedraslan.hossamexams.Fragment;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.developer.mohamedraslan.hossamexams.Contracts.AddQuestionContract;
import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.developer.mohamedraslan.hossamexams.MainPresnter.AddQ_Presnter;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Utils.ViewsEmpty;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


/**
 * Created by microprocess on 2018-10-03.
 */
public class AddQ_frag extends Fragment
                       implements View.OnClickListener , AddQuestionContract.AddQUI {


    Button buttonA , buttonB , buttonC , buttonD , savingData;
    Drawable trueClick,falseClick;
    private String selectAnswer = "";
    EditText writeQuestion,answerOne,answerTwo , answerThree , answerFour;
    FirebaseDatabase firebaseDatabaseQuestions;
    DatabaseReference databaseReferenceQuestions;
    AnimatedDialog animatedDialog;
    String val = "";
    String QestionID = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trueClick                 = Objects.requireNonNull(getActivity()).getDrawable(R.drawable.radiobutton_check_true);
        falseClick                = Objects.requireNonNull(getActivity()).getDrawable(R.drawable.radiobutton_check_false);
        firebaseDatabaseQuestions = FirebaseDatabase.getInstance();
        databaseReferenceQuestions= firebaseDatabaseQuestions.getReference(DataBase_Refrences.BANKQUESTIONS.getRef());
        animatedDialog            = new AnimatedDialog(getActivity());

        ControlPanel.Title.setText(getString(R.string.addQ));
        if (getArguments()!=null){
            ControlPanel.Title.setText(getString(R.string.edit));
            QestionID = getArguments().getString("ID","");
            val       = getArguments().getString("val","");

        }




    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v        = inflater.inflate(R.layout.add_qestion_frag_layout,container,false);

        buttonA       = v.findViewById(R.id.A);
        buttonB       = v.findViewById(R.id.B);
        buttonC       = v.findViewById(R.id.C);
        buttonD       = v.findViewById(R.id.D);
        savingData    = v.findViewById(R.id.savingData);
        writeQuestion = v.findViewById(R.id.writeQuestion);
        answerOne     = v.findViewById(R.id.answerOne);
        answerTwo     = v.findViewById(R.id.answerTwo);
        answerThree   = v.findViewById(R.id.answerThree);
        answerFour    = v.findViewById(R.id.answerFour);
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        savingData.setOnClickListener(this);

        if (val.equals("Editing") && !QestionID.equals("") && !val.equals("")){
            animatedDialog.ShowDialog();
            AddQ_Presnter addQ_presnter = new AddQ_Presnter(this);
            addQ_presnter.tellModletoGetQuestion(databaseReferenceQuestions,this,QestionID);
        }

        return v;
    }


    @Override
    public void onClick(View view) {

        if (view == buttonA){
            if (answerOne.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "قم بملئ الحقل اولا حتي نتمكن من اخذ الإجابة", Toast.LENGTH_SHORT).show();
            }
            selectAnswer = answerOne.getText().toString();
            buttonA.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            buttonD.setBackground(falseClick);
        }

        if (view == buttonB){
            if (answerTwo.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "قم بملئ الحقل اولا حتي نتمكن من اخذ الإجابة", Toast.LENGTH_SHORT).show();
            }
            selectAnswer = answerTwo.getText().toString();
            buttonB.setBackground(trueClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            buttonD.setBackground(falseClick);
        }

        if (view == buttonC){
            if (answerThree.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "قم بملئ الحقل اولا حتي نتمكن من اخذ الإجابة ", Toast.LENGTH_SHORT).show();
            }
            selectAnswer = answerThree.getText().toString();
            buttonC.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonD.setBackground(falseClick);

        }
        if (view == buttonD){

            if (answerFour.getText().toString().isEmpty()){

                Toast.makeText(getActivity(), "قم بملئ الحقل اولا حتي نتمكن من اخذ الإجابة ", Toast.LENGTH_SHORT).show();
            }
            selectAnswer = answerFour.getText().toString();
            buttonD.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);

        }
        if (view == savingData){


            ViewsEmpty.isEmpty(writeQuestion,getResources().getString(R.string.answerOne));
            ViewsEmpty.isEmpty(answerOne , getResources().getString(R.string.answerOne));
            ViewsEmpty.isEmpty(answerTwo , getResources().getString(R.string.answerTwo));
            ViewsEmpty.isEmpty(answerThree , getResources().getString(R.string.answerThre));
            ViewsEmpty.isEmpty(answerFour , getResources().getString(R.string.answerFour));
            if (selectAnswer.isEmpty()){

                Toast.makeText(getActivity(), "يرجي ادخال الاجابه الصحيحه", Toast.LENGTH_LONG).show();

            }


            if (!writeQuestion.getText().toString().isEmpty()
                    && !answerOne.getText().toString().isEmpty()
                    && !answerTwo.getText().toString().isEmpty()
                    && !answerThree.getText().toString().isEmpty()
                    && !answerFour.getText().toString().isEmpty()
                    && !selectAnswer.isEmpty()){


                if (!val.equals("") && val.equals("Editing") && !QestionID.equals("") ){


                    Questions_Form questions_form  = new Questions_Form(writeQuestion.getText().toString(),answerOne.getText().toString(),answerTwo.getText().toString(),answerThree.getText().toString()
                            ,answerFour.getText().toString(),QestionID,selectAnswer);
                    animatedDialog.ShowDialog();
                    AddQ_Presnter addQ_presnter    = new AddQ_Presnter(this);
                    addQ_presnter.update_modle_to_editQuestion(databaseReferenceQuestions,this,QestionID,questions_form);

                }else {

                    Questions_Form questions_form  = new Questions_Form(writeQuestion.getText().toString(),answerOne.getText().toString(),answerTwo.getText().toString(),answerThree.getText().toString()
                            ,answerFour.getText().toString(),databaseReferenceQuestions.push().getKey(),selectAnswer);
                    animatedDialog.ShowDialog();
                    AddQ_Presnter addQ_presnter    = new AddQ_Presnter(this);
                    addQ_presnter.updateModelToSaveData(databaseReferenceQuestions,questions_form);


                }




            }



        }

    }


    @Override
    public void dataSaved() {

        final MediaPlayer player = MediaPlayer.create(getActivity(),R.raw.plucky);
        player.start();
        animatedDialog.Close_Dialog();
        selectAnswer = "";
        buttonD.setBackground(falseClick);
        buttonB.setBackground(falseClick);
        buttonA.setBackground(falseClick);
        buttonC.setBackground(falseClick);
        writeQuestion.setText("");
        answerOne.setText("");
        answerTwo.setText("");
        answerFour.setText("");
        answerThree.setText("");

        AlertDialog alertDialog = new AlertDialog(getActivity(),"لقد تم اضافه السؤال بنجاح");
        alertDialog.show();

    }

    @Override
    public void problem(String E) {
        animatedDialog.Close_Dialog();
        AlertDialog alertDialog = new AlertDialog(getActivity(),"لقد حدثت مشكله اثناء الاتصال");
        alertDialog.show();
    }

    @Override
    public void questionHere(Questions_Form questions_form) {

        String[] allanswer = new String[]{ questions_form.getAnswerOne(),questions_form.getAnswerTwo(),questions_form.getAnswerThree(),questions_form.getAnswerFour()};

        writeQuestion.setText(questions_form.getQuestion());
        answerOne.setText(questions_form.getAnswerOne());
        answerTwo.setText(questions_form.getAnswerTwo());
        answerThree.setText(questions_form.getAnswerThree());
        answerFour.setText(questions_form.getAnswerFour());
        returnResult(allanswer,questions_form.getCorrectAnswer());

        if (!selectAnswer.equals("")){



            if (selectAnswer.equals(answerOne.getText().toString())){

                buttonA.setBackground(trueClick);

            }
            if (selectAnswer.equals(answerTwo.getText().toString())){

                buttonB.setBackground(trueClick);

            }
            if (selectAnswer.equals(answerThree.getText().toString())){

                buttonC.setBackground(trueClick);

            }
            if (selectAnswer.equals(answerFour.getText().toString())){

                buttonD.setBackground(trueClick);
            }




        }


        animatedDialog.Close_Dialog();


    }

    @Override
    public void problem_notHere(String error) {

    }



    @Override
    public void dataEditedsussess() {

        animatedDialog.Close_Dialog();
        Toast.makeText(getActivity(), "لقد تم تعديل السؤال بنجاح", Toast.LENGTH_SHORT).show();
        ControlPanelContract.ControlUI controlUI = (ControlPanelContract.ControlUI) getActivity();

        if (controlUI!=null){

            controlUI.editSuccessopenBank();

        }

    }

    @Override
    public void datanotEditedfailed(String E) {

        animatedDialog.Close_Dialog();
        Toast.makeText(getActivity(), "لقد حدثت مشكله حاول مره اخري", Toast.LENGTH_SHORT).show();
    }



    private void returnResult(String[] allAnswer , String result){

        for (int i=0 ; i<allAnswer.length ; i++){

            if (result.equals(allAnswer[i])){

                selectAnswer = allAnswer[i];
                break;

            }

        }

    }
}