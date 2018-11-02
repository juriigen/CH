package ch.com.constructionhunt.ui.driver.constructionsitelocation;

import javax.inject.Inject;

import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.ui.base.BasePresenter;
import ch.com.constructionhunt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jurgen on 11.10.2018.
 */

public class LocationPresenter<V extends LocationMvpView> extends BasePresenter<V> implements LocationMvpPresenter<V> {


    @Inject
    public LocationPresenter(DataManager dataManager, SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }



    @Override
    public String getUsername() {
        return getDataManager().getCurrentUserName();
    }

    @Override
    public String getPassword() {
        return getDataManager().getPassword();
    }

}

