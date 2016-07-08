package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.bean.AddressBean;

import java.util.List;

/**
 * Created by ting说你跳 on 2016/6/17.
 */
public class AddressAdapter extends BaseAdapter {
    Context                           mContext;
    List<AddressBean.AddressListBean> mList;

    public AddressAdapter(Context context, List<AddressBean.AddressListBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public AddressBean.AddressListBean getItem(int position) {
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
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.address_list_item, null);
            convertView.setTag(holder);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_address_list_item);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AddressBean.AddressListBean item = getItem(position);
        String name = item.getName();
        String phoneNumber = item.getPhoneNumber();
        String address = item.getProvince().replace("0","") + "\t" + item.getCity().replace("0","") + "\t" + item.getAddressArea();
        holder.tv.setText(name + "\n" + phoneNumber + "\n" + address + "\n" + item.getAddressDetail());
        return convertView;
    }

    static class ViewHolder {
        TextView tv;
    }
}
