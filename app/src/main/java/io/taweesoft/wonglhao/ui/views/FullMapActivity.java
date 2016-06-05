package io.taweesoft.wonglhao.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.ui.fragments.MapFragment;

public class FullMapActivity extends AppCompatActivity {

    private MapFragment map;
    private Bar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_map);
        map = (MapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        bar = (Bar)getIntent().getSerializableExtra("bar");
        initComponents();
    }

    public void initComponents() {
        map.setIsViewOnCurrentLocation(false);
        LatLng latLng = new LatLng( bar.getLat(), bar.getLon() );
        map.onMapClick( latLng, bar.getName() );
        map.initCamera( latLng,bar.getName() );
    }
}
