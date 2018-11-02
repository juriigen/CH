package ch.com.constructionhunt.ui.splashscreen;


import ch.com.constructionhunt.ui.base.MvpView;

/**
 * Created by jurgen on 10/10/2018.
 */

public interface SplashMvpView extends MvpView {

    void openLoginActivity();

    void openDriverSelection();

    void openSalesActivity();

    void startSyncService();

}
