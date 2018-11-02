package ch.com.constructionhunt.ui.driver.driverlistview;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import ch.com.constructionhunt.data.DataManager;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.data.network.ApiEndPoint;
import ch.com.constructionhunt.ui.base.BasePresenter;
import ch.com.constructionhunt.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jurgen on 29.10.2018.
 */

public class DriverListPresenter<V extends DriverListMvpView> extends BasePresenter<V>
        implements DriverListMvpPresenter<V> {
    private ArrayList<Location> constructionSitesList;

    @Inject
    public DriverListPresenter(DataManager dataManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_GET_LOCATIONS + "username=" + getDataManager().getCurrentUserName() + "&password=" + getDataManager().getPassword())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {
                            constructionSitesList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject locationJSONObject = response.getJSONObject(i);

                                    Location location = new Location(locationJSONObject.getLong("location_id"), null, locationJSONObject.getDouble("location_longitude"), locationJSONObject.getDouble("location_latitude"), locationJSONObject.getString("state"), locationJSONObject.getString("created_at"), locationJSONObject.getString("city"));
                                    constructionSitesList.add(location);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            // List<Location> constructionSitesList = getDataManager().getAllLocations();
                            getMvpView().updateList(constructionSitesList);


                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
}
}
