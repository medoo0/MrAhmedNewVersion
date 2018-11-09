package com.example.mohamedraslan.hossamexams.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mohamedraslan.hossamexams.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by microprocess on 2018-10-20.
 */

public class ViewHolder3 extends RecyclerView.ViewHolder {
    @BindView(R.id.txNameStudent)
    TextView txName ;

    @BindView(R.id.txDegree)
    TextView txDegree ;

    @BindView(R.id.txFinalDegree)
    TextView txFinalDegree ;

    @BindView(R.id.Press_on_CardView)
    CardView cardView ;

    @BindView(R.id.circleimage)
    CircleImageView circleImageView ;

    public ViewHolder3(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
