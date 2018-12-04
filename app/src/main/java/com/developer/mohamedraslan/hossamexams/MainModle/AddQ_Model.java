package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;
import com.developer.mohamedraslan.hossamexams.Contracts.AddQuestionContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class AddQ_Model implements AddQuestionContract.AddQModel {

    String QKey;

    @Override
    public void upload_Questions_toServer(DatabaseReference reference, final AddQuestionContract.AddQPresnter addQPresnter, Questions_Form questions_form) {

        reference.child(questions_form.getQuestionID()).setValue(questions_form).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if (task.isSuccessful()){

                    addQPresnter.updateUIDataSavedSuccessfull();


                }
                else {

                    addQPresnter.updatUiDataNotSaved("Error");

                }


            }
        });


    }




    @Override
    public void getQuestionDetails(DatabaseReference reference, final AddQuestionContract.AddQPresnter addQPresnter, String qID) {


        reference.child(qID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    Questions_Form questions_form = dataSnapshot.getValue(Questions_Form.class);
                    addQPresnter.updateUitoQuestion(questions_form);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                addQPresnter.problemOcurrwhengetQ(databaseError.getDetails());
            }
        });



    }

    @Override
    public void editingQuestion(DatabaseReference reference, final AddQuestionContract.AddQPresnter addQPresnter, String QID, Questions_Form questions_form) {


        reference.child(QID).setValue(questions_form).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if (task.isSuccessful()){

                   addQPresnter.dataedited();


                }
                else {

                    addQPresnter.dataNotEdited();

                }


            }
        });


    }
}
