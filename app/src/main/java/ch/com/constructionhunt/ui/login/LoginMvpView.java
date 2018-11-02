package ch.com.constructionhunt.ui.login;


import ch.com.constructionhunt.ui.base.MvpView;

/**
 * Created by jurgen on 10/10/2018.
 */

public interface LoginMvpView extends MvpView {
    void openDriverSelection();
    void clearEditTexts();
    void openSalesActivity();
}
