package com.developer.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.CustomTypeFaceSpan;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by microprocess on 2018-10-19.
 */

public class ViewHolder2  extends RecyclerView.ViewHolder {

        Button BtnResults;
        TextView ExamName;
        TextView Date;
        CardView Cardview;
        Context context;
        AnimatedDialog dialog;
        FrameLayout fram;
        String ExamID ;
        LinearLayout layout;

        public ViewHolder2( View itemView) {
            super(itemView);

            BtnResults = itemView.findViewById(R.id.ShowResults);
            ExamName   = itemView.findViewById(R.id.ExamName);
            Date       =  itemView.findViewById(R.id.date);
            Cardview = itemView.findViewById(R.id.card);
            context = itemView.getContext();
            dialog = new AnimatedDialog(context);
            fram = itemView.findViewById(R.id.fram);
            layout = itemView.findViewById(R.id.linear2);

        }




}
