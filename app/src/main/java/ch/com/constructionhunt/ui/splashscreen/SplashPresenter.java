package ch.com.constructionhunt.ui.splashscreen;


import android.os.Handler;

import javax.inject.Inject;

import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.ui.base.BasePresenter;
import ch.com.constructionhunt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jurgen on 10/10/2018.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager, SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        getMvpView().startSyncService();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                decideNextActivity();
            }
        }, 1000);
    }

    private void decideNextActivity() {
        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getMvpView().openLoginActivity();
        } else {
            if (getDataManager().getCurrentUserName().equals("lukas")) {
                getMvpView().openSalesActivity();
            } else if (getDataManager().getCurrentUserName().equals("peter")) {
                getMvpView().openDriverSelection();
            }

        }
    }
}
