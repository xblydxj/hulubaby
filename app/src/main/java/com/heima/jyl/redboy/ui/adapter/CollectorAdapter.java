package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.BaseUrl;
import com.heima.jyl.redboy.bean.FavoritesBean;

import java.util.List;

/**
 * Created by ting说你跳 on 2016/6/15.
 */
public class CollectorAdapter extends BaseAdapter{
    Context             mContext;
    List<FavoritesBean.ProductListBean> mList;

    public CollectorAdapter(Context context, List<FavoritesBean.ProductListBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public FavoritesBean.ProductListBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.collector_list_item, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.img = (ImageView) convertView.findViewById(R.id.img_collector_list_item);
            holder.tv_name = (TextView) convertView.findViewById(R.id.name_collector_list_item);
            holder.tv_price = (TextView) convertView.findViewById(R.id.price_collector_list_item);
            holder.tv_marketPrice = (TextView) convertView.findViewById(R.id.marketPrice_collector_list_item);
            holder.tv_num = (TextView) convertView.findViewById(R.id.num_collector_list_item);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        FavoritesBean.ProductListBean item = getItem(position);
        Glide.with(mContext).load(BaseUrl.basUrl + item.getPic()).placeholder(R.drawable.ic_default_adimage).into(holder.img);
        holder.tv_name.setText(item.getName());
        holder.tv_marketPrice.setText("￥"+item.getMarketPrice());
        holder.tv_price.setText("￥"+item.getPrice());
        holder.tv_num.setText("已有"+item.getCommentCount()+"人评价");
        Log.d("tag", holder.tv_name.getText().toString().trim());
        return convertView;
    }

    static class ViewHolder{
        ImageView img;
        TextView tv_name;
        TextView tv_price;
        TextView tv_marketPrice;
        TextView tv_num;
    }
}
