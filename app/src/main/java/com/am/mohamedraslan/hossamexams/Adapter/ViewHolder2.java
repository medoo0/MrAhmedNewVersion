package com.am.mohamedraslan.hossamexams.Adapter;

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

import com.am.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.am.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.am.mohamedraslan.hossamexams.Dialog.CustomTypeFaceSpan;
import com.am.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.am.mohamedraslan.hossamexams.R;
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

            Cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    PopupMenu popup = new PopupMenu(context, Cardview);

                    //Inflating the Popup using xml file
                    popup.getMenuInflater().inflate(R.menu.delete_results, popup.getMenu());

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.Delete_results){

                                //Deleting
                                final AlertDialog alertDialog = new AlertDialog(context,"تحذير"," يرجي العلم عند حذف هذه النتائج سوف يتم ازالتها من قائمة نتائجي عند جميع الطلبة وسوف يتم ازالة الاختبار من قائمة الاختبارات ايضا( في حالة وجوده) , متأكد ؟ ");
                                alertDialog.show();
                                alertDialog.btnYes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.ShowDialog();
                                        Delete_Exam();
                                        alertDialog.dismiss();
                                    }
                                });


                            }

                            return true;
                        }
                    });


                    Menu menu = popup.getMenu();
                    for (int i = 0; i < menu.size(); i++) {
                        MenuItem mi = menu.getItem(i);
                        applyFontToMenuItem(mi);

                    }



                    popup.show();
                }
            });


        }

    private void applyFontToMenuItem(MenuItem mi) {

        Typeface font = Typeface.createFromAsset(context.getAssets(),"atherfont.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypeFaceSpan("", font, Color.WHITE), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);

    }

    private void Delete_Exam(){

        Query reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.RESULT.getRef())
                .orderByChild("examID")
                .equalTo(ExamID);

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.exists()) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



                    DatabaseReference reference2  = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.STARTEDEXAM.getRef())
                    .child(ExamID);

                        reference2.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    DatabaseReference reference3  = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.ResultsRef.getRef())
                            .child(ExamID);
                    reference3.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            DatabaseReference reference4  = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.EXAMS.getRef())
                                    .child(ExamID);
                            reference4.removeValue();

                            dialog.Close_Dialog();
                            AlertDialog alertDialog = new AlertDialog(context,"تم حذف النتائج بنجاح");
                            alertDialog.show();
                        }
                    });

                }
            });

    }



}
