package ch.com.constructionhunt.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.Locale;

import ch.com.constructionhunt.R;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

/**
 * Created by jurgen on 13.10.2018.
 */

public class LocationUtils {

    private static final String TAG = "LocationUtils";
    private long UPDATE_INTERVAL = 10*1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */
    private int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static long DISPLACEMENT = 10;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;
    private static final int REQUEST_CODE_PERMISSION_LOCATION2 = 3;

    private LocationCallback mLocationCallback;

    private Activity activity;

    public LocationUtils(Activity mactivity) {
        activity = mactivity;
    }

    // Trigger new location updates at interval

public void stopFusedLocationClient(){
        getFusedLocationProviderClient(activity).removeLocationUpdates(new LocationCallback());
}
    public void getLastLocation() {

        checkLocationPermission();
        getFusedLocationProviderClient(activity).getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("LocationActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });
    }

    @TargetApi(16)
    public void requestSingleUpdate() {
        // TODO: Comment-out this line.
        Looper.prepare();
        checkLocationPermission();
        final long startTime = System.currentTimeMillis();
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                // TODO: These lines of code will run on UI thread.
                if ((locationResult.getLastLocation() != null) && (System.currentTimeMillis() <= startTime + 30 * 200)) {
                    System.out.println("LOCATION: " + locationResult.getLastLocation().getLatitude() + "|" + locationResult.getLastLocation().getLongitude());
                    System.out.println("ACCURACY: " + locationResult.getLastLocation().getAccuracy());
                    getFusedLocationProviderClient(activity).removeLocationUpdates(mLocationCallback);
                } else {
                    System.out.println("LastKnownNull? :: " + (locationResult.getLastLocation() == null));
                    System.out.println("Time over? :: " + (System.currentTimeMillis() > startTime + 30 * 200));
                }

                // TODO: After receiving location result, remove the listener.
                getFusedLocationProviderClient(activity).removeLocationUpdates(this);
            }
        };

        LocationRequest req = new LocationRequest();
        req.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        req.setFastestInterval(2000);
        req.setInterval(2000);
        // Receive location result on UI thread.
        getFusedLocationProviderClient(activity).requestLocationUpdates(req, mLocationCallback, Looper.getMainLooper());
    }

    public void onLocationChanged(Location location) {
        // New location has now been determined
        String msg = "Latitude: " + Double.toString(location.getLatitude()) +
                "  Longitude: " + Double.toString(location.getLongitude());
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    public void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSION_LOCATION);
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSION_LOCATION2);
            return;
        }
    }

    public void checkIfGpsIsEnabled() {
        LocationManager lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
            dialog.setMessage(activity.getResources().getString(R.string.gps_enable));
            dialog.setPositiveButton(activity.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    activity.startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton(activity.getString(R.string.no), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }
    }

    /**
     * this function get Longitude and Latitude coordinates and send back the real street address.
     *
     * @param LATITUDE
     * @param LONGITUDE
     * @param ctx
     * @return
     */
    @SuppressLint("LongLogTag")
    public void getCompleteAddressString(double LATITUDE, double LONGITUDE, Context ctx) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current location address", "" + strReturnedAddress.toString());
            } else {
                Log.w("My Current location address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current location address", "Can not get Address!");
        }
        Toast.makeText(activity, strAdd, Toast.LENGTH_SHORT).show();
    }

    /**
     * this function convert real address to geographical coordinates.
     *
     * @param strAddress -real address
     * @return LatLng object which contain the coordinates
     */
    public LatLng getLocationFromAddress(String strAddress) {
        Geocoder coder = new Geocoder(activity);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
            Log.d("coordinates", p1.latitude + "" + p1.longitude);

        } catch (Exception ex) {
            Log.d("Location Exception", "error converting address");
            ex.printStackTrace();
        }

        return p1;
    }
}
