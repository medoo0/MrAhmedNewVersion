package com.example.mohamedraslan.hossamexams.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.example.mohamedraslan.hossamexams.Adapter.addExamTouchHelper;
import com.example.mohamedraslan.hossamexams.Adapter.addExam_Rec_Adapter;
import com.example.mohamedraslan.hossamexams.Contracts.addExamContract;
import com.example.mohamedraslan.hossamexams.Dialog.AnimatedDialog;
import com.example.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.example.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.example.mohamedraslan.hossamexams.JsonModel.Zone;
import com.example.mohamedraslan.hossamexams.MainPresnter.addExamPresenter;
import com.example.mohamedraslan.hossamexams.R;
import com.example.mohamedraslan.hossamexams.Views.ControlPanel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class addExam extends Fragment implements addExamContract.view  , addExamTouchHelper.RecyclerItemTouchHelperListener {

    @BindView(R.id.chosen_Qestions_Rec)
    RecyclerView recyclerView;

    @BindView(R.id.questionssize)
    TextView Questions_size;


    static EditText et_second;


    static EditText et_minute;


    static EditText et_hour;

    @BindView(R.id.et_degree)
    EditText et_degree;

    @BindView(R.id.et_random_number_question)
    EditText et_random_number_question;

    @BindView(R.id.Final_Degree)
    TextView txFinal_Degree;

    @BindView(R.id.ExamName)
    EditText ExamName ;

    @BindView(R.id.deleteAll)
    Button Btn_DeleteList;

    @BindView(R.id.Btn_addExam)
    Button Btn_addExam;


    String final_degree;
    int hour, minute, second;
    addExamContract.presenter presenter;
    addExam_Rec_Adapter adapter;
    private List<Questions_Form> Questions;
    AnimatedDialog dialog ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ControlPanel.Title.setText(R.string.addExam);
        ControlPanel.SetNavChecked(2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_exam, container, false);
        et_hour   = v.findViewById(R.id.et_hour);
        et_minute = v.findViewById(R.id.et_minute);
        et_second = v.findViewById(R.id.et_second);
        Questions = new ArrayList<>();
        ButterKnife.bind(this, v);
        //display dialog .
        dialog = new AnimatedDialog(getActivity());
        dialog.ShowDialog();

        et_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment dFragment = new TimePickerShow();
                // Show the date picker dialog fragment
                assert getFragmentManager() != null;
                dFragment.show(getFragmentManager(), "Time Picker");
            }
        });

        et_minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new TimePickerShow();
                // Show the date picker dialog fragment
                assert getFragmentManager() != null;
                dFragment.show(getFragmentManager(), "Time Picker");
            }
        });

        et_degree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (et_random_number_question.getText().toString().isEmpty()
                        || charSequence.length() == 0
                        || et_degree.getText().toString().equals("0")) {

                    // Do not Make any Thing .
                    txFinal_Degree.setText("0");

                } else {

                    int x = Integer.parseInt(et_random_number_question.getText().toString());
                    int y = Integer.parseInt(String.valueOf(charSequence));
                    final_degree = String.valueOf(x * y);
                    txFinal_Degree.setText(final_degree);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_random_number_question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (et_degree.getText().toString().isEmpty()
                        || charSequence.length() == 0
                        || et_random_number_question.getText().toString().equals("0")) {

                    // Do not Make any Thing .
                    txFinal_Degree.setText("0");

                } else {

                    int x = Integer.parseInt(et_degree.getText().toString());
                    int y = Integer.parseInt(String.valueOf(charSequence));
                    final_degree = String.valueOf(x * y);
                    txFinal_Degree.setText(final_degree);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Btn_DeleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.ClearList();


            }
        });


        Btn_addExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Check_Hours_And_Minuts();
                if (hour > 0 || minute > 0) {
                    second = 0 ;
                    if (CheckReminderData()== 1) {

                        if(Questions.size() > 0 ) {


                            if(!ExamName.getText().toString().isEmpty()) {

                                dialog.ShowDialog();
                                //  getting time from server  ..... //
                                Map<String , String> map = new HashMap<>();
                                map.put("key", DataBase_Refrences.TimeApiKey.getRef());
                                map.put("format",DataBase_Refrences.Format.getRef());

                                addExamPresenter addExamPresenter = new addExamPresenter(addExam.this);
                                addExamPresenter.tellModelToGetDate(map);
//
//                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                                String currentDateandTime = sdf.format(new Date());
//
////                                presenter.storeExaminDatabase(hour,minute,second,et_degree.getText().toString()
////                                        ,et_random_number_question.getText().toString()
////                                        ,final_degree,Questions,ExamName.getText().toString(),currentDateandTime);



                            }
                            else {

                                Toast.makeText(getActivity(), "Enter Exam Name", Toast.LENGTH_SHORT).show();

                            }

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "No selected Questions ", Toast.LENGTH_SHORT).show();

                        }



                    }

                }


            }


        });

        //config your recycler view  .
        presenter = new addExamPresenter(this);
        presenter.CallgetQestionsToRecycleView();

        return v;
    }


    @Override
    public void realtimehere(Zone zone) {


       String date  = getDate(zone.getTimestamp());

        //  هنا الوقت موجود من هنا بقا حنقدر نخزن الوقت في الداتا بيز
        presenter.storeExaminDatabase(hour,minute,second,et_degree.getText().toString()
                ,et_random_number_question.getText().toString()
                ,final_degree,Questions,ExamName.getText().toString(),date,Questions_size.getText().toString(),zone.getTimestamp());


    }

    @Override
    public void cantgetRealTime(String E) {

    }

    @Override
    public void ConfigRecyclerview(List<Questions_Form> Questions) {
        dialog.Close_Dialog();
        this.Questions = Questions ;
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new addExamTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new addExam_Rec_Adapter(Questions, this);
        recyclerView.setAdapter(adapter);
        Update_Questions_size(Questions.size());

    }

    @Override
    public void Problem(String Result) {

        dialog.Close_Dialog();

        com.example.mohamedraslan.hossamexams.Dialog.AlertDialog alertDialog
                = new com.example.mohamedraslan.hossamexams.Dialog.AlertDialog(getActivity(),Result);
        alertDialog.show();



    }

    @Override
    public void Update_Questions_size(int lengh) {

        Questions_size.setText(lengh + "");

    }

    @Override
    public void refreshAdapter() {

        adapter.notifyDataSetChanged();

    }

    @Override
    public void Successful_Storing() {

        dialog.Close_Dialog();

        final MediaPlayer player = MediaPlayer.create(getActivity(),R.raw.plucky);
        player.start();
        if(getActivity() != null) {
            com.example.mohamedraslan.hossamexams.Dialog.AlertDialog alertDialog
                    = new com.example.mohamedraslan.hossamexams.Dialog.AlertDialog(getActivity(), "Exam added successfully");
            alertDialog.show();
        }
        //send notification to all users
        sendPost(ExamName.getText().toString());


        //reset every thing
        et_hour.setText("00");
        et_minute.setText("00");
        et_second.setText("00");
        et_random_number_question.setText("0");
        et_degree.setText("0");
        presenter.ClearList();
        txFinal_Degree.setText("0");
        ExamName.setText("");
        dialog.Close_Dialog();

    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        adapter.removeItem(viewHolder.getAdapterPosition());

    }


    public static class TimePickerShow extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.YEAR);
            int minit = calendar.get(Calendar.MONTH);

            TimePickerDialog dpd = new TimePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT, this, hour, minit, true);

            // Create a TextView programmatically.
            TextView tv = new TextView(getActivity());

            // Create a TextView programmatically
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                    RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
            tv.setLayoutParams(lp);
            tv.setPadding(10, 10, 10, 10);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            tv.setText("This is a custom title.");
            tv.setTextColor(Color.parseColor("#ff0000"));
            tv.setBackgroundColor(Color.parseColor("#FFD2DAA7"));

            dpd.setTitle("Select Time of Exam"); // Uncomment this line to activate it

            return dpd;
        }


        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {

            et_hour.setText(i + "");
            et_minute.setText(i1 + "");

        }


    }

    void Check_Hours_And_Minuts() {

        hour = 0;
        minute = 0;
        // لو الاتنين مليانين
        if (!et_hour.getText().toString().isEmpty() && !et_minute.getText().toString().isEmpty()) {


            int hours = Integer.parseInt(et_hour.getText().toString());
            int minutes = Integer.parseInt(et_minute.getText().toString());

            // لو االدقائق والساعات بيساوي صفر
            if (hours <= 0 && minutes <= 0) {


                Toast.makeText(getActivity(), "Enter Values grater than zero", Toast.LENGTH_SHORT).show();
            } else {
                hour = Integer.parseInt(et_hour.getText().toString());
                minute = Integer.parseInt(et_minute.getText().toString());


            }


        }
        // لو الساعات فاضي والدقايق فاضي
        else if (et_hour.getText().toString().isEmpty() && et_minute.getText().toString().isEmpty()) {


            Toast.makeText(getActivity(), "Enter Values grater than zero", Toast.LENGTH_SHORT).show();


        }
        // لو الساعات فاضي والدقائق مليان
        else if ((et_hour.getText().toString().isEmpty() && !et_minute.getText().toString().isEmpty())) {

            int minutes = Integer.parseInt(et_minute.getText().toString());

            if (minutes <= 0) {

                Toast.makeText(getActivity(), "fill in the cells with values grater than Zero", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "fill in the cells with values grater than Zero", Toast.LENGTH_SHORT).show();
            } else {

                hour = 0;
                minute = Integer.parseInt(et_minute.getText().toString());

            }

        }

        // لو الساعات مليان والدقائق فاضي
        else if (!et_hour.getText().toString().isEmpty() && et_minute.getText().toString().isEmpty()) {

            int hours = Integer.parseInt(et_hour.getText().toString());

            if (hours <= 0) {

                Toast.makeText(getActivity(), "fill in the hour with values grater than zero..", Toast.LENGTH_SHORT).show();
            } else {

                minute = 0;
                hour = Integer.parseInt(et_hour.getText().toString());


            }

        }
    }

    public int CheckReminderData() {
        int result = 0 ;
        if (!et_degree.getText().toString().isEmpty()) {

            int degree = Integer.parseInt(et_degree.getText().toString());
            if (degree <= 0) {
                Toast.makeText(getActivity(), "Degree Distribution must be grater than zero", Toast.LENGTH_SHORT).show();
                result = 0;
            } else {

                if (!et_random_number_question.getText().toString().isEmpty()) {

                    int random = Integer.parseInt(et_random_number_question.getText().toString());

                    if (random <= 0) {
                        Toast.makeText(getActivity(), "please select the number of questions", Toast.LENGTH_SHORT).show();
                        result = 0;
                    } else {

                        int finaldegree = Integer.parseInt(Questions_size.getText().toString());
                        if (random > finaldegree) {
                            Toast.makeText(getActivity(), "Random Questions must be less than or equal the selected questions", Toast.LENGTH_SHORT).show();
                            result = 0;
                        } else {
                            result = 1;
                        }

                    }
                }
                else {

                    result = 0;
                    Toast.makeText(getActivity(), "please fill in the question cell", Toast.LENGTH_SHORT).show();

                }

            }

        } else {
            result = 0;
            Toast.makeText(getActivity(), "please fill in the degree cell", Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    private String getDate(long time_stamp_server) {

//        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
//        return formatter.format(time_stamp_server);
          Calendar cal = Calendar.getInstance(Locale.ENGLISH);
          cal.setTimeInMillis(time_stamp_server * 1000L);
          return DateFormat.format("dd-MM-yyyy", cal).toString();

    }

    public void sendPost(String s) {


        JSONObject obj = null;
        JSONObject dataobjData = null;

        try {

            obj = new JSONObject();

            dataobjData = new JSONObject();
            dataobjData.put("image", "0");
            dataobjData.put("message","A new test has been added ( "+s+" )");

            obj.put("to", "/topics/all");
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
                        String response= null;
                        try {
                            response = new String(error.networkResponse.data,"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Log.e("Error Response",response);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "key=" + "AAAA0NknKjs:APA91bH8x30IaI5ZAz49kUGAOXEwjiFxZnWTpELAu2DMOu_vgz5GhNDnERYkv7X5Z-NveF02btyVdkMyHWhYH0wYTU3nqtbW9vx67M4Xv1vn7-rOisNEYixwQpeImD-7yguPEhTM_Nkk");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        if(getActivity() != null) {

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            int socketTimeout = 1000 * 60;// 60 seconds
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsObjRequest.setRetryPolicy(policy);
            requestQueue.add(jsObjRequest);

        }
    }


}
