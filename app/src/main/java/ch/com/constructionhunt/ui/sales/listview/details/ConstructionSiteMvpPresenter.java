package ch.com.constructionhunt.ui.sales.listview.details;

import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.ui.base.MvpPresenter;

/**
 * Created by jurgen on 17.10.2018.
 */

public interface ConstructionSiteMvpPresenter<V extends ConstructionSiteMvpView>
        extends MvpPresenter<V> {
    Location getConstructionSiteDetails();
    void sendUpdatedState();
}