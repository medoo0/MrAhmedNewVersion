package com.developer.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.mohamedraslan.hossamexams.Contracts.Years_inDepsContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;

import java.util.List;

public class Adapter_show_YearsInDeps extends RecyclerView.Adapter<Adapter_show_YearsInDeps.HolderYearsInDeps> {


    private  Context mContext;
    private List<Year_modle_json>list;
    private Years_inDepsContract.ViewMainYear yearlistener;
    String depName ;


    public Adapter_show_YearsInDeps(Context mContext, List<Year_modle_json> list, Years_inDepsContract.ViewMainYear yearlistener,String depName) {
        this.mContext = mContext;
        this.list = list;
        this.yearlistener = yearlistener;
        this.depName = depName;
    }

    @NonNull
    @Override
    public HolderYearsInDeps onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View row = LayoutInflater.from(mContext).inflate(R.layout.year_shown_inadapter,parent,false);
       return new HolderYearsInDeps(row,mContext,list,yearlistener,depName);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderYearsInDeps holder, int position) {

        holder.setTextinTextView(list.get(position).getYearName());
        holder.animateCard();
        yearlistener.getSizeofarray(getItemCount());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class HolderYearsInDeps extends RecyclerView.ViewHolder implements View.OnClickListener{

    CardView  presstoshowdetailsofYears;
    TextView  name_of_year;
    private List<Year_modle_json>list;
    Context context;
    String depName;
    Years_inDepsContract.ViewMainYear yearlistener;

        public HolderYearsInDeps(View itemView,Context context,  List<Year_modle_json>list,Years_inDepsContract.ViewMainYear yearlistener,String depName) {
            super(itemView);
            this.context = context;
            this.depName = depName;
            this.list    = list;
            this.yearlistener = yearlistener;
            presstoshowdetailsofYears = itemView.findViewById(R.id.presstoshowdetailsofYears);
            name_of_year              = itemView.findViewById(R.id.name_of_year);
            presstoshowdetailsofYears.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            if (view == presstoshowdetailsofYears){


                yearlistener.getValuesofdepandyear(depName,list.get(getAdapterPosition()).getYearName());

                //  حتجيب كل البيانات بتاعه الصف الدراسي

            }

        }

        public void animateCard(){
            presstoshowdetailsofYears.setScaleX(.9f);
            presstoshowdetailsofYears.setScaleY(.9f);
            presstoshowdetailsofYears.animate().scaleX(1f).scaleY(1f).setDuration(800);

        }

        public void setTextinTextView(String text){

            name_of_year.setText("Student of " + text);
        }
    }


}
