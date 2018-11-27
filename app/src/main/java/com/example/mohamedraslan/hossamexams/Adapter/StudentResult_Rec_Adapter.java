package com.example.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import com.example.mohamedraslan.hossamexams.Contracts.StudentResultContract;
import com.example.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.example.mohamedraslan.hossamexams.Fragment.StudentsWrongs;
import com.example.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.example.mohamedraslan.hossamexams.JsonModel.FullResult;
import com.example.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.example.mohamedraslan.hossamexams.JsonModel.Resister_form;
import com.example.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.example.mohamedraslan.hossamexams.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by microprocess on 2018-10-20.
 */

public class StudentResult_Rec_Adapter  extends RecyclerView.Adapter<ViewHolder3> {

    int photosCounter = 0 ;
    public List <Result_Pojo> list,listnew;
    Context context;
    StudentResultContract.MainView view1;


    public StudentResult_Rec_Adapter(List<Result_Pojo> list, Context context,StudentResultContract.MainView view) {
        this.list = list;
        this.context = context;
        this.view1 = view;
    }

    @NonNull
    @Override
    public ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(context).inflate(R.layout.student_result_rec_layout,parent,false);

        ViewHolder3 viewHolder3 = new ViewHolder3(row);

        return viewHolder3;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder3 holder, final int position) {

        //photos changer .
        if (photosCounter == 0 ) {
            holder.circleImageView.setBackgroundResource(R.drawable.ic_student_1);
            holder.circleImageView.setTag(R.drawable.ic_student_1);
            photosCounter ++ ;
        }
        else if (photosCounter == 1) {
            holder.circleImageView.setBackgroundResource(R.drawable.ic_student_2);
            holder.circleImageView.setTag(R.drawable.ic_student_2);
            photosCounter ++ ;
        }
        else if( photosCounter == 2 )
        {
            holder.circleImageView.setBackgroundResource(R.drawable.ic_student_3);
            holder.circleImageView.setTag(R.drawable.ic_student_3);
            photosCounter ++ ;
        }
        else {
            holder.circleImageView.setBackgroundResource(R.drawable.ic_student_4);
            holder.circleImageView.setTag(R.drawable.ic_student_4);
            photosCounter = 0;
        }



        holder.txDegree.setText(list.get(position).getTotal());
        holder.txFinalDegree.setText(list.get(position).getFinalDegree());
        holder.txName.setText(list.get(position).getUserName());
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.USERREF.getRef())
//                .child(list.get(position).getUid());
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    //Get Student Name .
//                    Resister_form form = dataSnapshot.getValue(Resister_form.class);
//                    holder.txName.setText(form.getNameStudent()+"");
//
//                }
//                else {
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        if (position % 2 == 0) {

            holder.cardView.setX(-1000);
            holder.cardView.animate().translationXBy(1000).setDuration(800);
        }
        else
        {

            holder.cardView.setX(1000);
            holder.cardView.animate().translationXBy(-1000).setDuration(800);

        }
//
        view1.numberResult(getItemCount());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getWrongQuestions() != null){

                    if(list.get(position).getWrongQuestions().size() > 0 ) {


                        view1.showErrorsFragment(list.get(position).getUserName(),list.get(position).getFinalDegree(),list.get(position).getTotal()
                        ,list.get(position).getExamID(),list.get(position).getWrongQuestions(), (Integer) holder.circleImageView.getTag(),list.get(position).getUid()
                        ,holder.circleImageView);


                    }



                }else {


                    view1.showErrorsFragment(list.get(position).getUserName(),list.get(position).getFinalDegree(),list.get(position).getTotal()
                            ,list.get(position).getExamID(),list.get(position).getWrongQuestions(), (Integer) holder.circleImageView.getTag(),list.get(position).getUid()
                            ,holder.circleImageView);


                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public Filter getFilter() {




        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Result_Pojo> FilteredArrList = new ArrayList<>();
                if (listnew == null) {

                    listnew = new ArrayList<>(list); // saves the original data in mOriginalValues

                }

                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = listnew.size();
                    results.values = listnew;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < listnew.size(); i++) {



                        String data = listnew.get(i).getUserName().toString();

                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(listnew.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                list = (ArrayList<Result_Pojo>) results.values;

                if (results.count<1){

                    view1.numberResult(0);
                }
                // has the filtered values
                notifyDataSetChanged();

            }
        };
        return filter;
    }




}
