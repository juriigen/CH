package ch.com.constructionhunt.ui.driver.reward;

import ch.com.constructionhunt.ui.base.MvpPresenter;

/**
 * Created by jurgen on 29.10.2018.
 */

public interface RewardMvpPresenter<V extends RewardMvpView> extends MvpPresenter<V> {
    void onGetRewardClicked();
    void getPoints();
}
