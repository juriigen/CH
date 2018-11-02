package ch.com.constructionhunt.ui.main;


import java.util.List;

import ch.com.constructionhunt.ui.base.MvpView;

/**
 * Created by jurgen on 10/10/2018.
 */

public interface MainMvpView extends MvpView {

    void openLoginActivity();

    void updateUserName(String currentUserName);

    void updateUserEmail(String currentUserEmail);

    void updateUserProfilePic(String currentUserProfilePicUrl);

    void updateAppVersion();

    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();
}
