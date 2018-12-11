package com.developer.mohamedraslan.hossamexams.Views;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.Dialog.AddDepartmentDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.NotificationDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.StudentDialog;
import com.developer.mohamedraslan.hossamexams.Fragment.AboutDoctor;
import com.developer.mohamedraslan.hossamexams.Fragment.AboutProgrammer;
import com.developer.mohamedraslan.hossamexams.Fragment.AddQ_frag;
import com.developer.mohamedraslan.hossamexams.Fragment.Department_Questions;
import com.developer.mohamedraslan.hossamexams.Fragment.ExamList;
import com.developer.mohamedraslan.hossamexams.Fragment.ExamsResults;
import com.developer.mohamedraslan.hossamexams.Fragment.Exams_Questions_Student_RequestsForUnit;
import com.developer.mohamedraslan.hossamexams.Fragment.MoreDetailsFromNav;
import com.developer.mohamedraslan.hossamexams.Fragment.MyResults;
import com.developer.mohamedraslan.hossamexams.Fragment.PermissionsFromStudent;
import com.developer.mohamedraslan.hossamexams.Fragment.Question_Bank_Frag;
import com.developer.mohamedraslan.hossamexams.Fragment.RequestFromStudentToExamWhat;
import com.developer.mohamedraslan.hossamexams.Fragment.StudentManagement;
import com.developer.mohamedraslan.hossamexams.Fragment.StudentsWrongs;
import com.developer.mohamedraslan.hossamexams.Fragment.Students_Departments;
import com.developer.mohamedraslan.hossamexams.Fragment.Unites_inDepartmentQ;
import com.developer.mohamedraslan.hossamexams.Fragment.YeareInDepsQuestions;
import com.developer.mohamedraslan.hossamexams.Fragment.Years_In_Deps;
import com.developer.mohamedraslan.hossamexams.Fragment.addExam;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;
import com.developer.mohamedraslan.hossamexams.JsonModel.WorngQestion;
import com.developer.mohamedraslan.hossamexams.MainPresnter.ControlpanelPresnter;
import com.developer.mohamedraslan.hossamexams.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ControlPanel extends AppCompatActivity
                          implements ControlPanelContract.ControlUI
                                   , View.OnClickListener
                                   , NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private ImageView open_nav;
    private DrawerLayout drawer;
    private static NavigationView navigation;
    public static TextView Title;
    private FirebaseAuth auth;
    StudentDialog studentDialog;
    boolean mHasSaveInstanceState;
    ControlpanelPresnter controlpanelPresnter;
    boolean AreAdmin = false;
    AnimatedDialog animatedDialog;
    RelativeLayout snackBarr;
    BroadcastReceiver broadcastReceiver;
    FullRegisterForm fullRegisterForm = null;
    NotificationDialog notificationDialog;
    AddDepartmentDialog addDepartmentDialog;
    CircleImageView circleImageView;
    TextView UserName;
    public static ProgressBar progressBar;
    String depofStudentthahereNow = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawaer);


//        if (getIntent()!=null){
//
//            depofStudentthahereNow = getIntent().getStringExtra("depofME");
//
//        }
//


        // حنسجل الtokendevice في الداتا بيز الاول

        //notification
        FirebaseMessaging.getInstance().subscribeToTopic("all");


        controlpanelPresnter = new ControlpanelPresnter(this);
        controlpanelPresnter.updateUitoViews();

//        controlpanelPresnter.tellmodelAretokenExisitorNot();
        //  حنشةف التوكن متخزن اصلا ولا لا في الداتا بيز

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        controlpanelPresnter.tellModeltostoreToken(refreshedToken);


        //اخفاء الادوات من المستخدم العادي
        hideAdminToolsFromUsers();

        //Check if User Banned افحص المستخدم اذا كان محظور
        controlpanelPresnter.CheckifUserBanned(auth.getCurrentUser().getUid());

        //default fragment .
//        getSupportFragmentManager()
//                .beginTransaction()
//                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
//                .replace(R.id.Exam_Frame,new ExamList())
//                .commit();

        //image profile back ground .
        Random r = new Random();
        int n = r.nextInt(3);
        if (n == 0) {
            circleImageView.setBackgroundResource(R.drawable.ic_student_1);

        } else if (n == 1) {
            circleImageView.setBackgroundResource(R.drawable.ic_student_2);
        } else if (n == 2) {
            circleImageView.setBackgroundResource(R.drawable.ic_student_3);
        } else {
            circleImageView.setBackgroundResource(R.drawable.ic_student_4);
        }

        ///////////
        controlpanelPresnter.CheckifAdmin(auth.getCurrentUser().getUid());
        controlpanelPresnter.getuserName(auth.getCurrentUser().getUid());

    }


    @Override
    public void showWrongsforStudent(Result_Pojo result_pojo, String FinalDegree, String TotalD) {


        StudentsWrongs myResults = new StudentsWrongs();
        Bundle b = new Bundle();
        b.putString("me", "1");
        b.putString("FinalDegree", FinalDegree);
        b.putString("Total", TotalD);
        b.putParcelableArrayList("WrongQuestions", result_pojo.getWrongQuestions());
        myResults.setArguments(b);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Exam_Frame, myResults).addToBackStack(null)
                .commit();

    }

    @Override
    public void showFragmentWrongs(String name, String finalDegree, String total, String examID, ArrayList<WorngQestion> arrayList, Integer imageTag,
                                   String uID, CircleImageView imageView, String depName, String yearname, String unitName) {

        Bundle bundle = new Bundle();
        bundle.putString("Name", name);
        bundle.putString("FinalDegree", finalDegree);
        bundle.putString("Total", total);
        bundle.putString("depName", depName);
        bundle.putString("yearName", yearname);
        bundle.putString("unitName", unitName);
        bundle.putString("examID", examID);
        bundle.putParcelableArrayList("WrongQuestions", arrayList);
        //to pass image to next fragment.
        bundle.putInt("Image", imageTag);
        bundle.putString("UserUid", uID);
        // set MyFragment Arguments
        StudentsWrongs StudentsWrongs = new StudentsWrongs();
        StudentsWrongs.setArguments(bundle);
        ViewCompat.setTransitionName(imageView, "Image");
        getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(imageView, imageView.getTransitionName())
                .replace(R.id.Exam_Frame, StudentsWrongs)
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void showDialogStudent(String what, String depName, String yearName) {

//        studentDialog.dismiss();

//        switch (what){
//
//            case "allStudents":
//
//                StudentManagement all = new StudentManagement();
//                Bundle b = new Bundle();
//                b.putString("what","allStudents");
//                b.putString("depName",depName);
//                b.putString("yearName", yearName);
//                all.setArguments(b);
//                getSupportFragmentManager().popBackStack();
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
//                        .replace(R.id.Exam_Frame,all)
//                        .addToBackStack(null)
//                        .commit();
//
//                break;
//
//           case "myStudents":
//
//               StudentManagement myStudnet = new StudentManagement();
//               Bundle b1 = new Bundle();
//               b1.putString("what","myStudents");
//               b1.putString("depName",depName);
//               b1.putString("yearName", yearName);
//               myStudnet.setArguments(b1);
//               getSupportFragmentManager().popBackStack();
//               getSupportFragmentManager()
//                       .beginTransaction()
//                       .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
//                       .replace(R.id.Exam_Frame,myStudnet)
//                       .addToBackStack(null)
//                       .commit();
//
//               break;









    }

    @Override
    public void initializeViews() {
        toolbar    = findViewById(R.id.toolbar);
        open_nav   = findViewById(R.id.open_nav);
        open_nav.setOnClickListener(this);
        drawer     = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        snackBarr  = findViewById(R.id.snackBarr);
        Menu nav_Menu = navigation.getMenu();
        nav_Menu.findItem(R.id.exit).setEnabled(false);
        Title      = toolbar.findViewById(R.id.toolbar_title);
        auth       = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        animatedDialog = new AnimatedDialog(this);
        View headerLayout = navigation.getHeaderView(0);
        circleImageView = headerLayout.findViewById(R.id.myprofile);
        UserName        = headerLayout.findViewById(R.id.UserName);
        setSupportActionBar(toolbar);
//        open_nav.setOnClickListener(this);
        navigation.setNavigationItemSelectedListener(this);


    }



    @Override
    public void whenClickFAB_showFrag(String depName , String yearName , String unitName) {

        AddQ_frag addQ_frag = new AddQ_frag();
        Bundle b            = new Bundle();
        b.putString("depName1",depName);
        b.putString("yearName1",yearName);
        b.putString("unitName1",unitName);
        addQ_frag.setArguments(b);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Exam_Frame,addQ_frag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void CheckifUserBannedResult(String Result) {

        //اذا كان هذا الطالب محظور

        if(Result.equals("Successful")) {

            if(!ControlPanel.this.isFinishing())
            {

                AlertDialog alertDialog = new AlertDialog(ControlPanel.this
                        , getString(R.string.YouareBanned));
                alertDialog.setCancelable(false);
                alertDialog.show();
                alertDialog.btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(ControlPanel.this , MainActivity.class);
                        startActivity(intent);

                        finish();



                        //لو عاوز المحظور ميعرش يعمل اي اكونت تاني علي البرنامج يبقه الغي السطر الجي
                        auth.signOut();
                    }
                });
            }


        }


    }

    @Override
    public void editQuestions(String questionID, String val,String depName , String yearName , String unitName) {

        AddQ_frag addQ_frag = new AddQ_frag();
        Bundle b = new Bundle();
        b.putString("ID",questionID);
        b.putString("val",val);
        b.putString("depName1",depName);
        b.putString("yearName1",yearName);
        b.putString("unitName1",unitName);
        addQ_frag.setArguments(b);
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Exam_Frame,addQ_frag)
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void editSuccessopenBank(String depName , String yearName , String unitName) {

        Question_Bank_Frag question_bank_frag = new Question_Bank_Frag();
        Bundle b = new Bundle();
        b.putString("depName",depName);
        b.putString("yearName",yearName);
        b.putString("unitName",unitName);
        question_bank_frag.setArguments(b);
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Exam_Frame,question_bank_frag)
                .addToBackStack(null)
                .commit();

//        onBackPressed();

    }




    @Override
    public void onClick(View view) {

        if (view == open_nav){
            //  لو عزت تغير الاتجاه من هنا بتاع فتح ال nav
            if (drawer.isDrawerOpen(Gravity.START)){

                drawer.closeDrawer(Gravity.START);

            }else {
                drawer.openDrawer(Gravity.START);
            }

        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){


 //         first element in Nav  (:   //


            case R.id.more:
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.Exam_Frame,new MoreDetailsFromNav())
                        .commit();

                break;



            case R.id.Units:
                if (fullRegisterForm!=null){

                    Unites_inDepartmentQ years_in_depsQ1 = new Unites_inDepartmentQ();
                    Bundle b1 = new Bundle();
                    b1.putString("depName",fullRegisterForm.getParentYear());
                    b1.putString("yearName",fullRegisterForm.getYear());
                    years_in_depsQ1.setArguments(b1);
                    getSupportFragmentManager().popBackStack();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                            .replace(R.id.Exam_Frame,years_in_depsQ1)
                            .commit();
                }

                break;






            case R.id.questions:
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.Exam_Frame,new Department_Questions()).addToBackStack(null)
                        .commit();
                break;






            case R.id.exit:

                final AlertDialog aleartDialog = new AlertDialog(this,getString(R.string.title),getString(R.string.message));
                aleartDialog.show();
                aleartDialog.btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aleartDialog.dismiss();
//
//                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//                        String uID                = firebaseAuth.getCurrentUser().getUid();
//                         حنتشيك علي الي في الداتا ولو صح حنطلع بره
                        animatedDialog.ShowDialog();

                        if (AreAdmin){

                            // notificationstoAdmin Mr.AhmedSamyFrom Students disable when SignOut
                            FirebaseMessaging.getInstance().unsubscribeFromTopic("Admins");

                        }else {
                            String mytopic = fullRegisterForm.getParentYear()+fullRegisterForm.getYear();

                            FirebaseMessaging.getInstance().unsubscribeFromTopic(mytopic);

                        }

                        auth.signOut();
                        startActivity(new Intent(ControlPanel.this,MainActivity.class));
                        animatedDialog.Close_Dialog();
                        finish();

                    }
                });



                break;


            case R.id.aboutDoctor :
                SetNavUnChecked();
                //getSupportFragmentManager().popBackStack();
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.Exam_Frame,new AboutDoctor())
                        .addToBackStack(null)
                        .commit();


                break;
            case R.id.aboutProgrammer :
                SetNavUnChecked();
                //getSupportFragmentManager().popBackStack();
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.Exam_Frame,new AboutProgrammer())
                        .addToBackStack(null)
                        .commit();


                break;




        }
//        getSupportFragmentManager().popBackStack();   //finish
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {



        if (drawer.isDrawerOpen(Gravity.START)){
            drawer.closeDrawer(Gravity.START);
        }else {
           super.onBackPressed();
        }

    }


    private void hideAdminToolsFromUsers()
    {

        Menu nav_Menu      = navigation.getMenu();
        nav_Menu.findItem(R.id.more).setVisible(false);
        nav_Menu.findItem(R.id.Units).setVisible(false);
        nav_Menu.findItem(R.id.questions).setVisible(false);




    }
    @Override
    public void AdminTools() {

        Menu nav_Menu = navigation.getMenu();
        nav_Menu.findItem(R.id.more).setVisible(true);
        nav_Menu.findItem(R.id.Units).setVisible(false);
        nav_Menu.findItem(R.id.questions).setVisible(true);

        circleImageView.setBackgroundResource(R.drawable.ahmedsamy);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Exam_Frame,new MoreDetailsFromNav())
                .commitAllowingStateLoss();


        if (!AreAdmin){

            AreAdmin = true;
        }

        FirebaseMessaging.getInstance().subscribeToTopic("Admins");
        nav_Menu.findItem(R.id.exit).setEnabled(true);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void UserTools(FullRegisterForm fullRegisterForm) {

        this.fullRegisterForm = fullRegisterForm;
        String subScrib       = fullRegisterForm.getParentYear()+fullRegisterForm.getYear();

        FirebaseMessaging.getInstance().subscribeToTopic(subScrib);


        Menu nav_Menu = navigation.getMenu();
        nav_Menu.findItem(R.id.more).setVisible(false);
        nav_Menu.findItem(R.id.Units).setVisible(true);
        nav_Menu.findItem(R.id.questions).setVisible(false);
        if (AreAdmin){

            AreAdmin = false;
        }

        nav_Menu.findItem(R.id.exit).setEnabled(true);

        //  هنا حنجيب بعض البيانات من المستخدم عشان الفراج عاوزها عشسان يعرضله الوحدات الي موجوده  ركز جدااااااااااااااااااااااااا

        Unites_inDepartmentQ years_in_depsQ1 = new Unites_inDepartmentQ();
        Bundle b1 = new Bundle();
        b1.putString("depName",fullRegisterForm.getParentYear());
        b1.putString("yearName",fullRegisterForm.getYear());
        years_in_depsQ1.setArguments(b1);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Exam_Frame,years_in_depsQ1)
                .commitAllowingStateLoss();
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showRequestsFromStudent(String examID,String what,String nameExam,String depName , String yearName , String unitName) {


        RequestFromStudentToExamWhat requestFromStudentToExamWhat = new RequestFromStudentToExamWhat();
        switch (what){


            case "0":

                Bundle b = new Bundle();
                b.putString("depName",depName);
                b.putString("yearName", yearName);
                b.putString("unitName",unitName);
                b.putString("examid", examID);
                b.putString("name",nameExam);
                requestFromStudentToExamWhat.setArguments(b);
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.Exam_Frame,requestFromStudentToExamWhat,"K")
                        .addToBackStack(null)
                        .commit();
                break;

            case "1":

                progressBar.setVisibility(View.INVISIBLE);
                getSupportFragmentManager().popBackStack();
                RequestFromStudentToExamWhat requestFromStudentToExamWhat1 = new RequestFromStudentToExamWhat();
                Bundle b1 = new Bundle();
                b1.putString("depName",depName);
                b1.putString("yearName", yearName);
                b1.putString("unitName",unitName);
                b1.putString("examid", examID);
                b1.putString("name",nameExam);
                requestFromStudentToExamWhat1.setArguments(b1);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Exam_Frame,requestFromStudentToExamWhat1)
                        .addToBackStack(null)
                        .commit();


                com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog alertDialog
                        = new com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog(this, "لقد تم تنفيذ طلبك بنجاح.");
                alertDialog.show();

                break;

        }




        // حنظهر ام الطلبات بتاعه الامتحان بقااااااااااااااا


    }



    @Override
    public void setElevationZero() {

        if (toolbar!=null)
        toolbar.setElevation(6);

    }

    @Override
    public void showStudentDepartmentFragment() {


//        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Exam_Frame,new Students_Departments()).addToBackStack(null)
                .commit();
    }

    @Override
    public void showDialogToAddDeps() {

        addDepartmentDialog = new AddDepartmentDialog(this,R.style.PauseDialog,this);
        addDepartmentDialog.show();

    }

    @Override
    public void showSnackBar(String message) {


        Snackbar snackbar = Snackbar
                .make(snackBarr, message, Snackbar.LENGTH_LONG);


        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
        Typeface font = Typeface.createFromAsset(getAssets(),"atherfont.ttf");
        textView.setTypeface(font);
        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_LTR);
        snackbar.show();


    }












    @Override
    public void storeDeps(String parent, String child) {

        // storing depsStudentFrom Dialog...  //

        //  اولا حنشوف هذا الصف موجود الاول ولا لا لو موجود مش حنضيفه لو مش موجود حنضيفه
        controlpanelPresnter.tellModeltoCheckIfYearExisitOrNot(parent,child);





    }

    @Override
    public void showfragUnites(String depName, String yearName,int what) {


        //  ركز هنااااااااااااااااا متنساس لان هنا حنرفرش الفراج حالا

        switch (what){


            case 0:

                Unites_inDepartmentQ years_in_depsQ = new Unites_inDepartmentQ();
                Bundle b = new Bundle();
                b.putString("depName",depName);
                b.putString("yearName",yearName);
                years_in_depsQ.setArguments(b);
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.Exam_Frame,years_in_depsQ).addToBackStack(null)
                        .commit();

                break;

            case 1:
                getSupportFragmentManager().popBackStack();
                Unites_inDepartmentQ years_in_depsQ1 = new Unites_inDepartmentQ();
                Bundle b1 = new Bundle();
                b1.putString("depName",depName);
                b1.putString("yearName",yearName);
                years_in_depsQ1.setArguments(b1);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Exam_Frame,years_in_depsQ1).addToBackStack(null)
                        .commit();


                Snackbar snackbar = Snackbar
                        .make(snackBarr, "تم إضافه الوحدة بنجاح.", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
                Typeface font = Typeface.createFromAsset(getAssets(),"atherfont.ttf");
                textView.setTypeface(font);
                ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
                snackbar.show();

                break;
            case 2:
                getSupportFragmentManager().popBackStack();
                Unites_inDepartmentQ years_in_depsQ2 = new Unites_inDepartmentQ();
                Bundle b2 = new Bundle();
                b2.putString("depName",depName);
                b2.putString("yearName",yearName);
                years_in_depsQ2.setArguments(b2);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Exam_Frame,years_in_depsQ2).addToBackStack(null)
                        .commit();


                Snackbar snackbar2 = Snackbar
                        .make(snackBarr, "لقد تم حذف الوحدة بنجاح.", Snackbar.LENGTH_LONG);
                View sbView2 = snackbar2.getView();
                TextView textView2 = (TextView) sbView2.findViewById(android.support.design.R.id.snackbar_text);
                textView2.setTextColor(Color.YELLOW);
                textView2.setTextDirection(View.LAYOUT_DIRECTION_LTR);
                Typeface font2 = Typeface.createFromAsset(getAssets(),"atherfont.ttf");
                textView2.setTypeface(font2);
                ViewCompat.setLayoutDirection(snackbar2.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
                snackbar2.show();

                break;

        }





    }

    @Override
    public void showYearsDetails(String depsYear,String fragName) {



        if (fragName.equals("Students_Departments")){

            getSupportFragmentManager().popBackStack();

            Years_In_Deps years_in_deps = new Years_In_Deps();
            Bundle b = new Bundle();
            b.putString("depsYear",depsYear);
            years_in_deps.setArguments(b);
            //  حنجيب كل التفاصيل بتاعه السنه من حيث بقا الطلاب والامتحانات وكل حاجه خاصه بيها توضيح للدكتور فقط ولا تظهر هذه الاشياء للطالب
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.Exam_Frame,years_in_deps).addToBackStack(null)
                    .commit();

        }


        if (fragName.equals("Department_Questions")){

            getSupportFragmentManager().popBackStack();
            YeareInDepsQuestions years_in_depsQ = new YeareInDepsQuestions();
            Bundle b = new Bundle();
            b.putString("depsYear",depsYear);
            years_in_depsQ.setArguments(b);
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.Exam_Frame,years_in_depsQ).addToBackStack(null)
                    .commit();


        }

        //   هنا نفس الحاجه الي حتظهر بس مش عارفين مين الي حيظهرها فحنشوف الاول


    }

    @Override
    public void DoneYearAddesSussessfully() {

        if (addDepartmentDialog!=null){

            addDepartmentDialog.returnButton().setEnabled(true);
            addDepartmentDialog.getProgress().setVisibility(View.INVISIBLE);
            addDepartmentDialog.dismiss();

        }
        com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog alertDialog
                = new com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog(this, "تم إضافه العام الدراسي بنجاح.");
        alertDialog.show();


    }

    @Override
    public void NotDoneThereAreProblemWithConnection() {

        if (addDepartmentDialog!=null){

            addDepartmentDialog.returnButton().setEnabled(true);
            addDepartmentDialog.getProgress().setVisibility(View.INVISIBLE);
            addDepartmentDialog.dismiss();

        }
        com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog alertDialog
                = new com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog(this, "لقد حدثت مشكله أثناء الأتصال.");
        alertDialog.show();


    }

    @Override
    public void yearAleradyExisitNoAdding(String parent, String childYear) {

        if (addDepartmentDialog!=null){

            addDepartmentDialog.returnButton().setEnabled(true);
            addDepartmentDialog.getProgress().setVisibility(View.INVISIBLE);
            addDepartmentDialog.dismiss();

        }
        //  اظهر دايلوج للمستخدم وقوله هذه السنه موجوده بالفعل مينفعش تضيفها
        com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog alertDialog
                = new com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog(this, "هذه الفرقه الدراسية متواجده بالفعل لا يمكنك إضافتها مرة أخري.");
        alertDialog.show();

    }

    @Override
    public void yearNotExistAddThisYearNow(String parent, String childYear) {

        // هنا حنضيف ام السنه بقااا لانها مش موجوده

        controlpanelPresnter.tellModeltoAddYearQuickly(parent,childYear);





    }

    @Override
    public void yearP() {


        if (addDepartmentDialog!=null){

            addDepartmentDialog.returnButton().setEnabled(true);
            addDepartmentDialog.getProgress().setVisibility(View.INVISIBLE);
            addDepartmentDialog.dismiss();

        }

        Snackbar snackbar = Snackbar
                .make(snackBarr, "لقد حدث مشكله في الاتصال.", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
        Typeface font = Typeface.createFromAsset(getAssets(),"atherfont.ttf");
        textView.setTypeface(font);
        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_LTR);
        snackbar.show();


    }








    @Override
    public void notificationMessages(String message,ProgressBar p1 , ProgressBar p2,String depFromAdmin , String yearNameFromAdmin) {

        // send notification to allstudent //
        sendnotificationtoallUsers(message,p1,p2,depFromAdmin,yearNameFromAdmin);


    }
















    @Override
    public void showingresults() {


        Fragment fragment = getSupportFragmentManager().findFragmentByTag("K");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Exam_Frame,fragment,"K")
                .addToBackStack(null)
                .commit();



    }

    @Override
    public void tokenSussessfullystored() {
        //Toast.makeText(this, "التوكن اتخزن تمااااااااااام", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void problemwithtoken() {

       // Toast.makeText(this, "التوكن فيه مشكله مش عارف", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void tokenisExisitinFirebase() {

    }

    @Override
    public void tokennotExisitinFirebase() {



    }

    @Override
    public void userAreDeletedSussess() {
        animatedDialog.Close_Dialog();
        startActivity(new Intent(ControlPanel.this,MainActivity.class));
        finish();
    }

    @Override
    public void problemwithDeleteUser() {
        animatedDialog.Close_Dialog();
        Toast.makeText(this, "لقد حدثت مشكله اثناء الحذف.", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void SetUsername(String nameStudent) {
        UserName.setText( "Welcome,  "  + nameStudent );
    }
    public static void SetNavChecked(int position){

        navigation.getMenu().getItem(position).setChecked(true);

    }

    public static void SetNavUnChecked(){

        for (int position = 0 ; position < navigation.getMenu().size(); position++  ) {
            navigation.getMenu().getItem(position).setChecked(false);
        }

    }




    public void sendnotificationtoallUsers(String s, final ProgressBar p1, final ProgressBar p2,String depName , String yearName) {


        String topicName  = depName+yearName;

        JSONObject obj = null;
        JSONObject dataobjData = null;


        try {

            obj = new JSONObject();

            dataobjData = new JSONObject();
            dataobjData.put("image", "0");
            dataobjData.put("message",s);

            obj.put("to", "/topics/"+topicName+"");
            obj.put("data", dataobjData);

            Log.d("MYOBJs", obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, "https://fcm.googleapis.com/fcm/send", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("SUCCESS", response + "");
                        NotificationDialog.edTextNot.setText("");
                        p1.setVisibility(View.GONE);
                        p2.setVisibility(View.GONE);
                        NotificationDialog.sendfeedback.setEnabled(true);
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        Toast.makeText(ControlPanel.this, "لقد تم إرسال الإشعار بنجاح.", Toast.LENGTH_SHORT).show();
                        if (notificationDialog!=null){

                            notificationDialog.dismiss();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                            Toast.makeText(ControlPanel.this, "تاكد من الإتصال بالإنترنت", Toast.LENGTH_SHORT).show();
                            NotificationDialog.sendfeedback.setEnabled(true);
                            p1.setVisibility(View.GONE);
                            p2.setVisibility(View.GONE);


                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "key=" + "AAAAlXCKxUE:APA91bFGSM9okl_Va_Q5wGeK6LW3KAZNoFeme6l95iRGz5z-llVh1ZLXZ-yH0q5Ua3PmLPghxAirqgBujN-FLR5-OB-gKkGkHlOdW8wO3CkEAZ0x5_-h-SvKyAw_8eKlYDvNA4EO5kvM");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };



            RequestQueue requestQueue = Volley.newRequestQueue(this);
            int socketTimeout = 1000 * 60;// 60 seconds
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsObjRequest.setRetryPolicy(policy);
            requestQueue.add(jsObjRequest);


    }


    @Override
    public void showunitBankQuestion(String depName, String yearName, String unitName) {



        //  حنفتح فراج بقااا ويكون عباره عن الامتحانات والاسئله والطلبه وكل شي متعلق بالوحده اغلدراسيه

        Exams_Questions_Student_RequestsForUnit exams_questions_student_requestsForUnit = new Exams_Questions_Student_RequestsForUnit();
        Bundle b                                                                        = new Bundle();
        b.putString( "depName",depName);
        b.putString( "yearName",yearName);
        b.putString( "unitName",unitName);
        exams_questions_student_requestsForUnit.setArguments(b);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Exam_Frame,exams_questions_student_requestsForUnit)
                .addToBackStack(null)
                .commit();


    }
    @Override
    public void showQforUnitiRefere(String depName, String yearName, String unitName) {


        Question_Bank_Frag question_bank_frag = new Question_Bank_Frag();

        Bundle b = new Bundle();
        b.putString("depName",depName);
        b.putString("yearName",yearName);
        b.putString("unitName",unitName);
        question_bank_frag.setArguments(b);
         getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.Exam_Frame,question_bank_frag)
                        .addToBackStack(null)
                        .commit();
    }




    @Override
    public void removedsussesswewillupdateQuestionBankFragment(String depName, String yearName, String unitName,String what) {



        switch (what){


            case "":

                Question_Bank_Frag question_bank_frag = new Question_Bank_Frag();
                Bundle b = new Bundle();
                b.putString("depName",depName);
                b.putString("yearName",yearName);
                b.putString("unitName",unitName);
                question_bank_frag.setArguments(b);
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Exam_Frame,question_bank_frag)
                        .addToBackStack(null)
                        .commit();

                break;

            case "allQRemoved":



                Snackbar snackbar = Snackbar
                        .make(snackBarr, "All Question are Deleted.", Snackbar.LENGTH_LONG);


                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                textView.setTextDirection(View.LAYOUT_DIRECTION_LTR);
                Typeface font = Typeface.createFromAsset(getAssets(),"atherfont.ttf");
                textView.setTypeface(font);
                ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_LTR);
                snackbar.show();






                Question_Bank_Frag question_bank_frag1 = new Question_Bank_Frag();
                Bundle b1 = new Bundle();
                b1.putString("depName",depName);
                b1.putString("yearName",yearName);
                b1.putString("unitName",unitName);
                question_bank_frag1.setArguments(b1);
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Exam_Frame,question_bank_frag1)
                        .addToBackStack(null)
                        .commit();




                break;


            case "NoRemoved":


                Snackbar snackbar1 = Snackbar
                        .make(snackBarr, "Error ocures during deleting.", Snackbar.LENGTH_LONG);


                View sbView1 = snackbar1.getView();
                TextView textView1 = (TextView) sbView1.findViewById(android.support.design.R.id.snackbar_text);
                textView1.setTextColor(Color.YELLOW);
                textView1.setTextDirection(View.LAYOUT_DIRECTION_LTR);
                Typeface font1 = Typeface.createFromAsset(getAssets(),"atherfont.ttf");
                textView1.setTypeface(font1);
                ViewCompat.setLayoutDirection(snackbar1.getView(),ViewCompat.LAYOUT_DIRECTION_LTR);
                snackbar1.show();


                Question_Bank_Frag question_bank_frag2 = new Question_Bank_Frag();
                Bundle b2 = new Bundle();
                b2.putString("depName",depName);
                b2.putString("yearName",yearName);
                b2.putString("unitName",unitName);
                question_bank_frag2.setArguments(b2);
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Exam_Frame,question_bank_frag2)
                        .addToBackStack(null)
                        .commit();





                break;

        }



    }





//   هذا الجزء خاااص بال examList



    @Override
    public void showExamListForUnit(String depName, String yearname, String unitName) {


        ExamList examList = new ExamList();
        Bundle b = new Bundle();
        b.putString("depName" ,depName);
        b.putString("yearname",yearname);
        b.putString("unitName",unitName);
        examList.setArguments(b);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Exam_Frame,examList).addToBackStack(null)
                .commit();




    }

    @Override
    public void showAddExamFromExamListWhenClickonFloatActionButton(String depName, String yearname, String unitName) {

        //  هنا حنعرض الفراج الي بيضيف الامتحانات  ....

        addExam addExam = new addExam();
        Bundle b        = new Bundle();
        b.putString("depName2",depName);
        b.putString("yearname2",yearname);
        b.putString("unitName2",unitName);
        addExam.setArguments(b);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Exam_Frame,addExam)
                .addToBackStack(null)
                .commit();



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }





    //  هذا الجزء خاص بال Myresult


    @Override
    public void showMyresultFragment(String depName, String yearName, String unitName) {

        MyResults myResults = new MyResults();
        Bundle b            = new Bundle();
        b.putString("mdeNameresukt" ,depName);
        b.putString("yearNameresult",yearName);
        b.putString("unitNameresult",unitName);
        myResults.setArguments(b);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Exam_Frame,myResults)
                .addToBackStack(null)
                .commit();


    }


    @Override
    public void enableDisableDrawer(int mode) {
        if (drawer != null) {
            drawer.setDrawerLockMode(mode);

            if (open_nav.isShown())
            open_nav.setVisibility(View.GONE);
        }
    }

    @Override
    public void enableDrawer(int mode) {

        if (drawer!=null){
            drawer.setDrawerLockMode(mode);
        }

        if (!open_nav.isShown())
            open_nav.setVisibility(View.VISIBLE);



    }






  //  هذا الجزء خاااااااااااااااص بالنتائج بتاعه الطلاب



    @Override
    public void showResultOfStudentInThisUnitFragment(String depName, String yearName, String unitName) {

        ExamsResults examsResults = new ExamsResults();
        Bundle b                  = new Bundle();
        b.putString("depNameExamResult" ,depName);
        b.putString("yearNameExamResult",yearName);
        b.putString("unitNameExamResult",unitName);
        examsResults.setArguments(b);

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Exam_Frame,examsResults)
                .addToBackStack(null)
                .commit();



    }





//  خاص بالطلبات بتاعه الطلااااب


    @Override
    public void showRequestStudentFragment(String depName, String yearName, String unitName) {

        PermissionsFromStudent permissions = new PermissionsFromStudent();
        Bundle b                  = new Bundle();
        b.putString("depNameExamResult" ,depName);
        b.putString("yearNameExamResult",yearName);
        b.putString("unitNameExamResult",unitName);
        permissions.setArguments(b);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Exam_Frame,permissions).addToBackStack(null)
                .commit();



    }



    @Override
    public void showStudentMangmentFragmenttt(String depName, String yearName) {


        StudentManagement myStudnet = new StudentManagement();
        Bundle b1 = new Bundle();
        b1.putString("what","myStudents");
        b1.putString("depName",depName);
        b1.putString("yearName", yearName);
        myStudnet.setArguments(b1);
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Exam_Frame,myStudnet)
                .addToBackStack(null)
                .commit();

//
//        studentDialog = new StudentDialog(ControlPanel.this,R.style.PauseDialog,this,depName,yearName);
//        studentDialog.show();


    }




    @Override
    public void showDialogNotification() {



        notificationDialog = new NotificationDialog(this,R.style.PauseDialog,this);
        notificationDialog.show();



    }




}
