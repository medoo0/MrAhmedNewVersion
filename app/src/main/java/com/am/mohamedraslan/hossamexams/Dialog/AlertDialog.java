package com.am.mohamedraslan.hossamexams.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.am.mohamedraslan.hossamexams.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by microprocess on 2018-10-02.
 */

public class AlertDialog extends Dialog {

    @BindView(R.id.title)
    TextView txTitle;
    @BindView(R.id.Message)
    TextView txMessage;
    @BindView(R.id.yes)
    public Button btnYes;
    @BindView(R.id.no)
    public Button btnNo;

    Context context ;
    String Title;
    String Message;
    boolean Ok = false;
    public AlertDialog(@NonNull Context context, String Title , String Message) {
        super(context);

        this.context = context;
        this.Title = Title;
        this.Message = Message;

        Ok = false ;
    }

    public AlertDialog(@NonNull Context context, String Message) {
        super(context);

        this.context = context;
        this.Title = context.getString(R.string.app_name);
        this.Message = Message;
        Ok = true ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.aleartdialog);

        ButterKnife.bind(this);



        txTitle.setText(Title);
        txMessage.setText(Message);


        if (Ok){ // Alert Dialog (ok only) .
            btnYes.setVisibility(View.GONE);
            btnNo.setTextColor(context.getResources().getColor(R.color.color4));
            btnNo.setText(R.string.Ok);
        }


        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



    }




}
