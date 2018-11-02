package ch.com.constructionhunt.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import ch.com.constructionhunt.BuildConfig;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.data.AppDataManager;
import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.data.db.AppDbHelper;
import ch.com.constructionhunt.data.db.DbHelper;
import ch.com.constructionhunt.data.prefs.AppPreferencesHelper;
import ch.com.constructionhunt.data.prefs.PreferencesHelper;
import ch.com.constructionhunt.di.ApiInfo;
import ch.com.constructionhunt.di.ApplicationContext;
import ch.com.constructionhunt.di.DatabaseInfo;
import ch.com.constructionhunt.di.PreferenceInfo;
import ch.com.constructionhunt.utils.AppConstants;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by jurgen on 10/10/2018.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
