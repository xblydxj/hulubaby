package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.LimitBuyApi;
import com.heima.jyl.redboy.bean.LimitBuyBean;
import com.heima.jyl.redboy.ui.adapter.LimitBuyAdapter;
import com.heima.jyl.redboy.utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LimitBuyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv_home_limitbuy;
    private ArrayList<LimitBuyBean.LimitbuyListBean> mData = new ArrayList<>();
    private LimitBuyAdapter mAdapter;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limitbuy);
        initView();
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(LimitBuyApi.class).getLimitBuy(1, 10).enqueue(new Callback<LimitBuyBean>() {
            @Override
            public void onResponse(Response<LimitBuyBean> response, Retrofit retrofit) {
                List<LimitBuyBean.LimitbuyListBean> productList = response.body().getProductList();
                mData.addAll(productList);
                mAdapter = new LimitBuyAdapter(LimitBuyActivity.this, mData);
                lv_home_limitbuy.setAdapter(mAdapter);
                mAdapter.start();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        mHandler.postDelayed(this, 1000);
                    }
                }, 1000);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable);
            }
        });


    }

    private void initView() {
        lv_home_limitbuy = (ListView) findViewById(R.id.lv_home_limitbuy);

        lv_home_limitbuy.setOnItemClickListener(this);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.right_enter, R.anim.left_exit);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LimitBuyBean.LimitbuyListBean item = (LimitBuyBean.LimitbuyListBean) parent.getItemAtPosition(position);
        Intent intent = new Intent(parent.getContext(),ProductDetailActivity.class);
        intent.putExtra("pId", item.getId());
        startActivity(intent);
    }
}
