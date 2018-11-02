package ch.com.constructionhunt.ui.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.ui.driver.driverslc.DriverSlcAvtivity;
import ch.com.constructionhunt.ui.login.LoginActivity;
import ch.com.constructionhunt.ui.base.BaseActivity;
import ch.com.constructionhunt.ui.sales.SalesActivity;


/**
 * Created by jurgen on 10/10/2018.
 */

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    /**
     * Making the screen wait so that the  branding can be shown
     */

    @Override
    public void openDriverSelection() {
        Intent intent = DriverSlcAvtivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openSalesActivity() {
        Intent intent = SalesActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void startSyncService() {
         //SyncService.start(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

}