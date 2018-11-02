package ch.com.constructionhunt.ui.sales.map;

import ch.com.constructionhunt.ui.base.MvpView;

/**
 * Created by jurgen on 14.10.2018.
 */

public interface MapMvpView extends MvpView {
    void removeMarkers();
    void showMarkerAt(double latitude, double longitude);
}
