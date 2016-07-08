package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.bean.OrderBean;

import java.util.List;

/**
 * Created by ting说你跳 on 2016/6/18.
 */
public class OrderListAdapter extends BaseAdapter {
    Context                       mContext;
    List<OrderBean.OrderListBean> mList;

    public OrderListAdapter(Context context, List<OrderBean.OrderListBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public OrderBean.OrderListBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewhodler holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.order_list_item, null);
            holder = new Viewhodler();
            convertView.setTag(holder);
            holder.tv_num = (TextView) convertView.findViewById(R.id.num_order_list_item);
            holder.tv_price = (TextView) convertView.findViewById(R.id.price_order_list_item);
            holder.tv_state = (TextView) convertView.findViewById(R.id.state_order_list_item);
            holder.tv_date = (TextView) convertView.findViewById(R.id.date_order_list_item);
        } else {
            holder = (Viewhodler) convertView.getTag();
        }
        OrderBean.OrderListBean item = getItem(position);
        String state = "";
        switch (item.getState()) {
            case 0:
                state = "未完成";
                break;
            case 1:
                state = "已完成";
                break;
            case 2:
                state = "已取消";
                break;
        }
        holder.tv_num.setText(item.getOrderid());
        holder.tv_state.setText(state);
        holder.tv_date.setText(item.getTime()+"");
        holder.tv_price.setText(item.getPrice()+"");
        return convertView;
    }

    static class Viewhodler {
        TextView tv_num;
        TextView tv_price;
        TextView tv_state;
        TextView tv_date;
    }
}
