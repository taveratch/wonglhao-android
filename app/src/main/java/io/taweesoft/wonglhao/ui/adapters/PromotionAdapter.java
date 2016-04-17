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
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.models.Promotion;
import io.taweesoft.wonglhao.ui.views.BarActivity;

/**
 * Created by TAWEESOFT on 4/14/16 AD.
 */
public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.ViewHolder> {
    private List<Promotion> promotionList;
    private Context context;
    public PromotionAdapter(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.imgBar) public ImageView imgPromotion;
        @Bind(R.id.tvPromotionName) public TextView tvPromotionName;
        @Bind(R.id.tvBarName) public TextView tvBarName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_item_layout,null);
        final ViewHolder holder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Promotion promotion = promotionList.get(holder.getPosition());
                Bar bar = DataStorage.barMap.get(promotion.getBarId());
                Intent intent = new Intent(context , BarActivity.class);
                intent.putExtra("bar" , bar);
                if(bar == null)
                    intent.putExtra("bar_id" , promotion.getBarId());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Promotion promotion = promotionList.get(position);
        holder.tvPromotionName.setText(promotion.getName());
        holder.tvBarName.setText("@"+promotion.getBarName());
        Glide
                .with(context)
                .load(promotion.getBannerUrl())
                .centerCrop()
                .crossFade()
                .into(holder.imgPromotion);
    }

    @Override
    public int getItemCount() {
        return promotionList.size();
    }
}
