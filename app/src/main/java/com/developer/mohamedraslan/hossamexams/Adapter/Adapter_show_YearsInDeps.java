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
import com.developer.mohamedraslan.hossamexams.R;

import java.util.List;

public class Adapter_show_YearsInDeps extends RecyclerView.Adapter<Adapter_show_YearsInDeps.HolderYearsInDeps> {


    private  Context mContext;
    private List<String>list;
    private Years_inDepsContract.ViewMainYear yearlistener;


    public Adapter_show_YearsInDeps(Context mContext, List<String> list, Years_inDepsContract.ViewMainYear yearlistener) {
        this.mContext = mContext;
        this.list = list;
        this.yearlistener = yearlistener;
    }

    @NonNull
    @Override
    public HolderYearsInDeps onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View row = LayoutInflater.from(mContext).inflate(R.layout.year_shown_inadapter,parent,false);
       return new HolderYearsInDeps(row,mContext,list);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderYearsInDeps holder, int position) {

        holder.setTextinTextView(list.get(position));
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
    ImageView image_dropdownn;
    RelativeLayout relativefocus;
    LinearLayout Details_layout;
    private List<String>list;
    Context context;

        public HolderYearsInDeps(View itemView,Context context,  List<String>list) {
            super(itemView);
            this.context = context;
            this.list    = list;
            presstoshowdetailsofYears = itemView.findViewById(R.id.presstoshowdetailsofYears);
            name_of_year              = itemView.findViewById(R.id.name_of_year);
            Details_layout            = itemView.findViewById(R.id.Details_layout);
            image_dropdownn           = itemView.findViewById(R.id.image_dropdownn);
            relativefocus             = itemView.findViewById(R.id.relativefocus);
            presstoshowdetailsofYears.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            if (view == presstoshowdetailsofYears){


                if(Details_layout.isShown()){
                    Details_layout.setVisibility(View.GONE);
                    image_dropdownn.setImageResource(R.drawable.ic_dropdown);
                }
                else {
                    Details_layout.setVisibility(View.VISIBLE);
                    image_dropdownn.setImageResource(R.drawable.ic_dropup);
                    openAnimation(Details_layout);
                }

                Toast.makeText(context,list.get(getAdapterPosition()) , Toast.LENGTH_SHORT).show();

            }

        }

        public void animateCard(){
            presstoshowdetailsofYears.setScaleX(.9f);
            presstoshowdetailsofYears.setScaleY(.9f);
            presstoshowdetailsofYears.animate().scaleX(1f).scaleY(1f).setDuration(800);

        }

        public void setTextinTextView(String text){

            name_of_year.setText(text);
        }
    }
    public static void openAnimation(LinearLayout CardDownlayout ){

        CardDownlayout.setScaleX(0.0f);
        CardDownlayout.setScaleY(0.0f);
        CardDownlayout.animate().scaleX(1f).scaleY(1f).setDuration(350);
    }

}
