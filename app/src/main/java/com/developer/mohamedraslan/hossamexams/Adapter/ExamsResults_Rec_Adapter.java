package com.developer.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.PopupMenu;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.CustomTypeFaceSpan;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.Fragment.StudentResult;
import com.developer.mohamedraslan.hossamexams.JsonModel.Results_References;
import com.developer.mohamedraslan.hossamexams.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

public class ExamsResults_Rec_Adapter  extends FirebaseRecyclerAdapter<Results_References,ViewHolder2> {
    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
     *                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    FragmentManager fragmentManager;
    String depName ;
    String yearName ;
    String unitName ;
    AnimatedDialog dialog;
    Context mContext;


    public ExamsResults_Rec_Adapter(Class<Results_References> modelClass, int modelLayout, Class<ViewHolder2> viewHolderClass, Query ref , FragmentManager fragmentManager ,
                                    String depName , String yearName  , String unitName, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.fragmentManager = fragmentManager;
        dialog               = new AnimatedDialog(context);
        this.depName         = depName;
        this.yearName        = yearName;
        this.unitName        = unitName;
        this.mContext        = context;

    }

    @Override
    protected void populateViewHolder(final ViewHolder2 holder, final Results_References model, int position) {

        holder.ExamID = model.getExamID();

        holder.Cardview.setScaleX(.9f);
        holder.Cardview.setScaleY(.9f);
        holder.Cardview.animate().scaleX(1f).scaleY(1f).setDuration(500);
        holder.ExamName.setText(model.getExamName());
        holder.Date.setText(model.getExamDate());

        holder.BtnResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("ExamID", model.getExamID());
                bundle.putString("studentResultinDepname",depName);
                bundle.putString("studentResultinYearName",yearName);
                bundle.putString("studentResultinUnitName",unitName);

                // set MyFragment Arguments
                StudentResult StudentResult = new StudentResult();
                StudentResult.setArguments(bundle);


                        fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.Exam_Frame,StudentResult)
                        .addToBackStack(null)
                        .commit();

            }
        });
        holder.Cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(mContext, holder.Cardview);

                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.delete_results, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.Delete_results){

                            //Deleting
                            final AlertDialog alertDialog = new AlertDialog(mContext,"تحذير"," يرجي العلم عند حذف هذه النتائج سوف يتم ازالتها من قائمة نتائجي عند جميع الطلبة وسوف يتم ازالة الاختبار من قائمة الاختبارات ايضا( في حالة وجوده) , متأكد ؟ ");
                            alertDialog.show();
                            alertDialog.btnYes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    dialog.ShowDialog();
                                    Delete_Exam(model.getExamID());
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
    private void Delete_Exam(final String examID){

        Query reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.RESULT.getRef()).child(depName).child(yearName).child(unitName)
                .orderByChild("examID")
                .equalTo(examID);

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



        DatabaseReference reference2  = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.STARTEDEXAM.getRef()).child(depName).child(yearName).child(unitName)
                .child(examID);

        reference2.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                DatabaseReference reference3  = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.ResultsRef.getRef()).child(depName).child(yearName).child(unitName)
                        .child(examID);
                reference3.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        DatabaseReference reference4  = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.EXAMS.getRef())
                                .child(examID);
                        reference4.removeValue();

                        dialog.Close_Dialog();
                        AlertDialog alertDialog = new AlertDialog(mContext,"تم حذف النتائج بنجاح");
                        alertDialog.show();
                    }
                });

            }
        });

    }

    private void applyFontToMenuItem(MenuItem mi) {

        Typeface font = Typeface.createFromAsset(mContext.getAssets(),"atherfont.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypeFaceSpan("", font, Color.WHITE), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);

    }
}
