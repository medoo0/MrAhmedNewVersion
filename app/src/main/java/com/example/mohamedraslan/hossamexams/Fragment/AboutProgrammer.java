package com.example.mohamedraslan.hossamexams.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mohamedraslan.hossamexams.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class AboutProgrammer extends Fragment {

    @BindView(R.id.circleImageView)
    CircleImageView Alaa ;


    AdView mAdView;
    @BindView(R.id.facebookm)
    ImageView facebookm;

    @BindView(R.id.facebooka)
    ImageView facebooka;

    @BindView(R.id.circleImageView2)
    CircleImageView Mohamed;

    @BindView(R.id.textView)
    TextView textView ;

    @BindView(R.id.textView2)
    TextView textView2;
    PublisherAdView mPublisherAdView;

    @BindView(R.id.Scrollview)
    ScrollView Scrollview;

    int MohamedX ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_about_programmer, container, false);
        ButterKnife.bind(this,view);
        mPublisherAdView = view.findViewById(R.id.publisherAdView);
        textView.setAlpha(0);
        textView2.setAlpha(0);
        //Animation .
        Mohamed.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                MohamedX = (int) Mohamed.getX();
                Alaa.animate().translationXBy(MohamedX).rotation(360).setDuration(1000);
                Mohamed.animate().translationXBy(-MohamedX).rotation(360).setDuration(1000);
                facebookm.animate().translationXBy(MohamedX).rotation(360).setDuration(1000);
                facebooka.animate().translationXBy(-MohamedX).rotation(360).setDuration(1000);
                textView.animate().alpha(1).setDuration(3000);
                textView2.animate().alpha(1).setDuration(3000);
            }
        });

        Animation downtoup = AnimationUtils.loadAnimation(getActivity(),R.anim.downtoup);
        Scrollview.setAnimation(downtoup);


        facebookm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getActivity(),"https://www.facebook.com/alaadaherdubai");
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);

            }
        });


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
                Toast.makeText(getActivity(), "Fauilure", Toast.LENGTH_SHORT).show();
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


        facebooka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getActivity(),"https://www.facebook.com/mohamed.raslan.908");
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);

            }
        });





        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
    public String getFacebookPageURL(Context context , String FACEBOOK_URL ) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_URL;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
}
