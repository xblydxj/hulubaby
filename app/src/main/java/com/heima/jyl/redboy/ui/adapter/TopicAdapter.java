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
import com.heima.jyl.redboy.bean.STopicBean;

import java.util.ArrayList;

/**
 * Created by Jyl on 2016/6/16.
 */
public class TopicAdapter extends BaseAdapter {
    private ArrayList<STopicBean.TopicBean> mData;
    private Context mContext;

    public TopicAdapter(Context context, ArrayList<STopicBean.TopicBean> data) {
        this.mData = data;
        this.mContext = context;
    }

    @Override

    public int getCount() {
        return mData.size();
    }

    @Override
    public STopicBean.TopicBean getItem(int position) {
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
            convertView = View.inflate(parent.getContext(), R.layout.item_topic, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        STopicBean.TopicBean item = getItem(position);
        Glide.with(parent.getContext()).load(BaseUrl.basUrl + item.getPic()).placeholder(R.drawable.ic_default_adimage).into(holder.iv_item_topic);
        holder.tv_item_topic.setText(item.getName());
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView tv_item_topic;
        public ImageView iv_item_topic;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_item_topic = (TextView) rootView.findViewById(R.id.tv_item_topic);
            this.iv_item_topic = (ImageView) rootView.findViewById(R.id.iv_item_topic);
        }

    }
}
