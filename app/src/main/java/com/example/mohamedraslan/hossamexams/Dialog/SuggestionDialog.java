package com.example.mohamedraslan.hossamexams.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mohamedraslan.hossamexams.Adapter.Suggestion_Dialog_Adapter;
import com.example.mohamedraslan.hossamexams.Contracts.SigninContract;
import com.example.mohamedraslan.hossamexams.Contracts.SuggestionDialogInterface;
import com.example.mohamedraslan.hossamexams.R;

import java.util.List;

public class SuggestionDialog extends Dialog implements SuggestionDialogInterface.MainView {

    ProgressBar progressBar;
    SigninContract.view view;
    TextView noSuggestion;
    RecyclerView emailssuggetion;
    Context context;
    List <String> names;
    public SuggestionDialog(@NonNull Context context, int themeResId, SigninContract.view view, List<String>list) {
        super(context, themeResId);
        this.view   = view;
        this.context = context;
        this.names  = list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.suggestion_dialog);
        noSuggestion    = findViewById(R.id.noSuggestion);
        emailssuggetion = findViewById(R.id.emailssuggetion);
        progressBar     = findViewById(R.id.loading);
        progressBar.setVisibility(View.VISIBLE);
        Suggestion_Dialog_Adapter suggestion_dialog_adapter = new Suggestion_Dialog_Adapter(context,names,progressBar,this);
        suggestion_dialog_adapter.notifyDataSetChanged();
        emailssuggetion.setLayoutManager(new LinearLayoutManager(context));
        emailssuggetion.setAdapter(suggestion_dialog_adapter);


        noSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SuggestionDialog.this.dismiss();
            }
        });
    }

    @Override
    public void getMyEmails(String email) {

        view.getEmailinEditText(email);

    }
}
