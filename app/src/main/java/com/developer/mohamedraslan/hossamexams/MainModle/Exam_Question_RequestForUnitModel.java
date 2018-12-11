package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.Exam_Question_Request_forUnitInterface;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Exam_Question_RequestForUnitModel implements Exam_Question_Request_forUnitInterface.ExamQuestionRequestModel {


    Exam_Question_Request_forUnitInterface.ExamQuestioRequestPresnter examQuestioRequestPresnter;

    public Exam_Question_RequestForUnitModel(Exam_Question_Request_forUnitInterface.ExamQuestioRequestPresnter examQuestioRequestPresnter) {
        this.examQuestioRequestPresnter = examQuestioRequestPresnter;
    }

    @Override
    public void checkIfAdminorUsertoHideSomeButton(String uID) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference(DataBase_Refrences.ADMIN.getRef()).child(uID);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){

                    examQuestioRequestPresnter.yesThisAdmin();

                }

                else {

                    examQuestioRequestPresnter.yesThisUser();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                //  ToDo  when occur Exception  ....

            }
        });

    }
}
