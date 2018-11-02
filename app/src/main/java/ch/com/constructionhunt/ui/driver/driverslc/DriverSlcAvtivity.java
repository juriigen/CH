package ch.com.constructionhunt.ui.driver.driverslc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.ui.base.BaseActivity;
import ch.com.constructionhunt.ui.driver.constructionsitelocation.LocationActivity;
import ch.com.constructionhunt.ui.driver.driverlistview.DriverListActivity;
import ch.com.constructionhunt.ui.driver.reward.RewardActivity;
import ch.com.constructionhunt.ui.login.LoginActivity;

/**
 * Created by jurgen on 12.10.2018.
 */

public class DriverSlcAvtivity extends BaseActivity implements DriverSlcMvpView {

    @Inject
    DriverSlcMvpPresenter<DriverSlcMvpView> mPresenter;

    @BindView(R.id.location_rl)
    RelativeLayout locationButton;

    @BindView(R.id.listview_rl)
    RelativeLayout leaderboardButton;

    @BindView(R.id.reward_rl)
    RelativeLayout rewardButton;

    @BindView(R.id.toolbarDriver)
    Toolbar mToolbar;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DriverSlcAvtivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svsd);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(DriverSlcAvtivity.this);
        setUp();
    }

    @OnClick(R.id.location_rl)
    void onLocationButtonClicked(View v) {
        mPresenter.onLocationButtonClicked();
    }

    @OnClick(R.id.listview_rl)
    void onLeaderboardButtonClicked(View v) {
        mPresenter.onLeaderboardButtonClicked();
    }

    @OnClick(R.id.reward_rl)
    void onRewardButtonClicked(View v){
     mPresenter.onRewardButtonClicked();
    }

    @Override
    public void openLocationActivity() {
        Intent intent = LocationActivity.getStartIntent(DriverSlcAvtivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public void openDriverListActivity() {
        Intent intent = DriverListActivity.getStartIntent(DriverSlcAvtivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openRewardActivity() {
        Intent intent = RewardActivity.getStartIntent(DriverSlcAvtivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(DriverSlcAvtivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_item_logout:
                mPresenter.onLogoutClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.quit)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }


}
