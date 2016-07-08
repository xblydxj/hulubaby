package com.heima.jyl.redboy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.BaseUrl;
import com.heima.jyl.redboy.api.FavoritesApi;
import com.heima.jyl.redboy.bean.FavoritesBean;
import com.heima.jyl.redboy.ui.adapter.CollectorAdapter;
import com.heima.jyl.redboy.utils.SPUtils;

import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ting说你跳 on 2016/6/15.
 */


public class CollectorActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private FloatingActionButton btnClear;
    private ListView         mLvCollector;
    private CollectorAdapter mAdapter;
    private List<FavoritesBean.ProductListBean> mList;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        btnClear = (FloatingActionButton) findViewById(R.id.collector_clear);
        mLvCollector = (ListView) findViewById(R.id.lv_collector);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_collector_empty);
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl.basUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        String token = SPUtils.getString(this, "token");
        String user_id = SPUtils.getString(this, "user_id");
        retrofit.create(FavoritesApi.class)
                .getFavorites(user_id, token, 1, 10)
                .enqueue(new Callback<FavoritesBean>() {
                    @Override
                    public void onResponse(Response<FavoritesBean> response, Retrofit retrofit) {
                        String responseCode = response.body().getResponse();
                        if(!"error".equals(responseCode)){
                            mList = response.body().getProductList();
                            mAdapter = new CollectorAdapter(getApplicationContext(), mList);
                            mLvCollector.setAdapter(mAdapter);
                            //放在设置adapter之后
                            mLvCollector.setEmptyView(mLinearLayout);
                        }else {
                            Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setListener() {
        btnClear.setOnClickListener(this);
        mLvCollector.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collector_clear:
                mList.clear();
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FavoritesBean.ProductListBean itemAtPosition = (FavoritesBean.ProductListBean) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("pId",itemAtPosition.getId()+"");
        startActivity(intent);
    }
}
