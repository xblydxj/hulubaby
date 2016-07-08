package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.BaseUrl;
import com.heima.jyl.redboy.bean.NewProductBean;

import java.util.ArrayList;

/**
 * Created by Jyl on 2016/6/16.
 */
public class NewProductAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<NewProductBean.ProductListBean> mData;

    public NewProductAdapter(Context context, ArrayList<NewProductBean.ProductListBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public NewProductBean.ProductListBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_newproduct, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewProductBean.ProductListBean item = getItem(position);
        Glide.with(parent.getContext()).load(BaseUrl.basUrl + item.getPic()).into(holder.iv_item_newproduct);
        holder.tv_item_newproduct_name.setText(item.getName());
        holder.tv_item_newproduct_primecost.setText("原价 : "+item.getMarketPrice());
        holder.tv_item_nowprice.setText("￥"+item.getPrice());
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView iv_item_newproduct;
        public TextView tv_item_newproduct_name;
        public TextView tv_item_newproduct_primecost;
        public TextView tv_item_nowprice;
        public TextView tv_item_newproduct_comment;
        public TextView tv_item_newproduct_buy;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_item_newproduct = (ImageView) rootView.findViewById(R.id.iv_item_newproduct);
            this.tv_item_newproduct_name = (TextView) rootView.findViewById(R.id.tv_item_newproduct_name);
            this.tv_item_newproduct_primecost = (TextView) rootView.findViewById(R.id.tv_item_newproduct_primecost);
            this.tv_item_nowprice = (TextView) rootView.findViewById(R.id.tv_item_nowprice);
            this.tv_item_newproduct_comment = (TextView) rootView.findViewById(R.id.tv_item_newproduct_comment);
            this.tv_item_newproduct_buy = (TextView) rootView.findViewById(R.id.tv_item_newproduct_buy);
        }

    }
}
