package com.developer.mohamedraslan.hossamexams.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.developer.mohamedraslan.hossamexams.Contracts.MainActivityContract;
import com.developer.mohamedraslan.hossamexams.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

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

@BindView(R.id.imageAn)
    ImageView imageAn;
    PublisherAdView  mPublisherAdView;
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



        Animation animation;
        animation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.fromtoptobottom);
        imageAn.setAnimation(animation);

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

                     view1.showParentOrStudentFragment();

                 }

                 // add fragment (sign up )


             }
         });





        return v;
    }

}
