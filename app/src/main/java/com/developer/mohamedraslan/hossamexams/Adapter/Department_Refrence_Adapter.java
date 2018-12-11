package com.developer.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.developer.mohamedraslan.hossamexams.Contracts.Student_Department_Contract;
import com.developer.mohamedraslan.hossamexams.R;

import java.util.List;

public class Department_Refrence_Adapter extends RecyclerView.Adapter<Department_Refrence_Adapter.MyDepHolder> {


    List<String>list;
    Context mContext;
    Student_Department_Contract.mainView mainView;

    public Department_Refrence_Adapter(List<String> list, Context mContext,Student_Department_Contract.mainView mainView) {
        this.list = list;
        this.mContext = mContext;
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public MyDepHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row  = LayoutInflater.from(mContext).inflate(R.layout.student_dep_rec,parent,false);

        return new MyDepHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDepHolder holder, final int position) {

        holder.depsName.setText(list.get(position));

        if (position % 2 == 0) {

            holder.cardallDeps.setX(-1000);
            holder.cardallDeps.animate().translationXBy(1000).setDuration(800);
            holder.cardallDeps.setScaleX(.9f);
            holder.cardallDeps.setScaleY(.9f);
            holder.cardallDeps.animate().scaleX(1f).scaleY(1f).setDuration(500);
            holder.showRelative.setX(1000);
            holder.showRelative.animate().translationXBy(-1000).setDuration(800);
            holder.showRelative.setScaleX(.9f);
            holder.showRelative.setScaleY(.9f);
            holder.showRelative.animate().scaleX(1f).scaleY(1f).setDuration(500);
        }
        else
        {

            holder.cardallDeps.setX(1000);
            holder.cardallDeps.animate().translationXBy(-1000).setDuration(800);
            holder.cardallDeps.setScaleX(.9f);
            holder.cardallDeps.setScaleY(.9f);
            holder.cardallDeps.animate().scaleX(1f).scaleY(1f).setDuration(500);
            holder.showRelative.setX(-1000);
            holder.showRelative.animate().translationXBy(1000).setDuration(800);
            holder.showRelative.setScaleX(.9f);
            holder.showRelative.setScaleY(.9f);
            holder.showRelative.animate().scaleX(1f).scaleY(1f).setDuration(500);

        }



        mainView.setSizeOfList(getItemCount());



        holder.cardallDeps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainView.getDetailsForDeps(list.get(position));

            }
        });



    }

    @Override
    public int getItemCount() {

        return list.size();

    }





    public static class MyDepHolder extends RecyclerView.ViewHolder{


        CardView cardallDeps;
        RelativeLayout showRelative;
        TextView depsName;

        public MyDepHolder(View itemView) {
            super(itemView);
            cardallDeps = itemView.findViewById(R.id.cardallDeps);
            depsName    = itemView.findViewById(R.id.depsName);
            showRelative= itemView.findViewById(R.id.showRelative);
        }
    }


}
