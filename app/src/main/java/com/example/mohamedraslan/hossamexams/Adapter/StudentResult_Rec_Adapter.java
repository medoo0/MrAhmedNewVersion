package com.example.mohamedraslan.hossamexams.Adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.example.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.example.mohamedraslan.hossamexams.Fragment.StudentsWrongs;
import com.example.mohamedraslan.hossamexams.JsonModel.Resister_form;
import com.example.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.example.mohamedraslan.hossamexams.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by microprocess on 2018-10-20.
 */

public class StudentResult_Rec_Adapter  extends FirebaseRecyclerAdapter<Result_Pojo,ViewHolder3> {
    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
     *                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    int photosCounter = 0 ;
    FragmentManager fragmentManager;
    public StudentResult_Rec_Adapter(Class<Result_Pojo> modelClass, int modelLayout, Class<ViewHolder3> viewHolderClass, Query ref, FragmentManager fragmentManager) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void populateViewHolder(final ViewHolder3 holder, final Result_Pojo model, int position) {


        //photos changer .
        if (photosCounter == 0 ) {
            holder.circleImageView.setBackgroundResource(R.drawable.ic_student_1);
            holder.circleImageView.setTag(R.drawable.ic_student_1);
            photosCounter ++ ;
        }
        else if (photosCounter == 1) {
            holder.circleImageView.setBackgroundResource(R.drawable.ic_student_2);
            holder.circleImageView.setTag(R.drawable.ic_student_2);
            photosCounter ++ ;
        }
        else if( photosCounter == 2 )
        {
            holder.circleImageView.setBackgroundResource(R.drawable.ic_student_3);
            holder.circleImageView.setTag(R.drawable.ic_student_3);
            photosCounter ++ ;
        }
        else {
            holder.circleImageView.setBackgroundResource(R.drawable.ic_student_4);
            holder.circleImageView.setTag(R.drawable.ic_student_4);
            photosCounter = 0;
        }



        holder.txDegree.setText(model.getTotal());
        holder.txFinalDegree.setText(model.getFinalDegree());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.USERREF.getRef())
                .child(model.getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //Get Student Name .
                    Resister_form form = dataSnapshot.getValue(Resister_form.class);
                    holder.txName.setText(form.getNameStudent()+"");

                }
                else {


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        if (position % 2 == 0) {

            holder.cardView.setX(-1000);
            holder.cardView.animate().translationXBy(1000).setDuration(800);
        }
        else
        {

            holder.cardView.setX(1000);
            holder.cardView.animate().translationXBy(-1000).setDuration(800);

        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("Name", holder.txName.getText().toString() );
                bundle.putString("FinalDegree", model.getFinalDegree());
                bundle.putString("Total",model.getTotal());
                bundle.putString("examID",model.getExamID());
                bundle.putParcelableArrayList("WrongQuestions",model.getWrongQuestions());
                //to pass image to next fragment.
                bundle.putInt("Image", (Integer) holder.circleImageView.getTag());
                // set MyFragment Arguments
                StudentsWrongs StudentsWrongs = new StudentsWrongs();
                StudentsWrongs.setArguments(bundle);

                if(model.getWrongQuestions() != null)
                if(model.getWrongQuestions().size() > 0 ) {
                    ViewCompat.setTransitionName(holder.circleImageView, "Image");
                    fragmentManager
                            .beginTransaction()
                            .addSharedElement(holder.circleImageView, holder.circleImageView.getTransitionName())
                            .replace(R.id.Exam_Frame, StudentsWrongs)
                            .addToBackStack(null)
                            .commit();
                }

            }
        });




    }
}
