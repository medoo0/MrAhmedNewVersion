package com.example.mohamedraslan.hossamexams.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import butterknife.OnClick;


public class AboutDoctor extends Fragment {

    //card view
    @BindView(R.id.Press_on_CardView)
    CardView cardView;


    @BindView(R.id.Press_on_CardView2)
    CardView Press_on_CardView2;

    //imageview
    @BindView(R.id.image_dropdown)
    ImageView dropdown ;
    @BindView(R.id.image_dropdown2)
    ImageView dropdown2 ;


    //Details layout .
    @BindView(R.id.Details_layout)
    LinearLayout Details_layout;

    PublisherAdView mPublisherAdView;


    @BindView(R.id.facebook)
    ImageView facebook;

    @BindView(R.id.twitter)
    ImageView twitter;

    @BindView(R.id.Details_layout2)
    LinearLayout Details_layout2;

    @BindView(R.id.youtube)
    ImageView youtube;

    @BindView(R.id.website)
    ImageView website;

  AdView mAdView;
    @BindView(R.id.scroll)
    ScrollView scroll;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_about_doctor, container, false);
        ButterKnife.bind(this,v);
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
        //animation
        cardView.setScaleX(.9f);
        cardView.setScaleY(.9f);
        cardView.animate().scaleX(1f).scaleY(1f).setDuration(500);
        Press_on_CardView2.setScaleX(.9f);
        Press_on_CardView2.setScaleY(.9f);
        Press_on_CardView2.animate().scaleX(1f).scaleY(1f).setDuration(500);
        //animation

        //animation

        Press_on_CardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Details_layout2.isShown()){
                    Details_layout2.setVisibility(View.GONE);
                    dropdown2.setImageResource(R.drawable.ic_dropdown);
                }
                else {
                    Details_layout2.setVisibility(View.VISIBLE);
                    dropdown2.setImageResource(R.drawable.ic_dropup);
                    openAnimation(Details_layout2);
                }
                focusOnView(Press_on_CardView2);

            }
        });



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Details_layout.isShown()){
                    Details_layout.setVisibility(View.GONE);
                    dropdown.setImageResource(R.drawable.ic_dropdown);
                }
                else {
                    Details_layout.setVisibility(View.VISIBLE);
                    dropdown.setImageResource(R.drawable.ic_dropup);
                    openAnimation(Details_layout);
                }
                focusOnView(cardView);

            }
        });



        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,   Uri.parse("https://www.youtube.com/ahmedsamyy?fbclid=IwAR0uPkrDCbzMGRV_tpd-WIuZGHwmQNCHhdMYKM_RKVb6EBmVQoUWpZ1H9x4")));
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getActivity(),"https://www.facebook.com/ahmedsamy37");
                        facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ahmedsamy3000")));
                }catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + "ahmedsamy3000")));
                }


            }
        });


        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://english2fun.com/vb/forum.php"));
                startActivity(intent);

            }
        });

        return v ;
    }

    void openAnimation(LinearLayout CardDownlayout ){

        CardDownlayout.setScaleX(0.0f);
        CardDownlayout.setScaleY(0.0f);
        CardDownlayout.animate().scaleX(1f).scaleY(1f).setDuration(300);
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
    private final void focusOnView(final CardView cardView){
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.smoothScrollTo(0, cardView.getTop());
            }
        });
    }
}
