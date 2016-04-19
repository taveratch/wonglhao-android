package io.taweesoft.wonglhao.ui.views;

import android.app.Dialog;
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

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.models.Review;
import io.taweesoft.wonglhao.ui.adapters.ReviewAdapter;

public class ReviewActivity extends AppCompatActivity {

    @Bind(R.id.imgBar) ImageView imgBar;
    @Bind(R.id.rv) RecyclerView rv;
    @Bind(R.id.fabAddReview) FloatingActionButton fabAddReview;

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
        fabAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ReviewActivity.this);
                dialog.setContentView(R.layout.add_review_dialog_layout);
                Button btnReview = ButterKnife.findById(dialog , R.id.btnReview);
                final RatingBar ratingBar = ButterKnife.findById(dialog, R.id.ratingBar);
                final EditText etReview = ButterKnife.findById(dialog , R.id.etReview);
                btnReview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String review = etReview.getText().toString();
                        int star = (int)ratingBar.getRating();
                        
                    }
                });
            }
        });
    }

    @OnClick(R.id.btnBack)
    public void back() {
        finish();
    }
}
