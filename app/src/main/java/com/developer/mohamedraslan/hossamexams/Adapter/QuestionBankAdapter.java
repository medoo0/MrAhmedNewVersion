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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developer.mohamedraslan.hossamexams.Contracts.QuestionsBankContract;
import com.developer.mohamedraslan.hossamexams.Dialog.CustomTypeFaceSpan;
import com.developer.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.developer.mohamedraslan.hossamexams.MainPresnter.Question_BankPresenter;
import com.developer.mohamedraslan.hossamexams.R;

import java.util.ArrayList;
import java.util.List;


public class QuestionBankAdapter extends RecyclerView.Adapter<QuestionBankAdapter.ViewHolder> {
    public static List<Questions_Form> qestions ;
    private List<Questions_Form> listnew;
    Context context;
    String depName , yearName , unitName;

    QuestionsBankContract.view listinParent;
    QuestionsBankContract.presenter presenter;
    public QuestionBankAdapter(List<Questions_Form> qestions, Context context, QuestionsBankContract.view view,String depName , String yearName , String unitName){
        this.listinParent = view;
        this.qestions     = qestions;
        this.depName      = depName;
        this.yearName     = yearName;
        this.unitName          = unitName;
        this.context      = context;
        presenter         = new Question_BankPresenter(listinParent) ;

    }

    @NonNull
    @Override
    public QuestionBankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.qestion_rec_layout,parent,false);
        return new QuestionBankAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionBankAdapter.ViewHolder holder, int position) {
        final int p = position;
        holder.Question.setText(qestions.get(position).getQuestion());
        //animation
        holder.Cardview.setScaleX(.9f);
        holder.Cardview.setScaleY(.9f);
        holder.Cardview.animate().scaleX(1f).scaleY(1f).setDuration(500);

        //animation 2
        holder.background.setScaleX(.9f);
        holder.background.setScaleY(.9f);
        holder.background.animate().scaleX(1f).scaleY(1f).setDuration(500);

        listinParent.nuberQuestions(getItemCount());


        holder.Cardview.setOnClickListener(new View.OnClickListener() {    // when click to edit question
            @Override
            public void onClick(final View view) {

                PopupMenu popup = new PopupMenu(context, holder.Cardview);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.edit_q_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


                    @Override
                    public boolean onMenuItemClick(MenuItem item) {


                        if (item.getItemId() == R.id.edit){

                            listinParent.updateFragbyValuesTogoEditFrag(qestions.get(p).getQuestionID(),depName,yearName,unitName);

                        }
                        if (item.getItemId() == R.id.delete){


                              listinParent.removingQuestion(qestions.get(p).getQuestionID(),p,depName,yearName,unitName);


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

    public void remove(int position){

        if (position != RecyclerView.NO_POSITION) {
            qestions.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, qestions.size());

        }



        for (int i=0  ; i<qestions.size() ; i++){

            Log.d("message",qestions.get(i).getQuestion());

        }

    }





    @Override
    public int getItemCount() {

            return qestions.size();

    }


   static public class ViewHolder extends RecyclerView.ViewHolder {
        CardView Cardview;
        LinearLayout background;
        TextView tx;
        TextView Question;
        public ViewHolder(View itemView) {
            super(itemView);
            Question = itemView.findViewById(R.id.Question);
            Cardview = itemView.findViewById(R.id.Cardview);
            background = itemView.findViewById(R.id.Cardview2);
            tx = itemView.findViewById(R.id.tx);
        }
    }




    public Filter getFilter() {


        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Questions_Form> FilteredArrList = new ArrayList<>();
                if (listnew == null) {

                    listnew = new ArrayList<>(qestions); // saves the original data in mOriginalValues

                }

                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = listnew.size();
                    results.values = listnew;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < listnew.size(); i++) {
                        String data = listnew.get(i).getQuestion();
                        if (data.toLowerCase().contains(constraint.toString())) {
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

                qestions = (ArrayList<Questions_Form>) results.values;
                if(results.count<1){

                    listinParent.nuberQuestions(0);
                }

                notifyDataSetChanged();

            }
        };
        return filter;
    }


    private void applyFontToMenuItem(MenuItem mi) {

        Typeface font = Typeface.createFromAsset(context.getAssets(),"atherfont.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypeFaceSpan("", font, Color.WHITE), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);

    }

}
