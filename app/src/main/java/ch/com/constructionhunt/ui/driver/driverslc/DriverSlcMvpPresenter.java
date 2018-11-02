package ch.com.constructionhunt.ui.driver.driverslc;

import ch.com.constructionhunt.di.PerActivity;
import ch.com.constructionhunt.ui.base.MvpPresenter;

/**
 * Created by jurgen on 12.10.2018.
 */

@PerActivity
public interface DriverSlcMvpPresenter<V extends DriverSlcMvpView> extends MvpPresenter<V> {
    void onLocationButtonClicked();
    void onLeaderboardButtonClicked();
    void onRewardButtonClicked();
    void onLogoutClick();
}

