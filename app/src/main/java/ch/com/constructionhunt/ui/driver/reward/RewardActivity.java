package ch.com.constructionhunt.ui.driver.reward;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.ui.base.BaseActivity;
import ch.com.constructionhunt.ui.driver.driverslc.DriverSlcAvtivity;

/**
 * Created by jurgen on 29.10.2018.
 */

public class RewardActivity extends BaseActivity implements RewardMvpView {

    @Inject
    RewardMvpPresenter<RewardMvpView> mPresenter;

    @BindView(R.id.points)
    TextView pointsTv;

    @BindView(R.id.btn_getreward)
    Button getRewardButton;


    @BindView(R.id.toolbarDriver)
    Toolbar mToolbar;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RewardActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(RewardActivity.this);
        setUp();
    }


    @OnClick(R.id.btn_getreward)
    void onGetRewardClicked(View v) {
        mPresenter.onGetRewardClicked();
        mPresenter.getPoints();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mPresenter.getPoints();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = DriverSlcAvtivity.getStartIntent(RewardActivity.this);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = DriverSlcAvtivity.getStartIntent(RewardActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void updatePoints(String points) {
        int min = 100;
        pointsTv.setText(points + " points");
        if (Integer.parseInt(points) < min){
            getRewardButton.setBackgroundColor(getResources().getColor(R.color.semi_gray));
        }

    }
}
