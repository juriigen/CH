package ch.com.constructionhunt.ui.sales.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.di.component.ActivityComponent;
import ch.com.constructionhunt.ui.base.BaseFragment;

/**
 * Created by jurgen on 14.10.2018.
 */

public class MapFragmentCh extends BaseFragment implements
        MapMvpView {

    private GoogleMap googleMap;

    @BindView(R.id.mapview)
    MapView mapView;

    @Inject
    MapMvpPresenter<MapMvpView> mPresenter;

    private static final String TAG = "MapFragmentCh";

    public static MapFragmentCh newInstance() {
        Bundle args = new Bundle();
        MapFragmentCh fragment = new MapFragmentCh();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mapView = view.findViewById(R.id.mapview);
            try {
                MapsInitializer.initialize(getBaseActivity());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    googleMap = mMap;

                    List<Location> locationsList = mPresenter.getLocationList();
                    for (Location location : locationsList) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(latLng).title(location.getUpdatedAt()));
                    }
                    LatLng latLng2 = new LatLng(locationsList.get(1).getLatitude(), locationsList.get(1).getLongitude());
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng2).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            });
        }
        return view;
    }

    @Override
    protected void setUp(View view) {

        mPresenter.onViewPrepared();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        mapView.onDestroy();
        super.onDestroyView();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void removeMarkers() {

    }

    @Override
    public void showMarkerAt(double latitude, double longitude) {

    }


}
