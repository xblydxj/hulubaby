package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.bean.SCategoryBean;

import java.util.List;

/**
 * Created by 46321 on 2016/6/16/016.
 */
public class BrandCategory2Adapter extends BaseAdapter{
    private List<SCategoryBean.CategoryBean> mData = null;
    private Context mContext;
    public BrandCategory2Adapter(Context context, List<SCategoryBean.CategoryBean> data){
        this.mData = data;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public SCategoryBean.CategoryBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.brand_item_2,null);
            viewHolder.brand_item_2_title = (TextView) convertView.findViewById(R.id.brand_item_2_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SCategoryBean.CategoryBean categoryBean = mData.get(position);
        viewHolder.brand_item_2_title.setText(categoryBean.getName());
        return convertView;
    }

    static class ViewHolder{
        TextView brand_item_2_title;
    }
}
//1466059392845,"user_id":"14660490292870",