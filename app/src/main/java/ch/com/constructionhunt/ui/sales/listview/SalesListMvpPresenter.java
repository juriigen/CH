package ch.com.constructionhunt.ui.sales.listview;

import ch.com.constructionhunt.ui.base.MvpPresenter;

/**
 * Created by jurgen on 17.10.2018.
 */

public interface SalesListMvpPresenter<V extends SalesListMvpView>
        extends MvpPresenter<V> {

    void onViewPrepared();
}