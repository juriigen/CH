package ch.com.constructionhunt.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;



import javax.inject.Inject;

import ch.com.constructionhunt.MvpApp;
import ch.com.constructionhunt.di.component.DaggerServiceComponent;
import ch.com.constructionhunt.di.component.ServiceComponent;
import ch.com.constructionhunt.utils.AppLogger;

/**
 * Created by jurgen on 10/10/2018.
 */

public class SyncService extends Service {

    private static final String TAG = "SyncService";
    //todo implement data manager
   /*@Inject
   DataManager mDataManager;
*/
    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SyncService.class);
        context.startService(starter);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, SyncService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent component = DaggerServiceComponent.builder()
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();
        component.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppLogger.d(TAG, "SyncService started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        AppLogger.d(TAG, "SyncService stopped");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
