package com.example.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mohamedraslan.hossamexams.Contracts.SuggestionDialogInterface;
import com.example.mohamedraslan.hossamexams.R;

import java.util.List;

public class Suggestion_Dialog_Adapter extends RecyclerView.Adapter<Suggestion_Dialog_Adapter.HolderSuggestion> {

    Context context;
    ProgressBar progressBar;
    List<String>list;
    SuggestionDialogInterface.MainView mainView;
    public Suggestion_Dialog_Adapter(Context context, List<String> names,ProgressBar progressBar,SuggestionDialogInterface.MainView mainView) {
        this.context = context;
        this.list = names;
        this.progressBar = progressBar;
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public HolderSuggestion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row                          = LayoutInflater.from(context).inflate(R.layout.suggestion_holder,parent,false);
        HolderSuggestion holderSuggestion = new HolderSuggestion(row);
        return           holderSuggestion ;

    }

    @Override
    public void onBindViewHolder(@NonNull HolderSuggestion holder, final int position) {

        holder.myemailshere.setText(list.get(position));
        progressBar.setVisibility(View.GONE);
        holder.emailsSuggestionss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainView.getMyEmails(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderSuggestion extends RecyclerView.ViewHolder{
        TextView myemailshere;
        CardView emailsSuggestionss;
        public HolderSuggestion(View itemView) {
            super(itemView);
            myemailshere       = itemView.findViewById(R.id.myemailshere);
            emailsSuggestionss = itemView.findViewById(R.id.emailsSuggestionss);
        }
    }

}
