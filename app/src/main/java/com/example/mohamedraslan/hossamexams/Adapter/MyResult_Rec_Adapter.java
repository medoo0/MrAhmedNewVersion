package com.example.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamedraslan.hossamexams.Contracts.MyResultContract;
import com.example.mohamedraslan.hossamexams.Fragment.StudentsWrongs;
import com.example.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.example.mohamedraslan.hossamexams.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by microprocess on 2018-10-18.
 */

public class MyResult_Rec_Adapter extends RecyclerView.Adapter<MyResult_Rec_Adapter.ViewHolder> {


    List<Result_Pojo> Result;
    MyResultContract.view view;

    public MyResult_Rec_Adapter(List<Result_Pojo> result, MyResultContract.view view) {
        Result = result;
        this.view = view;
    }

    @NonNull
    @Override
    public MyResult_Rec_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myresults_rec_layout,parent,false);
        return new MyResult_Rec_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyResult_Rec_Adapter.ViewHolder holder, final int position) {
        holder.txExamName.setText(Result.get(position).getExamName());
        holder.txDegree.setText(Result.get(position).getTotal());
        holder.txFinalDegree.setText(Result.get(position).getFinalDegree());


        if (position % 2 == 0) {
//                holder.card.setScaleX(.9f);
//                holder.card.setScaleY(.9f);
//                holder.card.animate().alpha(1).scaleX(1f).scaleY(1f).setDuration(500);
            holder.Cardview.setX(-1000);
            holder.Cardview.animate().translationXBy(1000).setDuration(800);
        }
        else
        {

            holder.Cardview.setX(1000);
            holder.Cardview.animate().translationXBy(-1000).setDuration(800);

        }


        holder.Cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(Result.get(position).getWrongQuestions() != null)
                    if(Result.get(position).getWrongQuestions().size() > 0 ) {


                    //show fragment
                        view.showAtherFragment(Result.get(position),Result.get(position).getFinalDegree(),Result.get(position).getTotal());


                    }

            }
        });



    }

    @Override
    public int getItemCount() {
        return Result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ExamName)
        TextView txExamName;
        @BindView(R.id.txDegree)
        TextView txDegree;
        @BindView(R.id.txFinalDegree)
        TextView txFinalDegree;
        @BindView(R.id.Cardview)
        CardView Cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
