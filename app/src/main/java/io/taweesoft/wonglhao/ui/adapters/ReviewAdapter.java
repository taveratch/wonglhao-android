package io.taweesoft.wonglhao.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.models.Review;

/**
 * Created by TAWEESOFT on 4/19/16 AD.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> reviewList;
    private Context context;

    public ReviewAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvReview) public TextView tvReview;
        @Bind(R.id.tvUsername) public TextView tvUsername;
        @Bind(R.id.tvDate) public TextView tvDate;
        @Bind(R.id.ratingBar) public RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.review_item_layout , null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Review review = reviewList.get(position);
        DateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        holder.tvReview.setText(review.getText());
        holder.tvUsername.setText(review.getUsername());
        holder.tvDate.setText(format.format(review.getDate()));
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }
}
