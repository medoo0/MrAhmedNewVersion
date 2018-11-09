package com.example.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.example.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.example.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.example.mohamedraslan.hossamexams.Fragment.MyResults;
import com.example.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.example.mohamedraslan.hossamexams.Permissions.Call_permission;
import com.example.mohamedraslan.hossamexams.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class StudentManagementAdapter extends RecyclerView.Adapter<StudentManagementAdapter.ViewHolder> {
    List<FullRegisterForm> items ;
    List<FullRegisterForm> listnew;
    FirebaseAuth auth;
    int photosCounter = 0 ;
    Context context;
    FragmentManager fragmentManager;
    public  StudentManagementAdapter (Context context, List<FullRegisterForm> items  , FragmentManager fragmentManager){

        this.items = items ;
        this.context = context;
        auth = FirebaseAuth.getInstance();
        this.fragmentManager = fragmentManager ;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {



            holder.Name.setText(items.get(position).getNameStudent());
            holder.Contry.setText(items.get(position).getCountry());
            holder.phone.setText(items.get(position).getPhone());
            holder.email.setText(items.get(position).getEmail());

                //photos changer .
                if (photosCounter == 0 ) {
                    holder.circleImageView.setBackgroundResource(R.drawable.ic_student_1);
                    photosCounter ++ ;
                }
                else if (photosCounter == 1) {
                    holder.circleImageView.setBackgroundResource(R.drawable.ic_student_2);
                    photosCounter ++ ;
                }
                else if( photosCounter == 2 )
                {
                    holder.circleImageView.setBackgroundResource(R.drawable.ic_student_3);
                    photosCounter ++ ;
                }
                else {
                    holder.circleImageView.setBackgroundResource(R.drawable.ic_student_4);
                    photosCounter = 0;
                }


            //animation
            holder.Press_on_CardView.setScaleX(.9f);
            holder.Press_on_CardView.setScaleY(.9f);
            holder.Press_on_CardView.animate().scaleX(1f).scaleY(1f).setDuration(500);

            // When press on card it should Display Card Down layout .
            holder.Press_on_CardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(holder.CardDownlayout.isShown()){
                        holder.dropDown.setImageResource(R.drawable.ic_dropdown);
                        holder.CardDownlayout.setVisibility(View.GONE);

                    }
                    else {
                        holder.dropDown.setImageResource(R.drawable.ic_dropup);
                        holder.CardDownlayout.setVisibility(View.VISIBLE);
                        holder.CardDownlayout.setScaleX(0.0f);
                        holder.CardDownlayout.setScaleY(0.0f);
                        holder.CardDownlayout.animate().scaleX(1f).scaleY(1f).setDuration(300);
                    }

                }
            });

            //Call the Number
            holder.phone_Linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Call_permission permission = new Call_permission(context);

                    if (!permission.checkPermissionForCamera()) {
                        permission.requestPermissionForCamera();
                    } else {

                        permission.openCaller(context,items.get(position).getPhone()); // Caller
                    }



                }
            });

            //rating Users .
            holder.MYResult_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    Bundle bundle = new Bundle();
                    bundle.putString("uid", items.get(position).getuID() );
                    // set MyFragment Arguments
                    MyResults MyResults = new MyResults();
                    MyResults.setArguments(bundle);

                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                            .replace(R.id.Exam_Frame,MyResults).addToBackStack(null)
                            .commit();

                }
            });


            // to avoid  Ban of Admin Ban himself .
            if(!auth.getCurrentUser().getUid().equals(items.get(position).getuID())) {

                //Banned User .
                Check_if_user_banned(items.get(position).getuID(), holder);


            }






    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public   class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txNameStudent)
        TextView Name ;

        @BindView(R.id.txcountry)
        TextView Contry;

        @BindView(R.id.image_dropdown)
        ImageView dropDown;

        @BindView(R.id.circleimage)
        CircleImageView circleImageView;

        @BindView(R.id.txphone)
        TextView phone;

        @BindView(R.id.txemail)
        TextView email ;

        @BindView(R.id.phone_Linear)
        LinearLayout phone_Linear;

        @BindView(R.id.email_linear)
        LinearLayout email_linear;

        @BindView(R.id.ban_linear)
        LinearLayout ban_linear;

        @BindView(R.id.MYResult_linear)
        LinearLayout MYResult_linear;

        @BindView(R.id.Press_on_CardView)
        CardView Press_on_CardView;

        @BindView(R.id.Details_layout)
        RelativeLayout CardDownlayout;

        @BindView(R.id.txStudentban)
        TextView txStudentban;

        @BindView(R.id.banImag)
        ImageView banImag ;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    void Banned_User(final String BannedUser_ID){
        //Show Alert ask user to ban .
        final AnimatedDialog dialog = new AnimatedDialog(context);
        final AlertDialog alert  = new AlertDialog(context,
                context.getString(R.string.title),
                context.getString(R.string.ask_banned));
        alert.show();
        alert.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog.ShowDialog();

                DatabaseReference reference = FirebaseDatabase.getInstance()
                        .getReference(DataBase_Refrences.BLOCKUSER.getRef())
                        .child(BannedUser_ID);
                reference.setValue("Yes").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //successful
                        alert.dismiss();
                        dialog.Close_Dialog();
                        AlertDialog alert2  = new AlertDialog(context,context.getString(R.string.successful_banned));
                        alert2.show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Failure
                        alert.dismiss();
                        dialog.Close_Dialog();
                        AlertDialog alert2  = new AlertDialog(context,e.toString());
                        alert2.show();

                    }
                });

            }
        });
    }

    void Check_if_user_banned(final String Uid, final ViewHolder holder){


        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(DataBase_Refrences.BLOCKUSER.getRef())
                .child(Uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){


                    holder.txStudentban.setText(R.string.removeBan);
                    holder.banImag.setImageResource(R.drawable.reverse_user);
                    //remove user from ban list in firebase .
                    holder.ban_linear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            Remove_Banned_User(Uid);


                        }
                    });

                }
                else {

                    holder.txStudentban.setText(R.string.banned);
                    holder.banImag.setImageResource(R.drawable.ic_ban);
                    holder.ban_linear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //First put this Line in Firebase Database Rule ".write": "auth != null && !root.child('Blocked_User').hasChild(auth.uid)"
                            //Ban user

                            Banned_User(Uid);


                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void Remove_Banned_User(String Uid) {

        final DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(DataBase_Refrences.BLOCKUSER.getRef())
                .child(Uid);


        //Show Alert ask user to remove ban  .
        final AnimatedDialog dialog = new AnimatedDialog(context);
        final AlertDialog alert  = new AlertDialog(context,
                context.getString(R.string.title),
                context.getString(R.string.RemoveAsk));
        alert.show();
        alert.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog.ShowDialog();


                reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //successful
                        alert.dismiss();
                        dialog.Close_Dialog();
                        AlertDialog alert2  = new AlertDialog(context,context.getString(R.string.RemoveDone));
                        alert2.show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Failure
                        alert.dismiss();
                        dialog.Close_Dialog();
                        AlertDialog alert2  = new AlertDialog(context
                                ,e.toString());
                        alert2.show();

                    }
                });

            }
        });


    }


    public Filter getFilter() {




        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<FullRegisterForm> FilteredArrList = new ArrayList<>();
                if (listnew == null) {

                    listnew = new ArrayList<>(items); // saves the original data in mOriginalValues

                }

                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = listnew.size();
                    results.values = listnew;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < listnew.size(); i++) {
                        String data = listnew.get(i).getNameStudent();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(listnew.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                items = (ArrayList<FullRegisterForm>) results.values; // has the filtered values
                notifyDataSetChanged();

            }
        };
        return filter;
    }



}
