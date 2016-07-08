package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.BaseUrl;
import com.heima.jyl.redboy.bean.CarBean;

import java.util.List;

/**
 * @创建者 种金币
 * @创建时间 2016/6/18 15:55
 * @描述 结算中心Adapter
 */
public class CenterAdapter extends BaseAdapter {
    public Context                mContext;
    public List<CarBean.CartBean> mdatas;

    public CenterAdapter(Context context, List<CarBean.CartBean> mdatas) {
        mContext = context;
        this.mdatas = mdatas;
    }

    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.shop_item_message, null);
            holder.tvProductName = (TextView) convertView
                    .findViewById(R.id.item_productName);
            holder.ivProductImageUrl = (ImageView) convertView
                    .findViewById(R.id.shop_item_productImage);
            holder.tvPnum = (TextView) convertView
                    .findViewById(R.id.shop_item_num);
            holder.tvProductPrice = (TextView) convertView
                    .findViewById(R.id.item_price);
            holder.tvPriceSum = (TextView) convertView
                    .findViewById(R.id.item_sum);
            holder.flCheck = (FrameLayout) convertView
                    .findViewById(R.id.shop_item_fl);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.flCheck.setVisibility(View.INVISIBLE);
        CarBean.CartBean data = mdatas.get(position);
        holder.tvProductName.setText(data.getProductName());
        Glide.with(mContext)
                .load(BaseUrl.basUrl + data.getProductImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivProductImageUrl);
        holder.tvProductPrice.setText(data.getProductPrice());
        holder.tvPnum.setText(data.getPnum());
        int sum = Integer.valueOf(data.getPnum()) * Integer.valueOf(data.getProductPrice());
        holder.tvPriceSum.setText(sum + "");
        return convertView;
    }

    static class ViewHolder {
        ImageView   ivProductImageUrl;
        TextView    tvPnum;
        TextView    tvProductName;
        TextView    tvProductPrice;
        TextView    tvPriceSum;
        FrameLayout flCheck;
    }
}
