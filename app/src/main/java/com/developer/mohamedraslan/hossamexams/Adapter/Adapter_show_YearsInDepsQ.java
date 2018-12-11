package com.developer.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Contracts.Years_inDepsContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.developer.mohamedraslan.hossamexams.R;

import java.util.List;

public class Adapter_show_YearsInDepsQ extends RecyclerView.Adapter<Adapter_show_YearsInDepsQ.HolderYearsQ> {



    private  Context mContext;
    private List<Year_modle_json>myYears;
    private Years_inDepsContract.ViewMainYear yearlistener;
    String nameDep;

    public Adapter_show_YearsInDepsQ(Context mContext, List<Year_modle_json> list, Years_inDepsContract.ViewMainYear yearlistener,String nameDep) {
        this.mContext = mContext;
        this.myYears = list;
        this.yearlistener = yearlistener;
        this.nameDep   = nameDep;
    }


    @NonNull
    @Override
    public HolderYearsQ onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.years_shown_inadapterquestion,parent,false);
        return new HolderYearsQ(row,mContext,myYears,yearlistener,nameDep);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderYearsQ holder, int position) {

        holder.animateCard();
        holder.setTextinTextView(myYears.get(position).getYearName());
        yearlistener.getSizeofarray(getItemCount());

    }

    @Override
    public int getItemCount() {

        return myYears.size();
    }

    public static class HolderYearsQ extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView presstoshowdetailsofYearsQ;
        TextView name_of_yeartogetQ;
        Context context;
        String depName;
        Years_inDepsContract.ViewMainYear yearlistener;
        List<Year_modle_json>list;
        public HolderYearsQ(View itemView,Context context,List<Year_modle_json>list,Years_inDepsContract.ViewMainYear yearlistener,String depNamne) {
            super(itemView);
            this.context = context;
            this.yearlistener = yearlistener;
            this.depName = depNamne;
            this.list    = list;
            presstoshowdetailsofYearsQ = itemView.findViewById(R.id.presstoshowdetailsofYearsQ);
            name_of_yeartogetQ         = itemView.findViewById(R.id.name_of_yeartogetQ);
            presstoshowdetailsofYearsQ.setOnClickListener(this);
        }

        public void animateCard(){
            presstoshowdetailsofYearsQ.setScaleX(.9f);
            presstoshowdetailsofYearsQ.setScaleY(.9f);
            presstoshowdetailsofYearsQ.animate().scaleX(1f).scaleY(1f).setDuration(900);

        }
        public void setTextinTextView(String text){

            name_of_yeartogetQ.setText(text);
        }

        @Override
        public void onClick(View view) {

            if (view == presstoshowdetailsofYearsQ){

                yearlistener.getValuesofdepandyear(depName,list.get(getAdapterPosition()).getYearName());
            }

        }
    }


}
