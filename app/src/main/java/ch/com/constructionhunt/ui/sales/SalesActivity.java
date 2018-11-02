package ch.com.constructionhunt.ui.sales;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.ui.base.BaseActivity;
import ch.com.constructionhunt.ui.login.LoginActivity;
import ch.com.constructionhunt.ui.sales.listview.SalesListMvpPresenter;
import ch.com.constructionhunt.ui.sales.listview.SalesListMvpView;
import ch.com.constructionhunt.ui.sales.listview.details.ConstructionSiteFragment;
import ch.com.constructionhunt.ui.sales.map.MapMvpPresenter;
import ch.com.constructionhunt.ui.sales.map.MapMvpView;
import ch.com.constructionhunt.utils.NetworkUtils;

/**
 * Created by jurgen on 17.10.2018.
 */

public class SalesActivity extends BaseActivity implements SalesMvpView {

    @Inject
    SalesListMvpPresenter<SalesListMvpView> mvpViewSalesListMvpPresenter;

    @Inject
    MapMvpPresenter<MapMvpView> mapMvpViewMapMvpPresenter;

    @Inject
    SalesMvpPresenter<SalesMvpView> mPresenter;

    @Inject
    SalesPagerAdapter mPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.feed_view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SalesActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {

        setSupportActionBar(mToolbar);

        mPagerAdapter.setCount(2);

        mViewPager.setAdapter(mPagerAdapter);

      /*  mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.sales_list_view)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.sales_map_view)));*/

        mViewPager.setOffscreenPageLimit(mTabLayout.getTabCount());

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
            case R.id.nav_item_logout:
                mPresenter.onLogoutClick();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(SalesActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        ConstructionSiteFragment fragment = (ConstructionSiteFragment) getSupportFragmentManager().findFragmentByTag(ConstructionSiteFragment.TAG);
        if (fragment != null) {
            super.onBackPressed();
        } else {
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

}