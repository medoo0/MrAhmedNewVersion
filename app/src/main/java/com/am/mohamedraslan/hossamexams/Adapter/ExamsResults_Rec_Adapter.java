package com.am.mohamedraslan.hossamexams.Adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.am.mohamedraslan.hossamexams.Fragment.StudentResult;
import com.am.mohamedraslan.hossamexams.JsonModel.Results_References;
import com.am.mohamedraslan.hossamexams.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

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
    public ExamsResults_Rec_Adapter(Class<Results_References> modelClass, int modelLayout, Class<ViewHolder2> viewHolderClass, Query ref , FragmentManager fragmentManager ) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.fragmentManager = fragmentManager;

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

    }


}
