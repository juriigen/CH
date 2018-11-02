package ch.com.constructionhunt.ui.driver.constructionsitelocation;

import ch.com.constructionhunt.di.PerActivity;
import ch.com.constructionhunt.ui.base.MvpPresenter;

/**
 * Created by jurgen on 11.10.2018.
 */

@PerActivity
public interface LocationMvpPresenter<V extends LocationMvpView> extends MvpPresenter<V> {

    String getUsername();
    String getPassword();
}
