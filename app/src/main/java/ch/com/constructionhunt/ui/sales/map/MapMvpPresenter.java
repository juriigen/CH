package ch.com.constructionhunt.ui.sales.map;

import java.util.List;

import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.ui.base.MvpPresenter;

/**
 * Created by jurgen on 26.10.2018.
 */

public interface MapMvpPresenter<V extends MapMvpView>
        extends MvpPresenter<V> {
    void onViewPrepared();
    List<Location> getLocationList();
}
