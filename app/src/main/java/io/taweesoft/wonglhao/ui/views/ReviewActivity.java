package io.taweesoft.wonglhao.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.models.Review;
import io.taweesoft.wonglhao.ui.adapters.ReviewAdapter;

public class ReviewActivity extends AppCompatActivity {

    @Bind(R.id.imgBar) ImageView imgBar;
    @Bind(R.id.rv) RecyclerView rv;

    private List<Review> reviewList;
    private Bar bar;

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
        ReviewAdapter adapter = new ReviewAdapter(reviewList);
        rv.setAdapter(adapter);
        Glide
                .with(this)
                .load(bar.getImageUrl())
                .centerCrop()
                .crossFade()
                .into(imgBar);
    }
}
