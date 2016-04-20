package io.taweesoft.wonglhao.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;

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
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.managers.Load;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.models.Review;
import io.taweesoft.wonglhao.ui.adapters.ReviewAdapter;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ReviewActivity extends AppCompatActivity implements Observer {

    @Bind(R.id.imgBar) ImageView imgBar;
    @Bind(R.id.rv) RecyclerView rv;
    @Bind(R.id.fabAddReview) FloatingActionButton fabAddReview;

    private List<Review> reviewList;
    private ReviewAdapter adapter;
    private Bar bar;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);
        bar = (Bar)getIntent().getSerializableExtra("bar");
        reviewList = bar.getReviewList();
        initComponents();
    }

    public void initComponents() {
        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adapter = new ReviewAdapter(reviewList);
        rv.setAdapter(adapter);
        Glide
                .with(this)
                .load(bar.getImageUrl())
                .centerCrop()
                .crossFade()
                .into(imgBar);
        fabAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ReviewActivity.this);
                dialog.setContentView(R.layout.add_review_dialog_layout);
                Button btnReview = ButterKnife.findById(dialog , R.id.btnReview);
                final RatingBar ratingBar = ButterKnife.findById(dialog, R.id.ratingBar);
                final EditText etReview = ButterKnife.findById(dialog , R.id.etReview);
                btnReview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String review = etReview.getText().toString();
                        int star = (int)ratingBar.getRating();
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("username" , DataStorage.user.getUsername());
                        map.put("review" , review);
                        map.put("star" , star+"");
                        map.put("bar_id" , bar.getId());
                        Load load = Load.newInstance();
                        load.addObserver(ReviewActivity.this);
                        load.reviewBar(map);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @OnClick(R.id.btnBack)
    public void back() {
        finish();
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o == null) return;
        Response<Review> response = (Response<Review>)o;
        if(response.isSuccessful()){
            Review review = response.body();
            //Add review to real bar
            Bar realBar = DataStorage.barMap.get(bar.getId());
            realBar.getReviewList().add(0,review);
            reviewList.add(0,review);
            adapter.notifyDataSetChanged();
        }else{
            try {
                Toast.makeText(this, response.errorBody().string() , Toast.LENGTH_SHORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
