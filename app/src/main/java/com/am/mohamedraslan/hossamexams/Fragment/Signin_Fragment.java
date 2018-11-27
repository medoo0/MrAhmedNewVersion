package com.am.mohamedraslan.hossamexams.Fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.am.mohamedraslan.hossamexams.Contracts.MainActivityContract;
import com.am.mohamedraslan.hossamexams.Contracts.SigninContract;
import com.am.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.am.mohamedraslan.hossamexams.Dialog.SuggestionDialog;
import com.am.mohamedraslan.hossamexams.MainPresnter.SigninPresenter;
import com.am.mohamedraslan.hossamexams.R;
import com.am.mohamedraslan.hossamexams.SqLite.Operation;
import com.am.mohamedraslan.hossamexams.SqLite.SQlHelper;
import com.am.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Signin_Fragment extends Fragment implements SigninContract.view {

    SigninContract.presenter presenter ;
    AnimatedDialog dialog ;

    @BindView(R.id.email)
    EditText et_email;

    SuggestionDialog suggestionDialog;

    List<String> emails;

    SQLiteDatabase dp,dpWrite;
    SQlHelper helper;

    PublisherAdView mPublisherAdView;

    @BindView(R.id.password)
    EditText et_password;
    AdView mAdView;

    @BindView(R.id.email_sign_in_button)
    Button SignIn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper   = new SQlHelper(getActivity());
        dp       = helper.getReadableDatabase();
        dpWrite  = helper.getWritableDatabase();
        emails   = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_signin, container, false);

        Cursor pointer  = Operation.getData(dp);

        if (pointer!=null){

            while (pointer.moveToNext()){
                emails.add(pointer.getString(pointer.getColumnIndex("COL1")));
            }


            suggestionDialog = new SuggestionDialog(Objects.requireNonNull(getActivity()),R.style.PauseDialog,this,emails);
            suggestionDialog.show();


        }



        // initialize butterknife library .
        ButterKnife.bind(this , v );
        mPublisherAdView = v.findViewById(R.id.publisherAdView);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        mPublisherAdView.loadAd(adRequest);
        mPublisherAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });



        dialog =  new AnimatedDialog(getActivity());

        //initialize presenter
        presenter = new SigninPresenter(this);

        // solve problem of toggle password button and font with password
        et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et_password.setTypeface(Typeface.DEFAULT);


        ///
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check first if some thing empty .

               presenter.passtocheck(et_email.getText().toString(),et_password.getText().toString());

            }
        });

         return  v ;
    }



    // goto Register fragment
    @OnClick(R.id.txt_register)
    void Goto_Register_fragment (View view){
        //add fragment
        MainActivityContract.View view1 = (MainActivityContract.View) getActivity();
        if (view1!=null){

            view1.showFragmentRegister();
        }
    }






    @Override
    public void checkResult(String Result) {

       if(Result.equals("Successful")){


           SignIn.setEnabled(false);

           dialog.ShowDialog();
           presenter.passlogIn(et_email.getText().toString().trim() , et_password.getText().toString());

       }
        else if (Result.equals(getString(R.string.write_email))){

           et_email.setError(getString(R.string.write_email));
           et_email.requestFocus();
        }
        else if (Result.equals(getString(R.string.write_email_in_correct_way))){

           et_email.setError(getString(R.string.write_email_in_correct_way));
           et_email.requestFocus();
        }
        else if (Result.equals(getString(R.string.enterPass))) {

           et_password.setError(getString(R.string.enterPass),null);
           et_password.requestFocus();
        }
        else if(Result.equals(getString(R.string.less12char))) {

           et_password.setError(getString(R.string.less12char),null);
           et_password.requestFocus();
        }


    }

    @Override
    public void logInResult(String Result,String email) {

        if (Result.equals("Successful")){


            if (!Operation.AreExisitEmail(dp,email)){

                if (Operation.storingEmails(dpWrite,email)){

                    //Successful Login .
                    dialog.Close_Dialog();
                    //Toast.makeText(getActivity(), Result, Toast.LENGTH_SHORT).show();

                    getActivity().finish();

                    //Start Activity ControlPanel
                    Intent intent = new Intent(getActivity(),ControlPanel.class);
                    startActivity(intent);

                }

            }else {


                dialog.Close_Dialog();
                //Toast.makeText(getActivity(), Result, Toast.LENGTH_SHORT).show();

                getActivity().finish();

                //Start Activity ControlPanel
                Intent intent = new Intent(getActivity(),ControlPanel.class);
                startActivity(intent);


            }





        }
        else {

            SignIn.setEnabled(true);
            //Failure Login .
            dialog.Close_Dialog();
            if (isNetworkConnected()) {
                et_password.setError(getString(R.string.PleaseMakesure),null);

            }
            else {
                et_password.setError(getString(R.string.makesure_fromNetword),null);
            }
        }




    }

    @Override
    public void getEmailinEditText(String email) {

        suggestionDialog.dismiss();
        et_email.setText(email);


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
