package com.example.mohamedraslan.hossamexams.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mohamedraslan.hossamexams.JsonModel.WorngQestion;
import com.example.mohamedraslan.hossamexams.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by microprocess on 2018-10-20.
 */

public class StudentsWrongs_Rec_Adapter extends RecyclerView.Adapter<StudentsWrongs_Rec_Adapter.ViewHolder>{
    ArrayList<WorngQestion> list ;

    public StudentsWrongs_Rec_Adapter(ArrayList<WorngQestion> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentswrong_rec_layout,parent,false);
        return new StudentsWrongs_Rec_Adapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.question.setText(list.get(position).getQuestion());
        holder.correct_answer.setText(list.get(position).getCorrectAnswer());
        if(list.get(position).getStudentAnswer().isEmpty()){
            holder.hisanswer.setText(" لم يُجِيبُ . ");
        }
        else {

            holder.hisanswer.setText(list.get(position).getStudentAnswer());

        }

                holder.Liner.setScaleX(.9f);
                holder.Liner.setScaleY(.9f);
                holder.Liner.animate().alpha(1).scaleX(1f).scaleY(1f).setDuration(500);

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.question)
        TextView question;

        @BindView(R.id.hisanswer)
        TextView hisanswer;

        @BindView(R.id.correct_answer)
        TextView correct_answer;

        @BindView(R.id.Liner)
        LinearLayout Liner;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
