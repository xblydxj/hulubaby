package com.heima.jyl.redboy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.SUserInfoApi;
import com.heima.jyl.redboy.bean.SUserInfoBean;
import com.heima.jyl.redboy.ui.view.Listitemview;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;
import com.heima.jyl.redboy.utils.SnackBarUtils;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ting说你跳 on 2016/6/15.
 */
public class AccountActivity extends Activity implements View.OnClickListener {
    private FloatingActionButton mBtnMore;
    private FloatingActionButton mBtnBack;
    private Listitemview         mLtvOrder;
    private Listitemview         mLtvAddress;
    private Listitemview         mLtvCoupon;
    private Listitemview         mLtvCollector;
    private TextView             mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        setListener();
//        addActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv_account);
        mBtnMore = (FloatingActionButton) findViewById(R.id.account_fab_more);
        mBtnBack = (FloatingActionButton) findViewById(R.id.account_fab_exit);
        mLtvOrder = (Listitemview) findViewById(R.id.ltv_order);
        mLtvAddress = (Listitemview) findViewById(R.id.ltv_address);
        mLtvCoupon = (Listitemview) findViewById(R.id.ltv_coupon);
        mLtvCollector = (Listitemview) findViewById(R.id.ltv_collector);
    }

    private void initData() {
        String token = SPUtils.getString(this, "token");
        String user_id = SPUtils.getString(this, "user_id");
        Log.d("tag", token + " " + user_id);
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(SUserInfoApi.class)
                .getUserInfo(token, user_id)
                .enqueue(new Callback<SUserInfoBean>() {
                    @Override
                    public void onResponse(Response<SUserInfoBean> response, Retrofit retrofit) {
                        SUserInfoBean body = response.body();
                        if (body.error == null) {
                            SUserInfoBean.UserInfoBean userInfo = body.getUserInfo();
                            String username = SPUtils.getString(getApplicationContext(), "account");
                            String level = userInfo.getLevel();
                            int bonus = userInfo.getBonus();
                            Log.d("tag", level + "  " + bonus);
                            mTv.setText("用户名:  " + username + "\n会员等级:  " + level + "\n总积分:  " + bonus);
                            int orderCount = userInfo.getOrderCount();
                            int couponid = userInfo.getCouponid();
                            int favoritesCount = userInfo.getFavoritesCount();
                            TextView tv_order = (TextView) mLtvOrder.findViewById(R.id.view_list_item_title);
                            TextView tv_coupon = (TextView) mLtvCoupon.findViewById(R.id.view_list_item_title);
                            TextView tv_collector = (TextView) mLtvCollector.findViewById(R.id.view_list_item_title);
                            tv_order.setText("我的订单(" + orderCount + ")");
                            tv_coupon.setText("我的优惠券/礼品卡(" + couponid + ")");
                            tv_collector.setText("我的收藏(" + favoritesCount + ")");
                        } else {
                            SnackBarUtils.SnackBarShort(mTv, body.error.msg);
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        throwable.printStackTrace();
                        SnackBarUtils.SnackBarShort(mTv, "网络连接失败");
                    }
                });
    }

    private void setListener() {
        mBtnMore.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
        mLtvOrder.setOnClickListener(this);
        mLtvAddress.setOnClickListener(this);
        mLtvCoupon.setOnClickListener(this);
        mLtvCollector.setOnClickListener(this);
    }

    /*private void addActivity() {
        MyAppliaction.addActivity(AccountActivity.this);
    }*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_fab_exit:
                SPUtils.putString(this, "token", "");
                SPUtils.putString(this, "user_id", "");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.account_fab_more:
                finish();
                break;
            case R.id.ltv_order:
                startActivity(new Intent(this, OrderActivity.class));
                break;
            case R.id.ltv_address:
                startActivity(new Intent(this, AddressManageActivity.class));
                break;
            case R.id.ltv_coupon:
                break;
            case R.id.ltv_collector:
                startActivity(new Intent(this, CollectorActivity.class));
                break;
        }
    }
}
