package com.am.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.am.mohamedraslan.hossamexams.Contracts.QuestionsBankContract;
import com.am.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.am.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by microprocess on 2018-10-05.
 */

public class Question_BankModel implements QuestionsBankContract.model {

    QuestionsBankContract.presenter presenter;
    List<Questions_Form> StudentsDetails;

    public Question_BankModel(QuestionsBankContract.presenter presenter) {
        this.presenter = presenter;
        StudentsDetails = new ArrayList<>();
    }

    @Override
    public void getQuestionData() {

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child(DataBase_Refrences.BANKQUESTIONS.getRef());

        myref.addListenerForSingleValueEvent( new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Questions_Form g1 = data.getValue(Questions_Form.class);
                        StudentsDetails.add(g1);
                        presenter.SendListToView(StudentsDetails);
                    }


                } else {

                    //not exist
                    presenter.problem("لايوجد اسئلة");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                //problem
                presenter.problem(databaseError.getMessage());


            }
        });
    }

    @Override
    public void addQuestionToAddTestRecycler(String questionID) {

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference()
                .child(DataBase_Refrences.CHOSENQUESTIONID.getRef()).child(questionID);

        myref.setValue("yes").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                presenter.sentSuccessfully("Successful");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                presenter.problem(e.toString());
            }
        });

    }

    @Override
    public void removingQuestionFromDatabase(DatabaseReference reference, String Qid, final QuestionsBankContract.presenter presenter, final int position) {

        reference.child(Qid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    presenter.Qremoved(position);

                }else {

                    presenter.Q_notRemoved_checking();

                }
            }
        });


    }
}
