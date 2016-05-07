package th.co.itgenius.mvcandroidapp.manager.gcmservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import th.co.itgenius.mvcandroidapp.R;

/**
 * Created by samit on 7/5/2559.
 */
public class GcmRegisterService extends IntentService {

    // ข้อมูลการลงทะเบียน
    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};
    public static final String SENT_TOKEN_TO_SERVER = "SentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationCompleate";

    public GcmRegisterService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferencesCompat = PreferenceManager.getDefaultSharedPreferences(this);

        // Start register to GCM
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);

            Log.i(TAG, "GCM Registration Token: "+token);

            // Server send request
            sendRegistrationToServer(token);

            // Subscribe to topic channels
            subscribeTopic(token);

            // กรณีเคยเก็บ Token ไว้แล้ว
            sharedPreferencesCompat.edit().putBoolean(SENT_TOKEN_TO_SERVER,true).apply();

        }catch (Exception e){

            Log.d(TAG, "Fail to compleate regsiter",e);
            sharedPreferencesCompat.edit().putBoolean(SENT_TOKEN_TO_SERVER,false).apply();

        }

        // Notify UI when register complete
        Intent registrationComplete = new Intent(REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);

    }

    private void subscribeTopic(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for(String topic : TOPICS){
            pubSub.subscribe(token,"/topics/"+topic,null);
        }
    }

    private void sendRegistrationToServer(String token) {
            //  Add data TOKEN KEY to Server
            // ....
        Log.e(TAG, "Device TOKEN: "+token);
    }


}
