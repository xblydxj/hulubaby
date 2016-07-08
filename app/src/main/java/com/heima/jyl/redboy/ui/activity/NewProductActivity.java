package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.NewProductApi;
import com.heima.jyl.redboy.bean.NewProductBean;
import com.heima.jyl.redboy.ui.adapter.NewProductAdapter;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;
import com.heima.jyl.redboy.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class NewProductActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    private ListView lv_newproduct;
    private ArrayList<NewProductBean.ProductListBean> mData = new ArrayList<>();
    public static final String SALEDOWN = "saleDown";
    public static final String PRICEUP = "priceUp";
    public static final String PRICEDOWN = "priceDown";
    public static final String SHELVESDOWN = "shelvesDown";
    public static final String COMMENTDOWN = "commentDown";
    private TextView tv_salev;
    private TextView tv_price;
    private TextView tv_favour;
    private TextView tv_shelftime;
    private NewProductAdapter mAdapter;
    private Toolbar mToolbar;
    private ListView mLv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        initView();
        initData();
        Intent intent = getIntent();
        String title = intent.getStringExtra("titleName");
        mToolbar.setTitle(title);
    }

    private void initData() {
        getOrderby(SALEDOWN);
    }

    private void getOrderby(String order) {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(NewProductApi.class).getNewProduct(1, 10, order).enqueue(new Callback<NewProductBean>() {
            @Override
            public void onResponse(Response<NewProductBean> response, Retrofit retrofit) {
                List<NewProductBean.ProductListBean> productList = response.body().getProductList();
                mData.clear();
                mData.addAll(productList);
                mAdapter = new NewProductAdapter(NewProductActivity.this, mData);
                lv_newproduct.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Throwable throwable) {
                SnackBarUtils.SnackBarLong(lv_newproduct, "网络连接失败！");
            }
        });
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.product_toolbar);
        lv_newproduct = (ListView) findViewById(R.id.lv_newproduct);
        tv_salev = (TextView) findViewById(R.id.tv_salev);
        tv_salev.setOnClickListener(this);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_price.setOnClickListener(this);
        tv_favour = (TextView) findViewById(R.id.tv_favour);
        tv_favour.setOnClickListener(this);
        tv_shelftime = (TextView) findViewById(R.id.tv_shelftime);
        tv_shelftime.setOnClickListener(this);
        mLv = (ListView) findViewById(R.id.lv_newproduct);

        //初始化默认选中第一个按钮
        tv_salev.setBackgroundResource(R.drawable.rectangle_corner_pressed);
        mLv.setOnItemClickListener(this);
    }

    public int mPreState = 0;
    public int mNowState = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_back:
//                startActivity(new Intent(this, MainActivity.class));
//                finish();
//                overridePendingTransition(R.anim.right_enter, R.anim.left_exit);
//                break;
            case R.id.tv_salev:
                mNowState = 0;
                hideOther(mNowState,mPreState);
                mPreState = mNowState;
                tv_salev.setTextColor(Color.WHITE);
                tv_salev.setBackgroundResource(R.drawable.rectangle_corner_pressed);
                getOrderby(SALEDOWN);
                break;
            case R.id.tv_price:
                mNowState = 1;
                hideOther(mNowState,mPreState);
                mPreState = mNowState;
                tv_price.setTextColor(Color.WHITE);
                tv_price.setBackgroundResource(R.drawable.rectangle_corner_pressed);
                boolean b = SPUtils.getBoolean(this, SALEDOWN, true);
                getOrderby(b ? PRICEDOWN : PRICEUP);
                break;
            case R.id.tv_favour:
                mNowState = 2;
                hideOther(mNowState,mPreState);
                mPreState = mNowState;
                tv_favour.setTextColor(Color.WHITE);
                tv_favour.setBackgroundResource(R.drawable.rectangle_corner_pressed);
//                getOrderby(COMMENTDOWN);
                break;
            case R.id.tv_shelftime:
                mNowState = 3;
                hideOther(mNowState,mPreState);
                mPreState = mNowState;
                tv_shelftime.setTextColor(Color.WHITE);
                tv_shelftime.setBackgroundResource(R.drawable.rectangle_corner_pressed);
                getOrderby(SHELVESDOWN);
                break;
        }
    }


    private void hideOther(int nowState, int mPreState) {
        if (nowState == mPreState) {
            return;
        } else {
            switch (mPreState) {
                case 0:
                    tv_salev.setTextColor(Color.BLACK);
                    tv_salev.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
                case 1:
                    tv_price.setTextColor(Color.BLACK);
                    tv_price.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
                case 2:
                    tv_favour.setTextColor(Color.BLACK);
                    tv_favour.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
                case 3:
                    tv_shelftime.setTextColor(Color.BLACK);
                    tv_shelftime.setBackgroundResource(R.drawable.rectangle_corner);
                    break;
            }
        }

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.right_enter, R.anim.left_exit);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewProductBean.ProductListBean item = (NewProductBean.ProductListBean) parent.getItemAtPosition(position);
        String pId = item.getId();
        Intent intent = new Intent(parent.getContext(), ProductDetailActivity.class);
        intent.putExtra("pId", pId);
        startActivity(intent);
    }
}
