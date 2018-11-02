package ch.com.constructionhunt.ui.sales.listview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.di.component.ActivityComponent;
import ch.com.constructionhunt.ui.base.BaseFragment;
import ch.com.constructionhunt.ui.sales.listview.details.ConstructionSiteFragment;
import ch.com.constructionhunt.utils.DetailClickListener;
import ch.com.constructionhunt.utils.FragmentUtil;

/**
 * Created by jurgen on 17.10.2018.
 */

public class SalesListFragment extends BaseFragment implements
        SalesListMvpView {

    private static final String TAG = "SalesListFragment";

    @Inject
    SalesListMvpPresenter<SalesListMvpView> mPresenter;

    @Inject
    SalesListAdapter msalesListAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.sales_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    public static SalesListFragment newInstance() {
        Bundle args = new Bundle();
        SalesListFragment fragment = new SalesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saleslist, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            msalesListAdapter.setDetailClickListener(new DetailClickListener() {
                @Override
                public void onClick(View view, long id) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.feed_frame_layout, ConstructionSiteFragment.newInstance(id), ConstructionSiteFragment.TAG)
                            .addToBackStack(null)
                            .commit();
                }
            });

            swipeRefreshLayout.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            msalesListAdapter.clearItems();
                            Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
                            // This method performs the actual data-refresh operation.
                            // The method calls setRefreshing(false) when it's finished.
                            mPresenter.onViewPrepared();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
            );
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(msalesListAdapter);
        mPresenter.onViewPrepared();
    }


    @Override
    public void updateList(ArrayList<Location> locationList) {
        msalesListAdapter.addItems(locationList);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

}