package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heima.jyl.redboy.R;

import java.util.List;

/**
 * Created by JING on 2016/6/15.
 */
public class SearchAdapter extends BaseAdapter {


    private Context mContext;
    private List<String> mDatas;

    public SearchAdapter(Context context, List<String> Datas) {
        mContext = context;
        this.mDatas = Datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public String getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(mContext, R.layout.fragment_tv_hot_mess, null);

        TextView item = (TextView) view.findViewById(R.id.tv_hot_item_msg);
        item.setText(mDatas.get(position));

        return view;
    }
}
