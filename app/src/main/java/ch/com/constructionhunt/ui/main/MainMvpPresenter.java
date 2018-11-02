package ch.com.constructionhunt.ui.main;


import ch.com.constructionhunt.di.PerActivity;
import ch.com.constructionhunt.ui.base.MvpPresenter;

/**
 * Created by jurgen on 10/10/2018.
 */
@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {


    void onDrawerOptionLogoutClick();

    void onViewInitialized();

    void onNavMenuCreated();
}
