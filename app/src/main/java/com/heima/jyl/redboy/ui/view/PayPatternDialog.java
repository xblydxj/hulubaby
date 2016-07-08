package com.heima.jyl.redboy.ui.view;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.heima.jyl.redboy.R;

import java.util.ArrayList;
import java.util.List;


public class PayPatternDialog extends Dialog implements AdapterView.OnItemClickListener {


    private ArrayList<String> mDatas;
    private String pattern;
    private ListView lvPay;

    /**
     * 自定义Dialog监听器
     */
    public interface PriorityListener {
        /**
         * 回调函数，用于在Dialog的监听事件触发后刷新Activity的UI显示
         */
        void refreshPriorityUI(String string,int position);
    }

    private PriorityListener listener;

    /**
     * 带监听器参数的构造函数
     */

    public PayPatternDialog(Context context, PriorityListener priorityListener) {
        // 设置Dialog样式
        super(context, R.style.AddressDialogStyle);
        // 修改Dialog的Window的LayoutParams
        Window window = getWindow();
        LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 修改好要设置上去 否则不生效
        window.setAttributes(params);
        this.listener = priorityListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay_pattern);
        lvPay = (ListView) findViewById(R.id.lv_pay);
        lvPay.setOnItemClickListener(this);
        initData();
    }


    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add("在线支付");
        mDatas.add("货到付款");
        lvPay.setAdapter(new DiaglogAdapter(getContext(),mDatas));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pattern = mDatas.get(position);
        listener.refreshPriorityUI(pattern, position + 1);
        dismiss();
    }

    class DiaglogAdapter extends BaseAdapter{
        private Context mContext;
        private List<String> mData;
        public DiaglogAdapter(Context context, List<String> data){
            mContext = context;
            mData = data;
        }
        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                //第三个参数为false才会不会忽略父节点的参数
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_dialog, parent, false);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.tv_item_dialog);
            tv.setText(getItem(position));
            tv.setTextColor(Color.BLACK);
            return convertView;
        }
    }

}
