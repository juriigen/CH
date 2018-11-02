package ch.com.constructionhunt.ui.driver.driverlistview;

import java.util.ArrayList;

import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.ui.base.MvpView;

/**
 * Created by jurgen on 29.10.2018.
 */

public interface DriverListMvpView extends MvpView {
    void updateList(ArrayList<Location> constructionSiteList);
    void openDriverSelection();
}
