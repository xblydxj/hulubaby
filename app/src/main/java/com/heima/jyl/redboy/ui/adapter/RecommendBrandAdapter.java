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
import com.heima.jyl.redboy.bean.SBrandBean;

import java.util.ArrayList;

/**
 * Created by JING on 2016/6/15.
 */
public class RecommendBrandAdapter extends BaseAdapter {

        private Context                               mContext;
        private ArrayList<SBrandBean.BrandBean.ValueBean> mDatas;

        public RecommendBrandAdapter(Context mContext, ArrayList<SBrandBean.BrandBean.ValueBean> mDatas) {
            mContext = mContext;
            this.mDatas = mDatas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public SBrandBean.BrandBean.ValueBean getItem(int position) {
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
                convertView = View.inflate(parent.getContext(), R.layout.gd_view_item, null);
                holder =  new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            SBrandBean.BrandBean.ValueBean item_value = mDatas.get(position);

            Glide.with(parent.getContext()).load(BaseUrl.basUrl + item_value.getPic())
                    .placeholder(R.drawable.bar_more_normal).into(holder.icon);
            holder.name.setText(item_value.getName());

            return convertView;
        }

        static class ViewHolder {

            public View convert;
            ImageView icon;

            TextView  name;


            public ViewHolder(View convert) {
                this.convert = convert;
                this.icon = (ImageView) convert.findViewById(R.id.iv_img_brand);

                this.name = (TextView) convert.findViewById(R.id.tv_msg_prec);


            }
        }


    }
