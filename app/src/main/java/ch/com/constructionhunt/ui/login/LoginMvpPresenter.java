package ch.com.constructionhunt.ui.login;


import ch.com.constructionhunt.di.PerActivity;
import ch.com.constructionhunt.ui.base.MvpPresenter;

/**
 * Created by jurgen on 10/10/2018.
 */

@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String username, String password);

}
