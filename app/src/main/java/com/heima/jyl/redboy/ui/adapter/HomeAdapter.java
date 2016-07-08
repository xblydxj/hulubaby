package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.bean.HomeCategoryBean;

import java.util.ArrayList;

/**
 * Created by Jyl on 2016/6/15.
 */
public class HomeAdapter extends BaseAdapter {

    private LayoutInflater  inflater;
    private ArrayList<HomeCategoryBean> categroy;

    public HomeAdapter(Context context, ArrayList<HomeCategoryBean> categroy) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.categroy = categroy;
    }

    @Override
    public int getCount() {
        return categroy.size();
    }


    @Override
    public Object getItem(int position) {
        return categroy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        HomeCategoryBean item = categroy.get(position);
        if (convertView == null)
            view = inflater.inflate(R.layout.home_item, null);
        else
            view = convertView;
        ((TextView) view.findViewById(R.id.textContent)).setText(item.getTitle());
        ((ImageView) view.findViewById(R.id.imgIcon)).setBackgroundResource(item.getImgresid());
        return view;
    }


}
