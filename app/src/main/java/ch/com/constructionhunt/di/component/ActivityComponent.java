package ch.com.constructionhunt.di.component;

import ch.com.constructionhunt.di.PerActivity;
import ch.com.constructionhunt.di.module.ActivityModule;
import ch.com.constructionhunt.ui.driver.constructionsitelocation.LocationActivity;
import ch.com.constructionhunt.ui.driver.driverlistview.DriverListActivity;
import ch.com.constructionhunt.ui.driver.driverslc.DriverSlcAvtivity;
import ch.com.constructionhunt.ui.driver.reward.RewardActivity;
import ch.com.constructionhunt.ui.login.LoginActivity;
import ch.com.constructionhunt.ui.main.MainActivity;
import ch.com.constructionhunt.ui.sales.SalesActivity;
import ch.com.constructionhunt.ui.sales.listview.SalesListFragment;
import ch.com.constructionhunt.ui.sales.listview.details.ConstructionSiteFragment;
import ch.com.constructionhunt.ui.sales.map.MapFragmentCh;
import ch.com.constructionhunt.ui.splashscreen.SplashActivity;
import dagger.Component;

/**
 * Created by jurgen on 10/10/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(LocationActivity activity);

    void inject(MapFragmentCh fragment);

    void inject(SalesListFragment fragment);

    void inject(ConstructionSiteFragment fragment);

    void inject(SalesActivity activity);

    void inject(RewardActivity activity);

    void inject(DriverListActivity activity);

    void inject(DriverSlcAvtivity activity);
}
