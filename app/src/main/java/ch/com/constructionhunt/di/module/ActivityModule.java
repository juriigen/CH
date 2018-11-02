package ch.com.constructionhunt.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;


import java.util.ArrayList;

import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.di.ActivityContext;
import ch.com.constructionhunt.di.PerActivity;
import ch.com.constructionhunt.ui.driver.constructionsitelocation.LocationMvpPresenter;
import ch.com.constructionhunt.ui.driver.driverlistview.DriverListAdapter;
import ch.com.constructionhunt.ui.driver.driverlistview.DriverListMvpPresenter;
import ch.com.constructionhunt.ui.driver.driverlistview.DriverListMvpView;
import ch.com.constructionhunt.ui.driver.driverlistview.DriverListPresenter;
import ch.com.constructionhunt.ui.driver.driverslc.DriverSlcMvpView;
import ch.com.constructionhunt.ui.driver.driverslc.DriverSlcPresenter;
import ch.com.constructionhunt.ui.driver.reward.RewardMvpPresenter;
import ch.com.constructionhunt.ui.driver.reward.RewardMvpView;
import ch.com.constructionhunt.ui.driver.reward.RewardPresenter;
import ch.com.constructionhunt.ui.login.LoginMvpPresenter;
import ch.com.constructionhunt.ui.login.LoginMvpView;
import ch.com.constructionhunt.ui.login.LoginPresenter;
import ch.com.constructionhunt.ui.main.MainMvpPresenter;
import ch.com.constructionhunt.ui.main.MainMvpView;
import ch.com.constructionhunt.ui.main.MainPresenter;
import ch.com.constructionhunt.ui.driver.constructionsitelocation.LocationMvpView;
import ch.com.constructionhunt.ui.driver.constructionsitelocation.LocationPresenter;
import ch.com.constructionhunt.ui.sales.SalesMvpPresenter;
import ch.com.constructionhunt.ui.sales.SalesMvpView;
import ch.com.constructionhunt.ui.sales.SalesPagerAdapter;
import ch.com.constructionhunt.ui.sales.SalesPresenter;
import ch.com.constructionhunt.ui.sales.listview.SalesListAdapter;
import ch.com.constructionhunt.ui.sales.listview.SalesListMvpPresenter;
import ch.com.constructionhunt.ui.sales.listview.SalesListMvpView;
import ch.com.constructionhunt.ui.sales.listview.SalesListPresenter;
import ch.com.constructionhunt.ui.sales.listview.details.ConstructionSiteMvpPresenter;
import ch.com.constructionhunt.ui.sales.listview.details.ConstructionSiteMvpView;
import ch.com.constructionhunt.ui.sales.listview.details.ConstructionSitePresenter;
import ch.com.constructionhunt.ui.sales.map.MapMvpPresenter;
import ch.com.constructionhunt.ui.sales.map.MapMvpView;
import ch.com.constructionhunt.ui.sales.map.MapPresenter;
import ch.com.constructionhunt.ui.splashscreen.SplashMvpPresenter;
import ch.com.constructionhunt.ui.splashscreen.SplashMvpView;
import ch.com.constructionhunt.ui.splashscreen.SplashPresenter;
import ch.com.constructionhunt.ui.driver.driverslc.DriverSlcMvpPresenter;
import ch.com.constructionhunt.utils.rx.AppSchedulerProvider;
import ch.com.constructionhunt.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jurgen on 10/10/2018.
 */


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity
    DriverListMvpPresenter<DriverListMvpView> provideDriverListPresenter(
            DriverListPresenter<DriverListMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SalesMvpPresenter<SalesMvpView> provideSalesPresenter(
            SalesPresenter<SalesMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    RewardMvpPresenter<RewardMvpView> provideRewardPresenter(
            RewardPresenter<RewardMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LocationMvpPresenter<LocationMvpView> provideLocationPresenter(
            LocationPresenter<LocationMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DriverSlcMvpPresenter<DriverSlcMvpView> provideSvsdPresenter(
            DriverSlcPresenter<DriverSlcMvpView> presenter) {
        return presenter;
    }

    @Provides
    SalesListMvpPresenter<SalesListMvpView> provideSalesListMvpPresenter(
                    SalesListPresenter<SalesListMvpView> presenter) {
        return presenter;
    }
    @Provides
    MapMvpPresenter<MapMvpView> provideMapMvpPresenter(
        MapPresenter<MapMvpView> presenter){
        return presenter;
    }

    @Provides
    ConstructionSiteMvpPresenter<ConstructionSiteMvpView> provideConstructionSiteMvpPresenter(
            ConstructionSitePresenter<ConstructionSiteMvpView> presenter) {
        return presenter;
    }
    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    SalesPagerAdapter provideSalesPagerAdapter(AppCompatActivity activity) {
        return new SalesPagerAdapter(activity.getSupportFragmentManager());
    }
    @Provides
    SalesListAdapter provideSalesListAdapter() {
        return new SalesListAdapter(new ArrayList<Location>());
    }

    @Provides
    DriverListAdapter provideDriverListAdapter() {
        return new DriverListAdapter(new ArrayList<Location>());
    }
}
