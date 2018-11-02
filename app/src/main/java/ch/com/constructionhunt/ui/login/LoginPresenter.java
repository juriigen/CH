package ch.com.constructionhunt.ui.login;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;

import ch.com.constructionhunt.R;
import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.data.network.ApiEndPoint;
import ch.com.constructionhunt.ui.base.BasePresenter;
import ch.com.constructionhunt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jurgen on 10/10/2018.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(DataManager dataManager, SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onServerLoginClick(String username, String password) {
        //validate email and password
        if (username == null || username.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }
        getMvpView().showLoading();

        doServerLoginApiCall(username, password);


    }

    private void doServerLoginApiCall(final String username, final String password) {
        Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_LOGIN + "username=" + username + "&password=" + password)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("driver authenticated: "+username)) {
                            getDataManager().updateUserInfo(
                                    username,
                                    password,
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER);
                            getMvpView().hideLoading();
                            getMvpView().openDriverSelection();
                        }else if(response.equals("sales_agent authenticated: "+username)) {
                            getDataManager().updateUserInfo(
                                    username,
                                    password,
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER);
                            getMvpView().hideLoading();
                            getMvpView().openSalesActivity();
                        }
                    }


                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorBody().equals("wrong credentials")){
                            getMvpView().hideLoading();
                            getMvpView().clearEditTexts();
                            getMvpView().showMessage("Wrong credentials");
                        }else {
                            handleApiError(anError);
                        }
                    }
                });
    }
}
