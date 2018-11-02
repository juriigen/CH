package ch.com.constructionhunt.ui.sales.listview.details;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.di.component.ActivityComponent;
import ch.com.constructionhunt.ui.base.BaseFragment;

/**
 * Created by jurgen on 17.10.2018.
 */

public class ConstructionSiteFragment extends BaseFragment implements
        ConstructionSiteMvpView {
    public static final String TAG = "ConstructionSiteFragment";
    private long mid;
    private String newState;
    @Inject
    ConstructionSiteMvpPresenter<ConstructionSiteMvpView> mPresenter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.city_tv)
    TextView cityTextView;

    @BindView(R.id.longitude_tv)
    TextView longitudeTextView;

    @BindView(R.id.latitude_tv)
    TextView latitudeTextView;

    @BindView(R.id.states_spinner)
    Spinner statesSpinner;

    public static ConstructionSiteFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong("id", id);
        ConstructionSiteFragment fragment = new ConstructionSiteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cs_detail, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            mid = getArguments().getLong("id");
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        final Location location = mPresenter.getConstructionSiteDetails();
        cityTextView.setText(getCompleteAddressString(location.getLatitude(), location.getLongitude()));
        String currentString = location.getCreatedAt();
        String[] separated = currentString.split(" ");
        longitudeTextView.setText(separated[0]);
        latitudeTextView.setText("Date");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getBaseActivity(),
                R.array.state_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statesSpinner.setAdapter(adapter);
        int selectedItem = adapter.getPosition(location.getState());
        statesSpinner.setSelection(selectedItem);
        statesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newState = parent.getItemAtPosition(position).toString();
                if(!location.getState().equals(newState)){
                    mPresenter.sendUpdatedState();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public long getConstructionSiteId() {
        return mid;
    }

    @Override
    public String getConstructionSiteState() {
        return newState;
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getBaseActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("Frag", strReturnedAddress.toString());
            } else {
                Log.w("Frag", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("Frag", "Cannot get Address!");
        }
        return strAdd;
    }
}
