package io.taweesoft.wonglhao.ui.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.Load;
import io.taweesoft.wonglhao.managers.MyObservable;
import io.taweesoft.wonglhao.models.Element;
import io.taweesoft.wonglhao.models.Nearby;
import io.taweesoft.wonglhao.models.Observable;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 4/14/16 AD.
 */
public class NearByFragment extends Fragment implements MyObservable , Observer , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Observable observable = new Observable();
    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    private static View view;

    @Bind(R.id.toolbar) Toolbar toolbar;
    private MapFragment map;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null)
            view = inflater.inflate(R.layout.nearby_fragment_layout,container, false);
        ButterKnife.bind(this,view);
        initComponents();
        return view;
    }

    public void initComponents() {
        toolbar.setTitle(getString(R.string.nearBy));
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.setChanged();
                observable.notifyObservers();
            }
        });
        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
        map = (MapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        map.setIsViewOnCurrentLocation(true);
    }

    @Override
    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }

    @Override
    public void update(java.util.Observable observable, Object o) {
        if(o == null) return;
        Response<Element> response = (Response<Element>)o;
        if(response.isSuccessful()){
            List<Nearby> nearbyList = response.body().getNearbyList();
            for(Nearby nearby : nearbyList)
                map.onMapClick(new LatLng(nearby.getLat() , nearby.getLon()) , nearby.getName());
        }else{
            try {
                Toast.makeText(this.getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        Map<String,String> mapHash = new HashMap<>();
        mapHash.put("lat" , mCurrentLocation.getLatitude()+"");
        mapHash.put("long" , mCurrentLocation.getLongitude()+"");
        Load load = Load.newInstance();
        load.addObserver(this);
        load.getNearBy(mapHash);
        Log.e("RRR" , "123");
    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



}
