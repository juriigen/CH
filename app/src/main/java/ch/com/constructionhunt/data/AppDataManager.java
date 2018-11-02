package ch.com.constructionhunt.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ch.com.constructionhunt.data.db.DbHelper;
import ch.com.constructionhunt.data.db.model.Driver;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.data.prefs.PreferencesHelper;
import ch.com.constructionhunt.di.ApplicationContext;
import io.reactivex.Observable;

/**
 * Created by jurgen on 11.10.2018.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";


    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }


    @Override
    public Observable<Long> insertDriver(Driver driver) {
        return mDbHelper.insertDriver(driver);
    }

    @Override
    public Observable<List<Driver>> getAllDrivers() {
        return mDbHelper.getAllDrivers();
    }

    @Override
    public void clearDriverDb() {
        mDbHelper.clearDriverDb();
    }

    @Override
    public void clearLocationDb() {
        mDbHelper.clearLocationDb();
    }

    @Override
    public Location getLocation(long id) {
        return mDbHelper.getLocation(id);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
       return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getPassword() {
        return mPreferencesHelper.getPassword();
    }

    @Override
    public void setPassword(String password) {
        mPreferencesHelper.setPassword(password);

    }


    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }


    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
    }

    @Override
    public void updateApiHeader(String username, String password) {

    }

    @Override
    public void updateUserInfo(
            String username,
            String password,
            LoggedInMode loggedInMode
    ) {
        setPassword(password);
        setCurrentUserName(username);
        setCurrentUserLoggedInMode(loggedInMode);
    }

    @Override
    public void saveLocation(Location location) {
         mDbHelper.saveLocation(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return mDbHelper.getAllLocations();
    }

}
