package ch.com.constructionhunt.ui.sales.listview;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

public class SalesListPresenter<V extends SalesListMvpView> extends BasePresenter<V>
        implements SalesListMvpPresenter<V> {
private ArrayList<Location> constructionSitesList;
    @Inject
    public SalesListPresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
       // getMvpView().showLoading();
        Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_GET_LOCATIONS + "username=" + getDataManager().getCurrentUserName() + "&password=" + getDataManager().getPassword())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {
                            constructionSitesList = new ArrayList<>();
                            getDataManager().clearLocationDb();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject locationJSONObject = response.getJSONObject(i);

                                    Location location = new Location(locationJSONObject.getLong("location_id"), null, locationJSONObject.getDouble("location_longitude"), locationJSONObject.getDouble("location_latitude"), locationJSONObject.getString("state"), locationJSONObject.getString("created_at"), locationJSONObject.getString("city"));
                                    getDataManager().saveLocation(location);
                                    constructionSitesList.add(location);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                           // List<Location> constructionSitesList = getDataManager().getAllLocations();
                            getMvpView().updateList(constructionSitesList);


                        }
                        //getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
