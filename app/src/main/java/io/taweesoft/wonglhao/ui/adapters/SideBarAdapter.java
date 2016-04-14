package io.taweesoft.wonglhao.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.models.SideBarItem;

/**
 * Created by TAWEESOFT on 4/14/16 AD.
 */
public class SideBarAdapter extends ArrayAdapter<SideBarItem> {

    public SideBarAdapter(Context context, int resource, List<SideBarItem> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.left_menu_item_layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder)convertView.getTag();

        SideBarItem item = getItem(position);
        holder.imgIcon.setImageResource(item.getImg());
        holder.tvItemName.setText(item.getText());
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.imgIcon) public ImageView imgIcon;
        @Bind(R.id.tvItemName) public TextView tvItemName;

        public ViewHolder(View v) {
            ButterKnife.bind(this,v);
        }
    }
}
