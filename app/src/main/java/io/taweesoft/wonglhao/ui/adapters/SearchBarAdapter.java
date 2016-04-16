package io.taweesoft.wonglhao.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.ui.views.BarActivity;

/**
 * Created by TAWEESOFT on 4/16/16 AD.
 */
public class SearchBarAdapter extends RecyclerView.Adapter<SearchBarAdapter.ViewHolder> {

    private Context context;
    private List<Bar> barList;

    public SearchBarAdapter(List<Bar> barList) {
        this.barList = barList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvName) public TextView tvName;
        @Bind(R.id.tvCheckedIn) public TextView tvCheckedIn;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.search_item_layout,null);
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getPosition();
                Bar bar = barList.get(position);
                Intent intent = new Intent(context, BarActivity.class);
                intent.putExtra("bar" , bar);
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bar bar = barList.get(position);
        holder.tvName.setText(bar.getName());
        holder.tvCheckedIn.setText(bar.getChecked_in().size() + " " + context.getString(R.string.peopleWereHere));
    }

    @Override
    public int getItemCount() {
        return barList.size();
    }
}
