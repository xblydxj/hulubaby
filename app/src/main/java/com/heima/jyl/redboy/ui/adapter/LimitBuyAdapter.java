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
import com.heima.jyl.redboy.bean.LimitBuyBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jyl on 2016/6/15.
 */
public class LimitBuyAdapter extends BaseAdapter{
    private ArrayList<LimitBuyBean.LimitbuyListBean> mData;
    private Context mContext;
    private long leftTime;
    private Thread mThread;
    int result = 0;

    public LimitBuyAdapter(Context context, ArrayList<LimitBuyBean.LimitbuyListBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public LimitBuyBean.LimitbuyListBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_limitbuy, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        LimitBuyBean.LimitbuyListBean item = getItem(position);
        Glide.with(parent.getContext()).load(BaseUrl.basUrl + item.getPic()).placeholder(R.drawable.ic_default_adimage).into(holder.item_limitbuy_img);
        holder.item_limitbuy_name.setText(item.getName());
        holder.item_limitbuy_limitprice.setText("￥" + item.getLimitPrice());
        holder.item_limitbuy_price.setText("￥" + item.getPrice());
        leftTime = Long.parseLong(item.getLeftTime());
        holder.item_limitbuy_lefttime.setText("剩余时间 : " + new SimpleDateFormat("HH : mm : ss").format(new Date(leftTime)));
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView item_limitbuy_img;
        public TextView item_limitbuy_name;
        public TextView item_limitbuy_price;
        public TextView item_limitbuy_textlimitprice;
        public TextView item_limitbuy_limitprice;
        public TextView item_limitbuy_lefttime;
        public TextView item_limitbuy_buynow;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_limitbuy_img = (ImageView) rootView.findViewById(R.id.iv_item_newproduct);
            this.item_limitbuy_name = (TextView) rootView.findViewById(R.id.tv_item_newproduct_name);
            this.item_limitbuy_price = (TextView) rootView.findViewById(R.id.tv_item_newproduct_primecost);
            this.item_limitbuy_textlimitprice = (TextView) rootView.findViewById(R.id.item_limitbuy_textlimitprice);
            this.item_limitbuy_limitprice = (TextView) rootView.findViewById(R.id.tv_item_nowprice);
            this.item_limitbuy_lefttime = (TextView) rootView.findViewById(R.id.item_limitbuy_lefttime);
            this.item_limitbuy_buynow = (TextView) rootView.findViewById(R.id.tv_item_newproduct_buy);
        }

    }

    public void start() {
        mThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (mData == null || result == mData.size()) {
                            break;
                        }
                        sleep(1000);
                        for (LimitBuyBean.LimitbuyListBean bean : mData) {
                            bean.setLeftTime(String.valueOf(Integer.parseInt(bean.getLeftTime()) - 1000));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mThread.start();
    }
}
