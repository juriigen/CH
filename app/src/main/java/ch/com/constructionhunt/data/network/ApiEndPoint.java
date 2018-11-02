package ch.com.constructionhunt.data.network;

import ch.com.constructionhunt.BuildConfig;

/**
 * Created by jurgen on 15.10.2018.
 */

public final class ApiEndPoint {

    public static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL + "/login?";
    public static final String ENDPOINT_SERVER_SEND_LOCATION = BuildConfig.BASE_URL + "/addLocation";
    public static final String ENDPOINT_SERVER_GET_LOCATIONS = BuildConfig.BASE_URL + "/locations?";
    public static final String ENDPOINT_SERVER_UPDATE_STATE = BuildConfig.BASE_URL + "/changeState";
    public static final String ENDPOINT_SERVER_GET_POINTS = BuildConfig.BASE_URL + "/getPoints?";
    public static final String ENDPOINT_SERVER_GET_REWARD_CLICK = BuildConfig.BASE_URL + "/getReward?";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}