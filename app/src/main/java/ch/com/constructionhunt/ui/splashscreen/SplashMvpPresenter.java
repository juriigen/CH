package ch.com.constructionhunt.ui.splashscreen;


import ch.com.constructionhunt.di.PerActivity;
import ch.com.constructionhunt.ui.base.MvpPresenter;

/**
 * Created by jurgen on 10/10/2018.
 */

@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {

}
