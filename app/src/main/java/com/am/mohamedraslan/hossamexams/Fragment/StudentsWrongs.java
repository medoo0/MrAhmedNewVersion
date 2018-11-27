package com.am.mohamedraslan.hossamexams.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.mohamedraslan.hossamexams.Adapter.StudentsWrongs_Rec_Adapter;
import com.am.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.am.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.am.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.am.mohamedraslan.hossamexams.JsonModel.WorngQestion;
import com.am.mohamedraslan.hossamexams.R;
import com.am.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class StudentsWrongs extends Fragment {
    @BindView(R.id.txDegree)
    TextView txDegree;

    @BindView(R.id.txFinalDegree)
    TextView txFinalDegree;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.hidetext)
    TextView hidetext;

    @BindView(R.id.circleimage)
    CircleImageView circleimage;

    @BindView(R.id.txName)
    TextView txName;

    @BindView(R.id.Wrongs_rec)
    RecyclerView Wrongs_rec;

    @BindView(R.id.delete)
    ImageView delete ;
    String val = "";
    ArrayList<WorngQestion> wrong ;
    String examID , UserUid;
    AnimatedDialog dialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext())
                    .inflateTransition(android.R.transition.move));
        }

        if (getArguments()!=null){

           val = getArguments().getString("me","");


        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        ControlPanel.Title.setText(R.string.studentsWrongs);

        // Inflate the layout for this fragment
         View v =inflater.inflate(R.layout.fragment_students_wrongs, container, false);
        ButterKnife.bind(this,v);
        wrong = new ArrayList<>();
        dialog = new AnimatedDialog(getActivity());
        if (getArguments() != null && val.equals("") ) {
            String name = getArguments().getString("Name");
            String total= getArguments().getString("Total");
            String FinalDegree= getArguments().getString("FinalDegree");
            examID = getArguments().getString("examID");
            UserUid = getArguments().getString("UserUid");
            int source = getArguments().getInt("Image");
            circleimage.setBackgroundResource(source);
            wrong = getArguments().getParcelableArrayList("WrongQuestions");
            txDegree.setText(total);
            txFinalDegree.setText(FinalDegree);
            txName.setText(name);

            if (wrong!=null){


                StudentsWrongs_Rec_Adapter adapter = new StudentsWrongs_Rec_Adapter(wrong);
                Wrongs_rec.setLayoutManager(new LinearLayoutManager(getActivity()));
                Wrongs_rec.setAdapter(adapter);


            }else {

                String nam = getArguments().getString("Name");
                String tota= getArguments().getString("Total");
                String FinalDegre= getArguments().getString("FinalDegree");
                examID = getArguments().getString("examID");
                UserUid = getArguments().getString("UserUid");
                int soure = getArguments().getInt("Image");
                circleimage.setBackgroundResource(soure);
                txDegree.setText(tota);
                txFinalDegree.setText(FinalDegre);
                txName.setText(nam);
                hidetext.setVisibility(View.VISIBLE);

            }

        }

        else if ( getArguments() !=null &&val.equals("1")){
            txName.setVisibility(View.GONE);
            title.setText("My Mistakes");
            delete.setVisibility(View.GONE);
            circleimage.setVisibility(View.GONE);
            wrong             = getArguments().getParcelableArrayList("WrongQuestions");
            String total      = getArguments().getString("Total");
            String FinalDegree= getArguments().getString("FinalDegree");
            txFinalDegree.setText(FinalDegree);
            txDegree.setText(total);
            StudentsWrongs_Rec_Adapter adapter = new StudentsWrongs_Rec_Adapter(wrong);
            Wrongs_rec.setLayoutManager(new LinearLayoutManager(getActivity()));
            Wrongs_rec.setAdapter(adapter);

        }



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog(getActivity(),"تحذير" , "يُرجى العلم أنه في حالة حذف الطالب، سوف يتمكن الطالب من دخول الاختبار مرة أخرى وذلك في حالة وجود الاختبار في قائمة الاختبارات. متأكد؟");
                alertDialog.show();
                alertDialog.btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Delete();
                        dialog.ShowDialog();
                        alertDialog.dismiss();
                    }
                });

            }
        });

        return v;
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

    void  Delete() {
        if (getActivity() != null) {
            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.RESULT.getRef())
                    .child(examID + UserUid);
            reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.STARTEDEXAM.getRef())
                            .child(examID).child(UserUid);
                    reference1.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            dialog.Close_Dialog();
                            getActivity().onBackPressed();

                        }
                    });
                }
            });

        }

    }

}
