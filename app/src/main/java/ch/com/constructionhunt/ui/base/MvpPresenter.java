package ch.com.constructionhunt.ui.base;

/**
 * Created by jurgen on 10/10/2018.
 */

import com.androidnetworking.error.ANError;

import ch.com.constructionhunt.ui.base.MvpView;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(ANError error);

    void setUserAsLoggedOut();
}
