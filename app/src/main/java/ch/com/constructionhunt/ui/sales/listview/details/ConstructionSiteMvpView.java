package ch.com.constructionhunt.ui.sales.listview.details;

import ch.com.constructionhunt.ui.base.MvpView;

/**
 * Created by jurgen on 17.10.2018.
 */

public interface ConstructionSiteMvpView extends MvpView {
    long getConstructionSiteId();
    String getConstructionSiteState();
}
