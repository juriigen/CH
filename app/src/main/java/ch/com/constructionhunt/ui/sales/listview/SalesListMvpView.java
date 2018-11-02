package ch.com.constructionhunt.ui.sales.listview;

import java.util.ArrayList;

import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.ui.base.MvpView;

/**
 * Created by jurgen on 17.10.2018.
 */

public interface SalesListMvpView extends MvpView {

    void updateList(ArrayList<Location> constructionSiteList);
}

