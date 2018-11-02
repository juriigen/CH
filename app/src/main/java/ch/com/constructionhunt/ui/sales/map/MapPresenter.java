package ch.com.constructionhunt.ui.sales.map;

import java.util.List;

import javax.inject.Inject;

import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.ui.base.BasePresenter;
import ch.com.constructionhunt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jurgen on 14.10.2018.
 */

public class MapPresenter<V extends MapMvpView> extends BasePresenter<V>
        implements MapMvpPresenter<V>{

    @Inject
    public MapPresenter(DataManager dataManager, SchedulerProvider schedulerProvider,
                        CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public List<Location> getLocationList() {
        return getDataManager().getAllLocations();
    }

    @Override
    public void onViewPrepared() {

    }
}
