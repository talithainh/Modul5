package com.example.talit.modul5;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

//digunakan untuk ngecek kirim pesan dari dloud messaging firebase
    private static final String TAG = "FCM_GUE";

    //untuk menampilkan token pada logcat
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
    }


    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "Pengirim: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG,"Pesannya: " + remoteMessage.getData().get("body"));
        }
        MyNotificationManager.getmInstance(this).displayNotification(
                remoteMessage.getData().get("body"),
                remoteMessage.getData().get("title")
        );

    }

}
