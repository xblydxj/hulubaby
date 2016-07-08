package com.heima.jyl.redboy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.BaseUrl;
import com.heima.jyl.redboy.api.OrderApi;
import com.heima.jyl.redboy.bean.OrderBean;
import com.heima.jyl.redboy.ui.adapter.OrderListAdapter;
import com.heima.jyl.redboy.utils.SPUtils;

import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ting说你跳 on 2016/6/16.
 */
public class OrderActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button mBtnOrderAgo;
    private Button mBtnOrderUndone;
    private Button mBtnOrderCancel;
    private TextView mTvOrder;
    private ListView mLvOrder;
    private int mType;
    private OrderListAdapter mAdapter;
    private List<OrderBean.OrderListBean> mList;

    private String USER_ID;
    private String TOKEN;
    public static final int REQUEST_CODE = 102;
    public int mPreState = 0;
    public int mNowState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        initData();
        setListener();
    }

    private void initView() {

        mBtnOrderAgo = (Button) findViewById(R.id.btn_order_done);
        mBtnOrderUndone = (Button) findViewById(R.id.btn_order_undone);
        mBtnOrderCancel = (Button) findViewById(R.id.btn_order_cancel);
        mTvOrder = (TextView) findViewById(R.id.tv_order);
        mLvOrder = (ListView) findViewById(R.id.lv_order);
    }

    private void setListener() {
        mBtnOrderAgo.setOnClickListener(this);
        mBtnOrderUndone.setOnClickListener(this);
        mBtnOrderCancel.setOnClickListener(this);

        mLvOrder.setOnItemClickListener(this);
    }

    private void initData() {
        getUser();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl.basUrl).addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(OrderApi.class).getList(mType, TOKEN, USER_ID, 1, 10).enqueue(new Callback<OrderBean>() {
            @Override
            public void onResponse(Response<OrderBean> response, Retrofit retrofit) {
                OrderBean body = response.body();
                if (body.error == null) {
                    mList = body.getOrderList();
                    if (mList.size() != 0) {
                        mTvOrder.setVisibility(View.INVISIBLE);
                        mAdapter = new OrderListAdapter(getApplicationContext(), mList);
                        mLvOrder.setAdapter(mAdapter);
                        mLvOrder.setVisibility(View.VISIBLE);
                    } else {
                        mTvOrder.setVisibility(View.VISIBLE);
                        mLvOrder.setVisibility(View.INVISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), body.error.msg + "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_done:
                mType = 0;
                initData();
                mNowState = 0;
                hideOtherState(mNowState, mPreState);
                mPreState = mNowState;
                mBtnOrderAgo.setTextColor(Color.WHITE);
                mBtnOrderAgo.setBackgroundResource(R.drawable.rectangle_corner_pressed);
                break;
            case R.id.btn_order_undone:
                mType = 1;
                initData();
                mNowState = 1;
                hideOtherState(mNowState, mPreState);
                mPreState = mNowState;
                mBtnOrderUndone.setTextColor(Color.WHITE);
                mBtnOrderUndone.setBackgroundResource(R.drawable.rectangle_corner_pressed);
                break;
            case R.id.btn_order_cancel:
                mType = 2;
                initData();
                mNowState = 2;
                hideOtherState(mNowState, mPreState);
                mPreState = mNowState;
                mBtnOrderCancel.setTextColor(Color.WHITE);
                mBtnOrderCancel.setBackgroundResource(R.drawable.rectangle_corner_pressed);
                break;
            case R.id.btn_left:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OrderBean.OrderListBean orderListBean = mList.get(position);
        switch (orderListBean.getState()) {
            case 0:
                Intent unPayIntent = new Intent(this, UnPayActivity.class);
                unPayIntent.putExtra("orderListBean", orderListBean);
                startActivityForResult(unPayIntent, REQUEST_CODE);
                break;
            case 1:
                Intent payedIntent = new Intent(this, PayedActivity.class);
                payedIntent.putExtra("orderListBean", orderListBean);
                startActivityForResult(payedIntent, REQUEST_CODE);
                break;
            case 2:
                Intent cancelledIntent = new Intent(this, CancelledActivity.class);
                cancelledIntent.putExtra("orderListBean", orderListBean);
                startActivityForResult(cancelledIntent, REQUEST_CODE);
                break;
        }
        finish();
    }

    //获取用户信息
    private void getUser() {
        USER_ID = SPUtils.getString(this, "user_id");
        TOKEN = SPUtils.getString(this, "token");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UnPayActivity.REQUSET_CODE) {
            initData();
        }
    }

    private void hideOtherState(int nowState, int mPreState) {
        if (nowState == mPreState) {
            return;
        } else {
            switch (mPreState) {
                case 0:
                    mBtnOrderAgo.setTextColor(Color.BLACK);
                    mBtnOrderAgo.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
                case 1:
                    mBtnOrderUndone.setTextColor(Color.BLACK);
                    mBtnOrderUndone.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
                case 2:
                    mBtnOrderCancel.setTextColor(Color.BLACK);
                    mBtnOrderCancel.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
            }
        }
    }
}
