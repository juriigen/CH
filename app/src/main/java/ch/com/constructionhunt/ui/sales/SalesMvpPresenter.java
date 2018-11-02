package ch.com.constructionhunt.ui.sales;

import ch.com.constructionhunt.di.PerActivity;
import ch.com.constructionhunt.ui.base.MvpPresenter;
import ch.com.constructionhunt.ui.base.MvpView;

/**
 * Created by jurgen on 17.10.2018.
 */
@PerActivity
public interface SalesMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    void onLogoutClick();
}
