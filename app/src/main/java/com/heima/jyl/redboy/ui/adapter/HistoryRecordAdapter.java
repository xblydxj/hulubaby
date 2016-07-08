package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.bean.SearchBean;

import java.util.List;

/**
 * Created by JING on 2016/6/15.
 */
public class HistoryRecordAdapter extends BaseAdapter {


    private Context  mContext;
    private List<SearchBean.ProductListBean> mDatas;

    public HistoryRecordAdapter(Context mContext, List<SearchBean.ProductListBean> mDatas) {
        mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public SearchBean.ProductListBean getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.searchgoal_item, null);
            holder =  new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SearchBean.ProductListBean itemBean = mDatas.get(position);

        Glide.with(parent.getContext()).load(itemBean.getPic())
                .placeholder(R.drawable.bar_more_normal).into(holder.icon);
        holder.marketPrice.setText("￥"+itemBean.getMarketPrice());
        holder.name.setText(itemBean.getName());
        holder.price.setText("￥"+itemBean.getPrice());

        return convertView;
    }

    static class ViewHolder {

        public View convert;
        //int       id;
        ImageView icon;
        TextView  marketPrice;
        TextView  name;
        TextView  price;

        public ViewHolder(View convert) {
            this.convert = convert;

            this.icon = (ImageView) convert.findViewById(R.id.iv_search_goos_icon);
            this.marketPrice  = (TextView) convert.findViewById(R.id.tv_goods_search_marketprice);
            this.name = (TextView) convert.findViewById(R.id.tv_goods_search_name);
            this.price = (TextView) convert.findViewById(R.id.tv_goods_search_price);


        }
    }


}
