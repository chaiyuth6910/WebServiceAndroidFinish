package th.co.itgenius.mvcandroidapp.manager.gcmservice;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import th.co.itgenius.mvcandroidapp.R;
import th.co.itgenius.mvcandroidapp.activity.MainActivity;

/**
 * Created by samit on 7/5/2559.
 */
public class GcmDownstreamService extends GcmListenerService {
    private static final String TAG = "GcmDownstreamService";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        // To do something here...
        Log.e(TAG, "Message Incomming");

        Bundle notificationData = data.getBundle("notification");
        Log.e(TAG, "Title: "+notificationData.getString("title"));
        Log.e(TAG, "Body: "+notificationData.getString("body"));
        Log.e(TAG, "icon: "+notificationData.getString("icon"));

        Notification notification =
                new NotificationCompat.Builder(getApplicationContext()) // this is context
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(notificationData.getString("title"))
                        .setContentText(notificationData.getString("body"))
                        .setAutoCancel(true)
                        .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);
    }
}
