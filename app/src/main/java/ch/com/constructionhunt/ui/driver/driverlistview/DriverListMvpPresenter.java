package ch.com.constructionhunt.ui.driver.driverlistview;

import ch.com.constructionhunt.ui.base.MvpPresenter;

/**
 * Created by jurgen on 29.10.2018.
 */

public interface DriverListMvpPresenter<V extends DriverListMvpView>
        extends MvpPresenter<V> {
    void onViewPrepared();
}
