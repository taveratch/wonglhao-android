package io.taweesoft.wonglhao.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.ui.views.BarActivity;

/**
 * Created by TAWEESOFT on 4/15/16 AD.
 */
public class BarAdapter extends RecyclerView.Adapter<BarAdapter.ViewHolder>{

    private Context context;
    private List<Bar> barList;

    public BarAdapter(List<Bar> barList) {
        this.barList = barList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgBar) public ImageView imgBar;
        @Bind(R.id.tvBarName) public TextView tvBarName;
        @Bind(R.id.tvPromotion) public TextView tvPromotion;
        @Bind(R.id.tvCheckedIn) public TextView tvCheckedIn;
        @Bind(R.id.tvReview) public TextView tvReview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bar_item_layout , null);
        final ViewHolder holder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bar bar = barList.get(holder.getPosition());
                Intent intent = new Intent(context , BarActivity.class);
                intent.putExtra("bar" , bar);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bar bar = barList.get(position);
        holder.tvBarName.setText(bar.getName());
        holder.tvPromotion.setText(bar.getPromotionList().size() + " " + context.getString(R.string.promotion).toLowerCase());
        holder.tvCheckedIn.setText(bar.getChecked_in().size() + " " + context.getString(R.string.peopleWereHere).toLowerCase());
        holder.tvReview.setText(bar.getReviewList().size() + " " + context.getString(R.string.reviews).toLowerCase());
        Glide
                .with(context)
                .load(bar.getBannerUrl())
                .centerCrop()
                .crossFade()
                .into(holder.imgBar);
    }

    @Override
    public int getItemCount() {
        return barList.size();
    }
}
