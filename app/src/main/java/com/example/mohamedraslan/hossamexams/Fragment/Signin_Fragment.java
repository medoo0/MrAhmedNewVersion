package com.example.mohamedraslan.hossamexams.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mohamedraslan.hossamexams.Contracts.MainActivityContract;
import com.example.mohamedraslan.hossamexams.Contracts.SigninContract;
import com.example.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.example.mohamedraslan.hossamexams.MainPresnter.SigninPresenter;
import com.example.mohamedraslan.hossamexams.R;
import com.example.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

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

    @BindView(R.id.password)
    EditText et_password;
    AdView mAdView;

    @BindView(R.id.email_sign_in_button)
    Button SignIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_signin, container, false);

        // initialize butterknife library .
        ButterKnife.bind(this , v );
        mAdView = v.findViewById(R.id.adView);
        MobileAds.initialize(getActivity(),
                "ca-app-pub-4214877267260040~2367951421");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //initialize dialog with context .
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
    public void logInResult(String Result) {

        if (Result.equals("Successful")){


            //Successful Login .
            dialog.Close_Dialog();
            //Toast.makeText(getActivity(), Result, Toast.LENGTH_SHORT).show();

            getActivity().finish();

            //Start Activity ControlPanel
            Intent intent = new Intent(getActivity(),ControlPanel.class);
            startActivity(intent);


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
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
