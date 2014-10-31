package com.xjy.work;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xjy.work.utils.ViewHolderUtil;

import java.util.List;
import java.util.Map;

public class GridViewAdapter extends BaseAdapter {


    private List<Map<String, Object>> mMapList;
    private Context context;

    public GridViewAdapter(List<Map<String, Object>> list, Context context) {
        mMapList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mMapList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.result_list_item, null);
        }

        ImageView image1 = ViewHolderUtil.get(convertView, R.id.image1);
        ImageView image2 = ViewHolderUtil.get(convertView, R.id.image2);
        TextView textView = ViewHolderUtil.get(convertView, R.id.text);
        TextView idTextView = ViewHolderUtil.get(convertView, R.id.id);

        idTextView.setText(mMapList.get(position).get("_id").toString());
        textView.setText(mMapList.get(position).get("save_text").toString());
        image1.setImageResource(((Integer) mMapList.get(position).get("save_image1")));
        image2.setImageResource(((Integer) mMapList.get(position).get("save_image2")));
        return convertView;
    }
}
