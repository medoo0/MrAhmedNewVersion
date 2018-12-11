package com.developer.mohamedraslan.hossamexams.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
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
import com.developer.mohamedraslan.hossamexams.Contracts.RequestFromStudentToExamWhatContract;
import com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.developer.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.PermissionUserEntering;
import com.developer.mohamedraslan.hossamexams.R;
import com.developer.mohamedraslan.hossamexams.Views.ControlPanel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterExamsStudents extends RecyclerView.Adapter<ExamStudenstHolder> {

    List<PermissionUserEntering>list,listnew;
    Context context;
    int photosCounter = 0;
    String examID;
    String depName , yearName , unitName;
    RequestFromStudentToExamWhatContract.MainView mainView;
    String examName;



    public AdapterExamsStudents(List<PermissionUserEntering> list, Context context, String examID, RequestFromStudentToExamWhatContract.MainView mainView,
                                String examName,String depName,String yearName , String unitName) {
        this.list    = list;
        this.examID  = examID;
        this.context = context;
        this.mainView = mainView;
        this.depName = depName;
        this.yearName= yearName;
        this.unitName = unitName;
        this.examName = examName;

    }

    @NonNull
    @Override
    public ExamStudenstHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentthatrequestexamagainrec,parent,false);
        return new ExamStudenstHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamStudenstHolder holder, final int position) {

        holder.txNameStudentt.setText(list.get(position).getStudentNanme());
        mainView.numberStu(getItemCount());


        if (photosCounter == 0 ) {
            holder.circleimageee.setBackgroundResource(R.drawable.ic_student_1);
            photosCounter ++ ;
        }
        else if (photosCounter == 1) {
            holder.circleimageee.setBackgroundResource(R.drawable.ic_student_2);
            photosCounter ++ ;
        }
        else if( photosCounter == 2 )
        {
            holder.circleimageee.setBackgroundResource(R.drawable.ic_student_3);
            photosCounter ++ ;
        }
        else {
            holder.circleimageee.setBackgroundResource(R.drawable.ic_student_4);
            photosCounter = 0;
        }


        holder.Press_on_CardViewss.setScaleX(.9f);
        holder.Press_on_CardViewss.setScaleY(.9f);
        holder.Press_on_CardViewss.animate().scaleX(1f).scaleY(1f).setDuration(500);

        holder.agreeing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ControlPanel.progressBar.setVisibility(View.VISIBLE);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference(DataBase_Refrences.Permissions.getRef());
                reference.child(depName).child(yearName).child(unitName).child(examID).child(list.get(position).getuID()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            FirebaseDatabase firebaseDatabase2 = FirebaseDatabase.getInstance();
                            DatabaseReference reference1       = firebaseDatabase2.getReference("ExamStarted");
                            reference1.child(depName).child(yearName).child(unitName).child(examID).child(list.get(position).getuID()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){

                                       gettokentoUserthatMraccepthereRequest(list.get(position).getuID());
                                       mainView.refreshFragment(examName);


                                       // send notification to user .....





                                    }

                                }
                            });


                        }

                    }
                });
                //  حنخليه يتنيل يعيد تاااااااااااااني
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public Filter getFilter() {




        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<PermissionUserEntering> FilteredArrList = new ArrayList<>();
                if (listnew == null) {

                    listnew = new ArrayList<>(list); // saves the original data in mOriginalValues

                }

                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = listnew.size();
                    results.values = listnew;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < listnew.size(); i++) {
                        String data = listnew.get(i).getStudentNanme();
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

                list = (ArrayList<PermissionUserEntering>) results.values;
                if (results.count<1){

                    mainView.numberStu(0);
                }
                // has the filtered values
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public void removingItemsFromAdapter(int position, List<PermissionUserEntering> list) {

        if (position != RecyclerView.NO_POSITION) {
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
        }
    }


    public void allowAllusers(final String examID, final AnimatedDialog animatedDialog){





        final AlertDialog alertDialog = new AlertDialog(context,"تحذير"," يرجي العلم أن جميع المطالبين باعادة الاختبار سوف يتمكنون من الدخول الي الإختبار مجددا. متأكد ؟ ");
        alertDialog.show();
        alertDialog.setCancelable(false);
        alertDialog.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatedDialog.Close_Dialog();
                alertDialog.dismiss();
            }
        });
        alertDialog.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                FirebaseDatabase firebaseDatabasea = FirebaseDatabase.getInstance();
                DatabaseReference reference        = firebaseDatabasea.getReference(DataBase_Refrences.Permissions.getRef());
                reference.child(depName).child(yearName).child(unitName).child(examID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            FirebaseDatabase firebaseDatabasea = FirebaseDatabase.getInstance();
                            DatabaseReference reference        = firebaseDatabasea.getReference("ExamStarted");
                            reference.child(depName).child(yearName).child(unitName).child(examID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        FirebaseDatabase firebaseDatabasea = FirebaseDatabase.getInstance();
                                        DatabaseReference reference        = firebaseDatabasea.getReference("PermissionRefrence");
                                        reference.child(depName).child(yearName).child(unitName).child(examID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()){


                                                    animatedDialog.Close_Dialog();
                                                    sendtoStudentThaAcceptFromMrAhmed(context,depName,yearName);


                                                }

                                            }
                                        });

                                    }
                                }
                            });

                        }

                    }
                });
            }
        });


    }
    public void clearData() {
        list.clear(); //clear list
        notifyDataSetChanged(); //let your adapter know about the changes and reload view.
    }




    public void sendtoStudentThaAcceptFromMrAhmed(Context mcontext,String depName , String yearname) {


        String toTopic = depName+yearname;

        JSONObject obj = null;
        JSONObject dataobjData = null;

        try {

            obj = new JSONObject();

            dataobjData = new JSONObject();
            dataobjData.put("image", "0");
            dataobjData.put("message","  لقد وافق Mr.Ahmed علي جميع  طلبات إعاده اختبار  "  +  examName  );

            obj.put("to", "/topics/'"+toTopic+"'");
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
                        mainView.refreshFragment(examName);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                            mainView.refreshFragment(examName);
                        Toast.makeText(context, "تاكد من الإتصال بالإنترنت", Toast.LENGTH_SHORT).show();

//                        String response= null;
//                        try {
//                            response = new String(error.networkResponse.data,"UTF-8");
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                        Log.e("Error Response",response);
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

        RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
        int socketTimeout = 1000 * 60;// 60 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        requestQueue.add(jsObjRequest);


    }

    public void sendPost(Context mcontext,String tokenDevice) {


        JSONObject obj = null;
        JSONObject dataobjData = null;

        try {

            obj = new JSONObject();

            dataobjData = new JSONObject();
            dataobjData.put("image", "0");
            dataobjData.put("message","  لقد وافق Mr.Ahmed علي إعادة إختبار "  + examName );

            obj.put("to", tokenDevice);
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
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                        if (error.toString().equals("com.android.volley.NoConnectionError: java.net.UnknownHostException: Unable to resolve host fcm.googleapis.com: No address associated with hostname")){

                            Toast.makeText(context, "تاكد من الإتصال بالإنترنت", Toast.LENGTH_SHORT).show();


                        }
//                        String response= null;
//                        try {
//                            response = new String(error.networkResponse.data,"UTF-8");
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                        Log.e("Error Response",response);
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

            RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
            int socketTimeout = 1000 * 60;// 60 seconds
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsObjRequest.setRetryPolicy(policy);
            requestQueue.add(jsObjRequest);


    }


    public void gettokentoUserthatMraccepthereRequest(String uID){


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Tokens_Device");
        reference.child(uID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){


                    String tokens = (String) dataSnapshot.getValue();
                    sendPost(context,tokens);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
