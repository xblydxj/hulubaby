package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.bean.AreaBean;

import java.util.List;

/**
 * Created by Jyl on 2016/6/11.
 */
public class AreaAdapter extends BaseAdapter {
    private Context mContext;
    private List<AreaBean> mData;

    public AreaAdapter(Context context, List<AreaBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public AreaBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AreaBean bean = getItem(position);
        if (convertView == null) {
            //第三个参数为false才会不会忽略父节点的参数
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.item_tv);
        tv.setText(bean.name);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundResource(bean.isChecked?R.mipmap.choose_item_selected:R.mipmap.choose_item_normal);
        return convertView;
    }
}
