package com.example.mohamedraslan.hossamexams.Notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.mohamedraslan.hossamexams.R;
import com.example.mohamedraslan.hossamexams.Views.ControlPanel;
import com.example.mohamedraslan.hossamexams.Views.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by microprocess on 2018-07-08.
 */

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "Android News App";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {




        //imageUri will contain URL of the image to be displayed with Notification


        String imageUri = remoteMessage.getData().get("image");
        String message = remoteMessage.getData().get("message");
        //Calling method to generate notification

      //  Bitmap Image_Bitmap = getBitmapFromURL(imageUri);
        Bitmap Image_Bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.contracting);

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {


                String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (Uid != null && !Uid.isEmpty()) {

                  // doSomething
                    sendNotification(message, Image_Bitmap);

                }


        }


    }

    //This method is only generating push notification
    private void sendNotification(String messageBody ,Bitmap imageUri ) {
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle(getString(R.string.app_name))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setChannelId("default_channel_id")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(imageUri).setSummaryText(messageBody))/*Notification with Image*/
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notificationManager.getNotificationChannel("default_channel_id");
            if (mChannel == null) {
                mChannel = new NotificationChannel("default_channel_id", getString(R.string.app_name), importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notificationManager.createNotificationChannel(mChannel);
            }
        }
        notificationManager.notify(m, notificationBuilder.build());

    }
    public static Bitmap getBitmapFromURL(String url) {
        try {
            URL Url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmapFrmUrl = BitmapFactory.decodeStream(input);
            return bitmapFrmUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}