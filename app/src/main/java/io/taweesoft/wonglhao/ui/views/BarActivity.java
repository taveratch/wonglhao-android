package io.taweesoft.wonglhao.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.models.Bar;

public class BarActivity extends AppCompatActivity {

    @Bind(R.id.imgBar) ImageView imgBar;
    @Bind(R.id.tvBarName) TextView tvBarName;
    @Bind(R.id.tvType) TextView tvType;
    @Bind(R.id.tvDescription) TextView tvDescription;
    @Bind(R.id.ratingBar) RatingBar ratingBar;
    @Bind(R.id.tvRate) TextView tvRate;

    private Bar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        ButterKnife.bind(this);
        String id = ((Bar)getIntent().getSerializableExtra("bar")).getId();
        bar = DataStorage.barMap.get(id);
        initComponents();
    }

    public void initComponents() {
        tvBarName.setText(bar.getName());
        tvType.setText(bar.getType());
        tvDescription.setText(bar.getDescription());
        ratingBar.setRating((float)bar.getStar());
        tvRate.setText(String.format("(%d)" , bar.getRate()));
        // TODO: 4/16/16 AD Show placeholder in imgBar
        Glide
                .with(this)
                .load(bar.getImageUrl())
                .centerCrop()
                .crossFade()
                .into(imgBar);
    }

    @OnClick(R.id.btnBack)
    public void back() {
        finish();
    }
}
