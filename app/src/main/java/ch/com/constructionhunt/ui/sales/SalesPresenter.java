package ch.com.constructionhunt.ui.sales;

import javax.inject.Inject;

import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.ui.base.BasePresenter;
import ch.com.constructionhunt.ui.base.MvpView;
import ch.com.constructionhunt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jurgen on 17.10.2018.
 */

public class SalesPresenter <V extends SalesMvpView> extends BasePresenter<V> implements
        SalesMvpPresenter<V> {

    private static final String TAG = "SalesPresenter";

    @Inject
    public SalesPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    public void onLogoutClick(){
        getDataManager().setUserAsLoggedOut();
        getMvpView().openLoginActivity();
    }
}
