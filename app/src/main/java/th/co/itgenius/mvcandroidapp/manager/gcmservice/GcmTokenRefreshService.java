package th.co.itgenius.mvcandroidapp.manager.gcmservice;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by samit on 7/5/2559.
 */
public class GcmTokenRefreshService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this,GcmTokenRefreshService.class);
        startService(intent);
    }
}
