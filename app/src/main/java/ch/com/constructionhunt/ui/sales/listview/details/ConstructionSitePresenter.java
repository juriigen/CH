package ch.com.constructionhunt.ui.sales.listview.details;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.data.network.ApiEndPoint;
import ch.com.constructionhunt.ui.base.BasePresenter;
import ch.com.constructionhunt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jurgen on 17.10.2018.
 */

public class ConstructionSitePresenter <V extends ConstructionSiteMvpView> extends BasePresenter<V>
        implements ConstructionSiteMvpPresenter<V>{
    @Inject
    public ConstructionSitePresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public Location getConstructionSiteDetails() {
        return getDataManager().getLocation(getMvpView().getConstructionSiteId());
    }

    @Override
    public void sendUpdatedState() {
        JSONObject locationJSON = new JSONObject();
        try {
            locationJSON.put("location_id", Long.toString(getMvpView().getConstructionSiteId()));
            locationJSON.put("state", getMvpView().getConstructionSiteState());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Rx2AndroidNetworking.put(ApiEndPoint.ENDPOINT_SERVER_UPDATE_STATE )
                .addJSONObjectBody(locationJSON)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        getMvpView().showMessage("State updated");
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("",anError.getErrorBody());
                    }
                });


    }
}