package com.am.mohamedraslan.hossamexams.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.am.mohamedraslan.hossamexams.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExamStudenstHolder extends RecyclerView.ViewHolder {

    TextView txNameStudentt;
    CircleImageView circleimageee;
    CardView Press_on_CardViewss;
    Button agreeing;
    public ExamStudenstHolder(View itemView) {
        super(itemView);
        txNameStudentt = itemView.findViewById(R.id.txNameStudentt);
        agreeing       = itemView.findViewById(R.id.agreeing);
        circleimageee = itemView.findViewById(R.id.circleimageee);
        Press_on_CardViewss       = itemView.findViewById(R.id.Press_on_CardViewss);
    }
}
