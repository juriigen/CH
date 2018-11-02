package ch.com.constructionhunt.data;

import ch.com.constructionhunt.data.db.DbHelper;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.data.prefs.PreferencesHelper;

/**
 * Created by jurgen on 11.10.2018.
 */

public interface DataManager extends DbHelper, PreferencesHelper{

    void setUserAsLoggedOut();
    void updateApiHeader(String username, String password);
    void updateUserInfo(
            String username,
            String password,
            LoggedInMode loggedInMode);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
