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
import android.widget.RelativeLayout;
import android.widget.ScrollView;


import com.example.mohamedraslan.hossamexams.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutDoctor extends Fragment {

    //card view
    @BindView(R.id.Press_on_CardView)
    CardView cardView;



    //imageview
    @BindView(R.id.image_dropdown)
    ImageView dropdown ;
    @BindView(R.id.image_dropdown2)
    ImageView dropdown2 ;


    //Details layout .
    @BindView(R.id.Details_layout)
    RelativeLayout Details_layout;



    @BindView(R.id.facebook)
    ImageView facebook;

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

        //animation
        cardView.setScaleX(.9f);
        cardView.setScaleY(.9f);
        cardView.animate().scaleX(1f).scaleY(1f).setDuration(500);
        //animation

        //animation




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



        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getActivity(),"https://www.facebook.com/profile.php?id=100007437755276");
                        facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

        return v ;
    }

    void openAnimation(RelativeLayout CardDownlayout ){

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
