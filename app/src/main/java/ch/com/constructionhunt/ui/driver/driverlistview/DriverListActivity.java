package ch.com.constructionhunt.ui.driver.driverlistview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.ui.base.BaseActivity;
import ch.com.constructionhunt.ui.driver.constructionsitelocation.LocationActivity;
import ch.com.constructionhunt.ui.driver.driverslc.DriverSlcAvtivity;

/**
 * Created by jurgen on 29.10.2018.
 */

public class DriverListActivity extends BaseActivity implements DriverListMvpView {

    private static final String TAG = "DriverListActivity";

    @Inject
    DriverListAdapter driverListAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.driver_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbarMain)
    Toolbar mToolbar;

    @Inject
    DriverListMvpPresenter<DriverListMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DriverListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_listview);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(DriverListActivity.this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setUp();

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        driverListAdapter.clearItems();
                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        mPresenter.onViewPrepared();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
    }

    @Override
    public void openDriverSelection() {
        Intent intent = DriverSlcAvtivity.getStartIntent(DriverListActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(driverListAdapter);
        mPresenter.onViewPrepared();

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onBackPressed() {
        openDriverSelection();
    }

    @Override
    public void updateList(ArrayList<Location> constructionSiteList) {
        driverListAdapter.addItems(constructionSiteList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = DriverSlcAvtivity.getStartIntent(DriverListActivity.this);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
