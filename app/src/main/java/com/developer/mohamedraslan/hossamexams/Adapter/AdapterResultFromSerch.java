package com.developer.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.mohamedraslan.hossamexams.Contracts.AllDetailsStudentSearchContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.developer.mohamedraslan.hossamexams.R;

import java.util.List;

public class AdapterResultFromSerch extends RecyclerView.Adapter<AdapterResultFromSerch.MyHolderAboutSearch> {



    List<Result_Pojo>list;
    Context context;
    AllDetailsStudentSearchContract.AllStudentSerachUI allStudentSerachUI;


    public AdapterResultFromSerch(List<Result_Pojo> list, Context context, AllDetailsStudentSearchContract.AllStudentSerachUI allStudentSerachUI) {
        this.list = list;
        this.context = context;
        this.allStudentSerachUI = allStudentSerachUI;
    }

    @NonNull
    @Override
    public MyHolderAboutSearch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.resultofsearch,parent,false);


        return new MyHolderAboutSearch(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderAboutSearch holder, int position) {



        holder.unitName.setText(list.get(position).getUnitName());
        holder.ExamNamee.setText(list.get(position).getExamName());
        holder.txDegrees.setText(list.get(position).getTotal());
        holder.txFinalDegrees.setText(list.get(position).getFinalDegree());
        holder.animateCard();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolderAboutSearch extends RecyclerView.ViewHolder{
        TextView unitName , ExamNamee , txDegrees  ,txFinalDegrees;
        CardView Cardviewd;

        public MyHolderAboutSearch(View itemView) {
            super(itemView);

            unitName        = itemView.findViewById(R.id.unitName);
            ExamNamee       = itemView.findViewById(R.id.ExamNamee);
            txDegrees       = itemView.findViewById(R.id.txDegrees);
            txFinalDegrees  = itemView.findViewById(R.id.txFinalDegrees);
            Cardviewd       = itemView.findViewById(R.id.Cardviewd);

        }

        public void animateCard(){
            Cardviewd.setScaleX(.9f);
            Cardviewd.setScaleY(.9f);
            Cardviewd.animate().scaleX(1f).scaleY(1f).setDuration(800);

        }
    }




}
