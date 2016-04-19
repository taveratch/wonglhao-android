package io.taweesoft.wonglhao.ui.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.APIService;
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.managers.HttpManager;
import io.taweesoft.wonglhao.managers.Load;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.models.Element;
import io.taweesoft.wonglhao.ui.fragments.MapFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarActivity extends AppCompatActivity implements Observer {

    @Bind(R.id.imgBar) ImageView imgBar;
    @Bind(R.id.tvBarName) TextView tvBarName;
    @Bind(R.id.tvType) TextView tvType;
    @Bind(R.id.tvDescription) TextView tvDescription;
    @Bind(R.id.ratingBar) RatingBar ratingBar;
    @Bind(R.id.tvRate) TextView tvRate;
    @Bind(R.id.fabMenu) FloatingActionMenu fabMenu;
    @Bind(R.id.fabCheckIn) FloatingActionButton fabCheckIn;
    @Bind(R.id.fabReview) FloatingActionButton fabReview;
    @Bind(R.id.fabWhoHere) FloatingActionButton fabWhoHere;

    private MapFragment map;
    private ProgressDialog dialog;
    private Bar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        ButterKnife.bind(this);
        map = (MapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        bar = ((Bar)getIntent().getSerializableExtra("bar"));
        checkBar();
    }

    public void initComponents() {
        tvBarName.setText(bar.getName());
        tvType.setText(bar.getType());
        tvDescription.setText(bar.getDescription());
        ratingBar.setRating((float)bar.getStar());
        tvRate.setText(String.format("(%d)" , bar.getRate()));
        map.initCamera(new LatLng(bar.getLat() , bar.getLon()) , bar.getName());
        // TODO: 4/16/16 AD Show placeholder in imgBar
        Glide
                .with(this)
                .load(bar.getImageUrl())
                .centerCrop()
                .crossFade()
                .into(imgBar);
        fabCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BarActivity.this);
                builder.setMessage(BarActivity.this.getString(R.string.checkInDialog) + bar.getName());
                builder.setPositiveButton("Check in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("username",DataStorage.user.getUsername());
                        map.put("bar_id" , bar.getId());
                        map.put("gender" , DataStorage.user.getGender());
                        map.put("user_id" , DataStorage.user.getId());
                        Load load = Load.newInstance();
                        load.checkIn(map);
                        Toast.makeText(BarActivity.this , "Checked in" , Toast.LENGTH_SHORT).show();
                        fabMenu.toggle(true);
                    }
                });
                builder.create().show();
            }
        });
        fabWhoHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarActivity.this , WhoHereActivity.class);
                intent.putExtra("bar" , bar);
                startActivity(intent);
            }
        });
        fabReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarActivity.this , ReviewActivity.class);
                intent.putExtra("bar" , bar);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.btnBack)
    public void back() {
        finish();
    }

    public void checkBar(){
        if(bar == null){ //Load bar from server
            dialog = ProgressDialog.show(this ,null , getString(R.string.pleaseWait));
            dialog.setCancelable(false);
            String barId = getIntent().getStringExtra("bar_id");
            Map<String, String> map = new HashMap<>();
            map.put("bar_id" , barId);
            Load load = Load.newInstance();
            load.addObserver(this);
            load.getBar(map);
        }else{
            initComponents();
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o == null) return;
        Response<Element> response = (Response<Element>)o;
        if(response.isSuccessful()){
            bar = response.body().getBarList().get(0);
            DataStorage.barMap.put(bar.getId() , bar);
            initComponents();
        }else {
            // TODO: 4/17/16 AD Handle error
            Log.e("error" , response.raw().toString() );
        }
        dialog.dismiss();
    }

}
