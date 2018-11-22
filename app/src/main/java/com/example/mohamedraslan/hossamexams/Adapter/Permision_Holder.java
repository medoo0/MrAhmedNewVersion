package com.example.mohamedraslan.hossamexams.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mohamedraslan.hossamexams.R;

public class Permision_Holder extends RecyclerView.ViewHolder {


    Button buttongetallRequest;

    TextView ExamNamerequest;
    CardView cardanimate;
    public Permision_Holder(View itemView) {
        super(itemView);
        buttongetallRequest = itemView.findViewById(R.id.buttongetallRequest);
        ExamNamerequest     = itemView.findViewById(R.id.ExamNamerequest);
        cardanimate         = itemView.findViewById(R.id.cardanimate);
    }


    public void animates(){

        cardanimate.setScaleX(.9f);
        cardanimate.setScaleY(.9f);
        cardanimate.animate().scaleX(1f).scaleY(1f).setDuration(500);
    }
}
