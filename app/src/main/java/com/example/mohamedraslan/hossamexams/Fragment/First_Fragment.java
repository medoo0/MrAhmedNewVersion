package com.example.mohamedraslan.hossamexams.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mohamedraslan.hossamexams.Contracts.MainActivityContract;
import com.example.mohamedraslan.hossamexams.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class First_Fragment extends Fragment {

@BindView(R.id.Goto_signin)
Button Sign_in;

@BindView(R.id.Goto_signup)
Button Sign_up;
AdView mAdView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v =inflater.inflate(R.layout.fragment_first_, container, false);
// ca-app-pub-3940256099942544~3347511713
         ButterKnife.bind(this,v); // intialize butterknife .
        mAdView = v.findViewById(R.id.adView);

        MobileAds.initialize(getActivity(), "ca-app-pub-4214877267260040~2367951421");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Sign_in.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // add fragment (sign in )

                 try {
                     MainActivityContract.View view1 = (MainActivityContract.View) getActivity();

                     if (view1!=null){
                         view1.showLoginFragment();
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }

             }
         });

         Sign_up.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 // we will listen in activity to update fragment .....//

                 MainActivityContract.View view1 = (MainActivityContract.View) getActivity();
                 if (view1!=null){

                     view1.showFragmentRegister();

                 }

                 // add fragment (sign up )


             }
         });





        return v;
    }

}
