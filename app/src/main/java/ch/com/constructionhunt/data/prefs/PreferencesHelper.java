package ch.com.constructionhunt.data.prefs;

import ch.com.constructionhunt.data.DataManager;

/**
 * Created by jurgen on 12.10.2018.
 */

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getPassword();

    void setPassword(String password);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

}
