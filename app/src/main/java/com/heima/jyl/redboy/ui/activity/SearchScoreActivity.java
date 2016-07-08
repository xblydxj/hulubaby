package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.SearchApi;
import com.heima.jyl.redboy.bean.SearchBean;
import com.heima.jyl.redboy.ui.adapter.SearchGoalAdapter;
import com.heima.jyl.redboy.utils.Constants.Constants;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;
import com.heima.jyl.redboy.utils.SnackBarUtils;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchScoreActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    private Button                mBtSellCount;
    private Button                mBtPriceOrder;
    private Button                mBtCallback;
    private Button                mFirstSellTime;
    private ListView              mLvGoodsShow;
    private SearchGoalAdapter     mAdapter;
    private String                mSearchword;
    private LinearLayout mLl_search_message;
    private LinearLayout mLl_no_search_goal;
    public static final String PRICEUP = "priceUp";
    public static final String PRICEDOWN = "priceDown";
    public static final String ORDER = "defaultOrder";
    private String mOrderFlag;
    public List<SearchBean.ProductListBean> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_score);

        getSearchGoal();
        initView();
        //mLl_search_message.setVisibility(View.GONE);
        //mLl_no_search_goal.setVisibility(View.VISIBLE);

    }

    public void getSearchGoal() {

        if (mSearchword == null) {
            Intent intent = getIntent();
            mSearchword = intent.getStringExtra("searchword");
        }

       Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(SearchApi.class).
                getSearch(1, 10, Constants.SALE_DOWN,mSearchword)
                .enqueue(new Callback<SearchBean>() {
                    @Override
                    public void onResponse(Response<SearchBean> response, Retrofit retrofit) {
                        productList = response.body().getProductList();
                        // int  id; int marketPrice;String name;String pic;int price;
                        mAdapter = new SearchGoalAdapter(SearchScoreActivity.this, productList);

                        if (productList.size() != 0) {

                            mLvGoodsShow.setAdapter(mAdapter);

                        } else {
                            mLl_search_message.setVisibility(View.GONE);
                            mLl_no_search_goal.setVisibility(View.VISIBLE);

                        }
                    }
                    @Override
                    public void onFailure(Throwable throwable) {
                        SnackBarUtils.SnackBarLong(mBtSellCount,"网络请求失败");
                    }
                });


    }

    private void initView() {

        mBtSellCount = (Button) findViewById(R.id.bt_sell_count);
        mBtPriceOrder = (Button) findViewById(R.id.bt_price_order);
        mBtCallback = (Button) findViewById(R.id.bt_callback);
        mFirstSellTime = (Button) findViewById(R.id.firstSell_time);
        mLvGoodsShow = (ListView) findViewById(R.id.lv_goods_show);
        mLl_search_message = (LinearLayout) findViewById(R.id.ll_search_message);
        mLl_no_search_goal = (LinearLayout) findViewById(R.id.ll_no_search_goal);


        mBtSellCount.setOnClickListener(this);
        mBtPriceOrder.setOnClickListener(this);
        mBtCallback.setOnClickListener(this);
        mFirstSellTime.setOnClickListener(this);
        mLvGoodsShow.setOnItemClickListener(this);

        //设置第一个按钮默认状态
        mBtSellCount.setBackgroundResource(R.drawable.rectangle_corner_pressed);
    }

    public void getOrdersearch(String order) {

        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(SearchApi.class).
                getSearch(1, 10, order,mSearchword)
                .enqueue(new Callback<SearchBean>() {
                    @Override
                    public void onResponse(Response<SearchBean> response, Retrofit retrofit) {
                        productList = response.body().getProductList();

                        mAdapter = new SearchGoalAdapter(SearchScoreActivity.this, productList);

                            mLvGoodsShow.setAdapter(mAdapter);
                    }
                    @Override
                    public void onFailure(Throwable throwable) {
                        SnackBarUtils.SnackBarLong(mBtSellCount,"网络请求失败");
                    }
                });



    }

    public int mPreState = 0;
    public int mNowState = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//            case R.id.bt_return:
//                //startActivity(new Intent(this, MainActivity.class));
//                finish();
//                break;

            case R.id.bt_sell_count:
                mNowState = 0;
                hideOtherState(mNowState,mPreState);
                mPreState = mNowState;
                mBtSellCount.setTextColor(Color.WHITE);
                mBtSellCount.setBackgroundResource(R.drawable.rectangle_corner_pressed);
                getSearchGoal();
                break;

            case R.id.bt_price_order:
                mNowState = 1;
                hideOtherState(mNowState,mPreState);
                mPreState = mNowState;
                mBtPriceOrder.setTextColor(Color.WHITE);
                mBtPriceOrder.setBackgroundResource(R.drawable.rectangle_corner_pressed);

                //网络请求数据
                boolean flag = SPUtils.getBoolean(this, ORDER, true);
                SPUtils.putBoolean(this,ORDER,!flag);
                mOrderFlag = flag ? PRICEDOWN : PRICEUP;
                getOrdersearch(mOrderFlag);
                break;


            case R.id.bt_callback:

                mNowState = 2;
                hideOtherState(mNowState,mPreState);
                mPreState = mNowState;
                mBtCallback.setTextColor(Color.WHITE);
                mBtCallback.setBackgroundResource(R.drawable.rectangle_corner_pressed);
                Toast.makeText(this, "暂无数据", Toast.LENGTH_SHORT).show();
                break;

            case R.id.firstSell_time:
                mNowState = 3;
                hideOtherState(mNowState,mPreState);
                mPreState = mNowState;
                mFirstSellTime.setTextColor(Color.WHITE);
                mFirstSellTime.setBackgroundResource(R.drawable.rectangle_corner_pressed);
                Toast.makeText(this, "暂无数据", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void hideOtherState(int nowState,int mPreState) {
        if (nowState == mPreState) {
            return;
        }
        else {
            switch (mPreState) {
                case 0:
                    mBtSellCount.setTextColor(Color.BLACK);
                    mBtSellCount.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
                case 1:
                    mBtPriceOrder.setTextColor(Color.BLACK);
                    mBtPriceOrder.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
                case 2:
                    mBtCallback.setTextColor(Color.BLACK);
                    mBtCallback.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
                case 3:
                    mFirstSellTime.setTextColor(Color.BLACK);
                    mFirstSellTime.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("pId",productList.get(position).getId()+"");
        startActivity(intent);
    }
}
