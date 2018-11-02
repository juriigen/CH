package ch.com.constructionhunt;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor.Level;


import javax.inject.Inject;

import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.di.component.ApplicationComponent;
import ch.com.constructionhunt.di.component.DaggerApplicationComponent;
import ch.com.constructionhunt.di.module.ApplicationModule;
import ch.com.constructionhunt.utils.AppLogger;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by jurgen on 10/10/2018.
 */
public class MvpApp extends Application {


    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
