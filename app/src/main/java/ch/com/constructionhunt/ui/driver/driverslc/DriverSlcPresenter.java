package ch.com.constructionhunt.ui.driver.driverslc;

import javax.inject.Inject;

import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.ui.base.BasePresenter;
import ch.com.constructionhunt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jurgen on 12.10.2018.
 */

public class DriverSlcPresenter<V extends DriverSlcMvpView> extends BasePresenter<V>
        implements DriverSlcMvpPresenter<V> {

    private static final String TAG = "DriverSlcPresenter";

    @Inject
    public DriverSlcPresenter(DataManager dataManager, SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onLeaderboardButtonClicked() {
        getMvpView().showLoading();
        getMvpView().hideLoading();
        getMvpView().openDriverListActivity();
    }

    @Override
    public void onRewardButtonClicked() {
        getMvpView().showLoading();
        getMvpView().hideLoading();
        getMvpView().openRewardActivity();
    }

    @Override
    public void onLocationButtonClicked() {
        getMvpView().showLoading();
        getMvpView().hideLoading();
        getMvpView().openLocationActivity();
    }
    public void onLogoutClick(){
        getDataManager().setUserAsLoggedOut();
        getMvpView().openLoginActivity();
    }
}

