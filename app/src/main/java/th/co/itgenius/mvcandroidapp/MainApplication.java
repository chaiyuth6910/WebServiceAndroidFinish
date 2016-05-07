package th.co.itgenius.mvcandroidapp;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;

import com.itgenius.mvclibrary.manager.Contextor;

import th.co.itgenius.mvcandroidapp.manager.gcmservice.GcmRegisterService;

/**
 * Created by samit on 6/5/2559.
 */
public class MainApplication extends Application {

    private boolean isReceiverRegistered;

    @Override
    public void onCreate() {
        super.onCreate();

        // Call Contextor
        Contextor.getInstance().init(getApplicationContext());

        // Notification
        registerGcm();

        // Recieve Message
        registerReceiver();

    }

    private void registerGcm() {
        Intent intent = new Intent(this, GcmRegisterService.class);
        startService(intent);
    }

    private void registerReceiver() {

        if (!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(GcmRegisterService.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }

    private BroadcastReceiver mRegistrationBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            boolean sentToken = sharedPreferences.getBoolean(GcmRegisterService.SENT_TOKEN_TO_SERVER, false);
            // TODO Do something here

        }
    };


    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
