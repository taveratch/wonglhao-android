package io.taweesoft.wonglhao.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.models.Recent;

/**
 * Created by TAWEESOFT on 4/19/16 AD.
 */
public class WhoHereAdapter extends RecyclerView.Adapter<WhoHereAdapter.ViewHolder> {

    private Context context;
    private List<Recent> recentList;

    public WhoHereAdapter(List<Recent> recentList) {
        this.recentList = recentList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgGender) public ImageView imgGender;
        @Bind(R.id.tvUsername) public TextView tvUsername;
        @Bind(R.id.tvTime) public TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.who_here_item_layout , null);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recent recent = recentList.get(position);
        DateFormat format = new SimpleDateFormat("dd MMM yyyy" , Locale.getDefault());
        holder.tvTime.setText(format.format(recent.getTime()));
        if(recent.getGender().equals(context.getString(R.string.male)))
            holder.imgGender.setImageResource(R.drawable.man);
        else
            holder.imgGender.setImageResource(R.drawable.women);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
