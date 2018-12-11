package com.developer.mohamedraslan.hossamexams.MainModle;

import android.support.annotation.NonNull;

import com.developer.mohamedraslan.hossamexams.Contracts.Unites_Contract;
import com.developer.mohamedraslan.hossamexams.Dialog.AlertDialog;
import com.developer.mohamedraslan.hossamexams.Enums.DataBase_Refrences;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.JsonModel.Unites_Model_Json;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UnitesModel implements Unites_Contract.ParentUnitesModel {


    Unites_Contract.ParentUnitesPresnter parentUnitesPresnter;
    Unites_Model_Json unites_model_json;

    List<String> listunit;

    public UnitesModel(Unites_Contract.ParentUnitesPresnter parentUnitesPresnter) {
        this.parentUnitesPresnter = parentUnitesPresnter;
        listunit                  = new ArrayList<>();
    }


    @Override
    public void addUnite(String depName, String yearName,String unitName) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        final String startTime = sdf.format(new Date());

        // احنا حنستخدم الترقيم افضل من ال timeStamp في حاله الوحدات اولا لانها معدوده ثانيا في حاله المسح والاضافه مره اخري الترقيم حيبوظ
        switch (unitName){

            case "The First unit":
                unites_model_json = new Unites_Model_Json(unitName,1000);
                break;
            case "The Second Unit":
                unites_model_json = new Unites_Model_Json(unitName,2000);
                break;
            case "The Third Unit":
                unites_model_json = new Unites_Model_Json(unitName,3000);
                break;
            case "The Fourth Unit":
                unites_model_json = new Unites_Model_Json(unitName,4000);
                break;
            case "Fifth Unit":
                unites_model_json = new Unites_Model_Json(unitName,5000);
                break;
            case "Sixth Unit":
                unites_model_json = new Unites_Model_Json(unitName,6000);
                break;
            case "The Seventh Unit":
                unites_model_json = new Unites_Model_Json(unitName,7000);
                break;
            case "The Eighth Unit":
                unites_model_json = new Unites_Model_Json(unitName,8000);
                break;
            case "The ninth unit":
                unites_model_json = new Unites_Model_Json(unitName,9000);
                break;
                default:
                    //  مستحيل حتتنفذ لانهم معدودين
                    unites_model_json = new Unites_Model_Json(unitName,getTimeStamp(startTime));



        }

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Unites");
        reference.child(depName).child(yearName).child(unitName).setValue(unites_model_json).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    parentUnitesPresnter.unitSussAdded();
                }
                else {
                    parentUnitesPresnter.unitedcassesProblem();
                }


            }
        });

    }

    @Override
    public void getUnites(String depName, String yearName) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Unites");
        reference.child(depName).child(yearName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){


                    HashMap<String, Unites_Model_Json> results = dataSnapshot.getValue(new GenericTypeIndicator<HashMap<String, Unites_Model_Json>>() {});
                    List<Unites_Model_Json> toutourial = new ArrayList<>(Objects.requireNonNull(results).values());
                    parentUnitesPresnter.ifoundUnites(toutourial);
                }
                else {

                    parentUnitesPresnter.inoFoundUnites();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

              parentUnitesPresnter.problemOcurrs(databaseError.toString());

            }
        });

    }

    @Override
    public void checkingUnitofYear(String depName, String yearName, final String unitName) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference       = firebaseDatabase.getReference("Unites");
        reference.child(depName).child(yearName).child(unitName).addListenerForSingleValueEvent( new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    parentUnitesPresnter.tellUIunitExisitinData();

                } else {
                    parentUnitesPresnter.tellUiunitNotFount(unitName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                parentUnitesPresnter.callUIaboutConnection(databaseError.toString());

            }
        });



    }

    @Override
    public void checkifUserAdminorNot(String uID) {


        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(DataBase_Refrences.ADMIN.getRef())
                .child(uID);



        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    parentUnitesPresnter.happyImAdmin();

                }
                else {
                  parentUnitesPresnter.sadImUser();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


                //  noThinmg toDo this ocuur when happen Exception

            }
        });






    }

    @Override
    public void deleteUnit(final String depName, final String yearNmae, final String unitName) {



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference reference       = firebaseDatabase.getReference(DataBase_Refrences.EXAMS.getRef());
        reference.child(depName).child(yearNmae).child(unitName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){


                    reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if (task.isSuccessful()){

                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ExamStarted").child(depName).child(yearNmae).child(unitName);
                                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){


                                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.Permissions.getRef()).child(depName).child(yearNmae).child(unitName);
                                            reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()){


                                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PermissionRefrence").child(depName).child(yearNmae).child(unitName);
                                                        reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()){



                                                                    DatabaseReference referenceresultREF = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.ResultsRef.getRef()).child(depName).child(yearNmae).child(unitName);
                                                                    referenceresultREF.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                            if (task.isSuccessful()){

                                                                                DatabaseReference referenceresultREFR = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.RESULT.getRef()).child(depName).child(yearNmae).child(unitName);
                                                                                referenceresultREFR.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {


                                                                                        if (task.isSuccessful()){


                                                                                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                                                                            final DatabaseReference referenceQ       = firebaseDatabase.getReference("Banck_Questions");

                                                                                            referenceQ.child(depName).child(yearNmae).child(unitName).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                @Override
                                                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                                                                    if (dataSnapshot.exists()){

                                                                                                        referenceQ.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                            @Override
                                                                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                                                                if (task.isSuccessful()){


                                                                                                                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                                                                                                    final DatabaseReference referenceunit       = firebaseDatabase.getReference("Unites").child(depName).child(yearNmae).child(unitName);

                                                                                                                    referenceunit.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                        @Override
                                                                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                                                                            if (task.isSuccessful())
                                                                                                                                parentUnitesPresnter.unitDeleted();
                                                                                                                        }
                                                                                                                    });
                                                                                                                    //  كدا كل شي اتمسح


                                                                                                                }

                                                                                                            }
                                                                                                        });


                                                                                                    }else {



                                                                                                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                                                                                        final DatabaseReference referenceunit       = firebaseDatabase.getReference("Unites").child(depName).child(yearNmae).child(unitName);

                                                                                                        referenceunit.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                            @Override
                                                                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                                                                if (task.isSuccessful()){

                                                                                                                    parentUnitesPresnter.unitDeleted();
                                                                                                                }

                                                                                                            }
                                                                                                        });


                                                                                                    }


                                                                                                }

                                                                                                @Override
                                                                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                                }
                                                                                            });






                                                                                        }

                                                                                    }
                                                                                });

                                                                            }

                                                                        }
                                                                    });

                                                                    //  حنمسح بقااا الاسئله



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

                        }
                    });



                }else {




                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    final DatabaseReference referenceQ       = firebaseDatabase.getReference("Banck_Questions");

                    referenceQ.child(depName).child(yearNmae).child(unitName).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()){

                                referenceQ.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()){



                                            DatabaseReference referenceresultREF = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.ResultsRef.getRef()).child(depName).child(yearNmae).child(unitName);
                                            referenceresultREF.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {


                                                    if (task.isSuccessful()){

                                                        DatabaseReference referenceresultREFR = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.RESULT.getRef()).child(depName).child(yearNmae).child(unitName);
                                                        referenceresultREFR.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {


                                                                if (task.isSuccessful()){

                                                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.Permissions.getRef()).child(depName).child(yearNmae).child(unitName);

                                                                    reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                            if (task.isSuccessful()){

                                                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PermissionRefrence").child(depName).child(yearNmae).child(unitName);
                                                                                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                                        if (task.isSuccessful()){

                                                                                            FirebaseDatabase firebaseDatabase           = FirebaseDatabase.getInstance();
                                                                                            final DatabaseReference referenceunit       = firebaseDatabase.getReference("Unites").child(depName).child(yearNmae).child(unitName);

                                                                                            referenceunit.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                @Override
                                                                                                public void onComplete(@NonNull Task<Void> task) {



                                                                                                    parentUnitesPresnter.unitDeleted();
                                                                                                }
                                                                                            });

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

                                                }
                                            });








                                        }

                                    }
                                });


                            }else {



                                //  حنمسح الوحده بقاا



                                DatabaseReference referenceresultREF = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.ResultsRef.getRef()).child(depName).child(yearNmae).child(unitName);
                                referenceresultREF.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {


                                        if (task.isSuccessful()){

                                            DatabaseReference referenceresultREFR = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.RESULT.getRef()).child(depName).child(yearNmae).child(unitName);
                                            referenceresultREFR.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {


                                                    if (task.isSuccessful()){


                                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.Permissions.getRef()).child(depName).child(yearNmae).child(unitName);

                                                        reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if (task.isSuccessful()){

                                                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PermissionRefrence").child(depName).child(yearNmae).child(unitName);
                                                                    reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                            if (task.isSuccessful()){

                                                                                FirebaseDatabase firebaseDatabase           = FirebaseDatabase.getInstance();
                                                                                final DatabaseReference referenceunit       = firebaseDatabase.getReference("Unites").child(depName).child(yearNmae).child(unitName);

                                                                                referenceunit.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        parentUnitesPresnter.unitDeleted();
                                                                                    }
                                                                                });

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

                                    }
                                });





                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                parentUnitesPresnter.problemwithD(databaseError.toString());

            }
        });




    }

    public long getTimeStamp(String startTime){







        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long output=date.getTime()/1000L;
        String str=Long.toString(output);
        return Long.parseLong(str) * 1000;


    }
}
