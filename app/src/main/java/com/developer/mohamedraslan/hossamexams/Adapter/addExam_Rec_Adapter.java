package com.developer.mohamedraslan.hossamexams.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developer.mohamedraslan.hossamexams.Contracts.addExamContract;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.developer.mohamedraslan.hossamexams.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by microprocess on 2018-10-05.
 */

public class addExam_Rec_Adapter extends RecyclerView.Adapter<addExam_Rec_Adapter.ViewHolder> {
    List<Questions_Form> questions ;
    addExamContract.view view;

    public addExam_Rec_Adapter(List<Questions_Form> questions , addExamContract.view view) {

        this.questions = questions;
        this.view = view ;
    }

    @NonNull
    @Override
    public addExam_Rec_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chosen_question_rec_layout,parent,false);
        return new addExam_Rec_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull addExam_Rec_Adapter.ViewHolder holder, int position) {
        holder.txQuestion.setText(questions.get(position).getQuestion());

        //animation
        holder.Cardview.setScaleX(.9f);
        holder.Cardview.setScaleY(.9f);
        holder.Cardview.animate().scaleX(1f).scaleY(1f).setDuration(500);

        //animation 2
        holder.background.setScaleX(.9f);
        holder.background.setScaleY(.9f);
        holder.background.animate().scaleX(1f).scaleY(1f).setDuration(500);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void removeItem(final int position) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.CHOSENQUESTIONID.getRef())
                .child(questions.get(position).getQuestionID());

        reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                questions.remove(position);
                notifyItemRemoved(position);

                //update Question Size in fragement .

                view.Update_Questions_size(getItemCount());



            }
        });


    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView Cardview ;
        TextView txQuestion;
        LinearLayout background;
        public ViewHolder(View itemView) {
            super(itemView);
            Cardview = itemView.findViewById(R.id.Cardview);
            txQuestion = itemView.findViewById(R.id.Question);
            background = itemView.findViewById(R.id.background);
        }
    }






}
