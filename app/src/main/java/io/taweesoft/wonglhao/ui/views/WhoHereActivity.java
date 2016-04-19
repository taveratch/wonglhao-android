package io.taweesoft.wonglhao.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.Load;
import io.taweesoft.wonglhao.managers.Utility;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.models.Element;
import io.taweesoft.wonglhao.models.Recent;
import io.taweesoft.wonglhao.ui.adapters.WhoHereAdapter;
import retrofit2.Response;

public class WhoHereActivity extends AppCompatActivity implements Observer{

    @Bind(R.id.imgBar) ImageView imgBar;
    @Bind(R.id.rv) RecyclerView rv;

    private List<Recent> recentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_here);
        ButterKnife.bind(this);
        Load load = Load.newInstance();
        load.addObserver(this);
        Bar bar = ((Bar)getIntent().getSerializableExtra("bar"));
        Map<String,String> map = new HashMap<>();
        map.put("bar_id" , bar.getId());
        load.getCheckedIn(map);
        Glide
                .with(this)
                .load(bar.getImageUrl())
                .centerCrop()
                .crossFade()
                .into(imgBar);
    }

    public void initComponents() {
        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        WhoHereAdapter adapter = new WhoHereAdapter(recentList);
        rv.setAdapter(adapter);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o == null) return;
        Response<Element> response = (Response<Element>)o;
        if(response.isSuccessful()) {
            recentList = response.body().getRecentList();
            initComponents();
        }else{
            try {
                Utility.showToastDialog(this,response.errorBody().string(),false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btnBack)
    public void back() {
        finish();
    }
}
