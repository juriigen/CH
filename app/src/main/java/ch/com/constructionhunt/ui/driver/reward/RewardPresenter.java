package ch.com.constructionhunt.ui.driver.reward;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.data.network.ApiEndPoint;
import ch.com.constructionhunt.ui.base.BasePresenter;
import ch.com.constructionhunt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.Response;

/**
 * Created by jurgen on 29.10.2018.
 */

public class RewardPresenter<V extends RewardMvpView> extends BasePresenter<V>
        implements RewardMvpPresenter<V>  {
private String points;
    @Inject
    public RewardPresenter(DataManager dataManager, SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onGetRewardClicked() {
        getMvpView().showLoading();
        JSONObject JSONObject = new JSONObject();
        try {
            JSONObject.put("username", getDataManager().getCurrentUserName());
            JSONObject.put("password", getDataManager().getPassword());

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_GET_REWARD_CLICK)
                .addJSONObjectBody(JSONObject)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        if(response.code() == 200){
                            getMvpView().showMessage("Thank you! You will receive your reward soon!");
                        }else if (response.code() == 403){
                            getMvpView().showMessage("You must have at least 100 points!");
                        }
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public void getPoints() {
        Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_GET_POINTS + "username=" + getDataManager().getCurrentUserName() + "&password=" + getDataManager().getPassword() )
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.isEmpty()){
                            getMvpView().updatePoints(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
