package com.example.mohamedraslan.hossamexams.Permissions;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by microprocess on 2018-10-02.
 */

public class Call_permission implements ActivityCompat.OnRequestPermissionsResultCallback {

    private final Context context ;
    public Call_permission(Context context){

    this.context = context ;
    }

    public boolean checkPermissionForCamera() {
        //check if permmision done .
        int result = ContextCompat.checkSelfPermission( context, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public void requestPermissionForCamera() {  // camera request of permision
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CALL_PHONE)) {
            Toast.makeText( context, "Call Phone permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions((Activity) context, new String[]{ Manifest.permission.CALL_PHONE}, 5);
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{ Manifest.permission.CALL_PHONE}, 5);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 5: {

                // If request is cancelled, the result arrays are empty.
                if (    grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText((Context) context, "Permission denied to phone call ", Toast.LENGTH_SHORT).show();
                }

            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @SuppressLint("MissingPermission")
    public void openCaller (Context context , String Number){


        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + Number ));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}
