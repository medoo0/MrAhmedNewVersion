package com.example.mohamedraslan.hossamexams.Views;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamedraslan.hossamexams.Contracts.ExamContract;
import com.example.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.example.mohamedraslan.hossamexams.MainPresnter.ExamPresenter;
import com.example.mohamedraslan.hossamexams.R;
import com.example.mohamedraslan.hossamexams.Services.TimerServices;
import com.example.mohamedraslan.hossamexams.SqLite.SQlHelper;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Exam extends AppCompatActivity implements View.OnClickListener , ExamContract.view{
    @BindView(R.id.Liner1)
    LinearLayout layout1 ;

    @BindView(R.id.contains)
    RelativeLayout relativeLayout;

    @BindView(R.id.reltwo)
    RelativeLayout relativeLayout2;

    @BindView(R.id.answerOne)
    Button buttonA;

    @BindView(R.id.answerTwo)
    Button buttonB;

    @BindView(R.id.answerThree)
    Button buttonC;

    @BindView(R.id.answerFour)
    Button buttonD;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.Question)
    TextView textView;

    @BindView(R.id.Timer)
    TextView Timer;

    Animation LTR,RTL,downtoup;
    Drawable trueClick,falseClick;

    private String selectAnswer = "",ID_Qestion="" , oneQestionDegree = "" , final_degree = "";

    ExamContract.presenter presenter;
    SQLiteDatabase db;
    String TableName = "";
    private String Examname;
    private String ExamDate;
    Intent Timerservices;
    BroadcastReceiver broadcastReceiver ;
    boolean OpenJustOnce = true ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
        intialViews();
        //Animation
        Animation();








        Bundle bundle = getIntent().getExtras();
        if( bundle != null ){
             TableName = bundle.getString("SqlTableName");


            oneQestionDegree = bundle.getString("oneQestionDegree");
            final_degree = bundle.getString("final_degree");
            Examname = bundle.getString("Examname");
            ExamDate = bundle.getString("ExamDate");



        }

        //start Timer Config .
        Timerservices = new Intent(this, TimerServices.class);
        Timerservices.putExtra("TableID",TableName.replace(FirebaseAuth.getInstance().getCurrentUser().getUid(),"").substring(1));
        Timerservices.putExtra("final_degree",final_degree);
        Timerservices.putExtra("Examname",Examname);
        Timerservices.putExtra("ExamDate",ExamDate);


        SQlHelper helper = new SQlHelper(this);
        db = helper.getWritableDatabase();
        presenter.getQuestion(db,TableName);





    }
    private void intialViews(){

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        trueClick                 = Objects.requireNonNull(this).getDrawable(R.drawable.radiobutton_check_true);
        falseClick                = Objects.requireNonNull(this).getDrawable(R.drawable.radiobutton_check_false);

        presenter = new ExamPresenter(this);
    }
    private void Animation(){
        //Animation
        LTR =  AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
        RTL = AnimationUtils.loadAnimation(this,R.anim.slide_in_right);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        layout1.setAnimation(RTL);
        relativeLayout.startAnimation(LTR);
        relativeLayout2.startAnimation(downtoup);

    }


    @Override
    public void onClick(View view) {

        if (view == buttonA){

            selectAnswer = buttonA.getText().toString();
            buttonA.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            buttonD.setBackground(falseClick);
        }

        if (view == buttonB){

            selectAnswer = buttonB.getText().toString();
            buttonB.setBackground(trueClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);
            buttonD.setBackground(falseClick);
        }

        if (view == buttonC){

            selectAnswer = buttonC.getText().toString();
            buttonC.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonD.setBackground(falseClick);

        }
        if (view == buttonD){

            selectAnswer = buttonD.getText().toString();
            buttonD.setBackground(trueClick);
            buttonB.setBackground(falseClick);
            buttonA.setBackground(falseClick);
            buttonC.setBackground(falseClick);

        }

    }

        @OnClick(R.id.Skip)
        void BtnSkip(View view){

            final MediaPlayer player = MediaPlayer.create(this,R.raw.plucky);
            player.start();
            presenter.Skip(db,TableName,ID_Qestion);

        }


        @OnClick(R.id.Next)
        void BtnNext(View view){

            if(!selectAnswer.isEmpty()) {

                final MediaPlayer player = MediaPlayer.create(this,R.raw.plucky);
                player.start();
                presenter.insertAnswerInSql(db,TableName,ID_Qestion,selectAnswer,oneQestionDegree);
            }
            else {
                Toast.makeText(this,"Please Select Answer", Toast.LENGTH_SHORT).show();
            }


        }

    @Override
    public void quetionIs(String ID_Qestion, String question, String answerOne, String answerTwo, String answerThree, String answerFour, String correctAnswer) {
        textView.setText(question);
        buttonA.setText(answerOne);
        buttonB.setText(answerTwo);
        buttonC.setText(answerThree);
        buttonD.setText(answerFour);
        this.ID_Qestion = ID_Qestion ;


        //best place To open Timer Here .
        if(OpenJustOnce){
            startService(Timerservices);
            OpenJustOnce = false ;
        }
    }

    @Override
    public void Problem(String s) {
            if(!isFinishing()) {

                AlertDialog alertDialog = new AlertDialog(this, s);
                alertDialog.show();

            }
    }

    @Override
    public void AnswerInserted() {

        buttonD.setBackground(falseClick);
        buttonB.setBackground(falseClick);
        buttonA.setBackground(falseClick);
        buttonC.setBackground(falseClick);


        //Last Thing.
        selectAnswer = "" ;
        scrollView.scrollTo(5, 10);
        Animation();
        //get Another Question.
        presenter.getQuestion(db,TableName);
    }

    @Override
    public void Skipped() {

        selectAnswer = "" ;
        scrollView.scrollTo(5, 10);
        Animation();
        buttonD.setBackground(falseClick);
        buttonB.setBackground(falseClick);
        buttonA.setBackground(falseClick);
        buttonC.setBackground(falseClick);
        //get Another Question.
        presenter.getQuestion(db,TableName);
    }

    @Override
    public void ExamEnd(String s) {
        Intent intent = new Intent(this,Result.class);
        intent.putExtra("SqlTableName",TableName);
        intent.putExtra("Message",s);
        intent.putExtra("final_degree",final_degree);
        intent.putExtra("Examname",Examname);
        intent.putExtra("ExamDate",ExamDate);
        startActivity(intent);
        finish();
    }

    @Override
    public void BlockScreen(String s) {

        //لا تنس إغلاق ال Services هنا
        stopService(Timerservices);

        AlertDialog alertDialog = new AlertDialog(this,s);
        alertDialog.show();
        alertDialog.setCancelable(false);
        alertDialog.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopService(Timerservices);

        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();



        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("ServicesTimer")) {
                    long hours   =  intent.getLongExtra("hours",0);
                    long minutes =  intent.getLongExtra("minutes",0);
                    long seconds =  intent.getLongExtra("seconds",0);
                    Timer.setText(hours+" : "+minutes+" : "+seconds);


                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        // set the custom action
        intentFilter.addAction("ServicesTimer"); //Action is just a string used to identify the receiver as there can be many in your app so it helps deciding which receiver should receive the intent.
        // register the receiver
        registerReceiver(broadcastReceiver, intentFilter);





    }



    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
