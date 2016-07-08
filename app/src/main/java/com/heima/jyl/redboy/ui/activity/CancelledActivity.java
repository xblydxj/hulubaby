package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.AddressDetailApi;
import com.heima.jyl.redboy.api.OrderDeleteApi;
import com.heima.jyl.redboy.bean.AddressDefaultDetailBean;
import com.heima.jyl.redboy.bean.OrderBean;
import com.heima.jyl.redboy.ui.adapter.ProductAdapter;
import com.heima.jyl.redboy.ui.view.ListView;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;
import com.heima.jyl.redboy.utils.SnackBarUtils;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CancelledActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUSET_CODE = 103;
    private Toolbar center_id_toolbar;
    private TextView textView5;
    private TextView payment_address_text;
    private TextView textAddress2;
    private TextView textAdress3;
    private TextView textAdress4;
    private RelativeLayout center_rl_address;
    private TextView payment_payHint_text;
    private TextView payment_payValue_text;
    private RelativeLayout payment_payType_rel;
    private TextView payment_sendTimeHint_text;
    private TextView payment_sendTimeValue_text;
    private RelativeLayout payment_sendTime_rel;
    private TextView payment_invoiceHint_text;
    private TextView payment_invoiceValue_text;
    private RelativeLayout payment_invoice_rel;
    private TextView payment_remarkHint_text;
    private TextView payment_remarkView_text;
    private LinearLayout payment_remarkHint;
    private TextView payment_remarkView_empty;
    private ListView payment_product_list;
    private TextView shopcar_total_buycount_text;
    private TextView shopcar_total_money_text;
    private TextView ordr_submit_bottom_text;
    private ScrollView scrollView;
    private String USER_ID;
    private String TOKEN;
    private OrderBean.OrderListBean mOrderListBean;
    private Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelled);
        initView();
        initData();
    }

    private void initData() {
        getUser();
        mOrderListBean = getIntent().getParcelableExtra("orderListBean");
        textView5.setText(mOrderListBean.getOrderid());
        mRetrofit = RetrofitUtil.RetrofitConfig();
        mRetrofit.create(AddressDetailApi.class).getAddressDetail(mOrderListBean.getAddressid(), TOKEN, USER_ID).enqueue(new Callback<AddressDefaultDetailBean>() {
            @Override
            public void onResponse(Response<AddressDefaultDetailBean> response, Retrofit retrofit) {
                if (response.body().error == null) {
                    AddressDefaultDetailBean.AddressBean address = response.body().getAddress();
                    textAddress2.setText(address.getName());
                    textAdress3.setText(address.getPhoneNumber());
                    textAdress4.setText(address.getAddressArea() + "\t" + address.getAddressDetail());
                } else {
                    Toast.makeText(CancelledActivity.this, response.body().error.msg, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Throwable throwable) {
                SnackBarUtils.SnackBarShort(textAdress4, "网络连接失败");

            }
        });
        payment_payValue_text.setText(mOrderListBean.getPaymentType() == 0 ? "支付宝支付" : "现金支付");
        payment_sendTimeValue_text.setText(mOrderListBean.getDeliveryType() == 1 ? "任何时间" : "仅限工作日");
        switch (mOrderListBean.getInvoiceType()) {
            case 0:
                payment_remarkHint.setVisibility(View.VISIBLE);
                payment_invoiceHint_text.setText("不需要发票");
                break;
            case 1:
                payment_invoiceHint_text.setText("个人发票");
                break;
            case 2:
                payment_invoiceHint_text.setText("单位发票");
                break;
        }
        payment_invoiceValue_text.setText(mOrderListBean.getInvoiceContent());
        payment_product_list.setAdapter(new ProductAdapter(this, mOrderListBean.getCarts()));
        setBuyDetail(mOrderListBean.getCarts());

    }

    private void setBuyDetail(List<OrderBean.OrderListBean.CartBean> carts) {
        int sum = 0;
        int num = 0;
        for (OrderBean.OrderListBean.CartBean data : carts) {
            num = num + Integer.valueOf(data.getPnum());
            sum = sum + Integer.valueOf(data.getPnum()) * Integer.valueOf(data.getProductPrice());
        }
        shopcar_total_money_text.setText(sum + ".00");
        shopcar_total_buycount_text.setText(num + "");
    }

    private void initView() {
        center_id_toolbar = (Toolbar) findViewById(R.id.center_id_toolbar);
        textView5 = (TextView) findViewById(R.id.textView5);
        payment_address_text = (TextView) findViewById(R.id.payment_address_text);
        textAddress2 = (TextView) findViewById(R.id.textAddress2);
        textAdress3 = (TextView) findViewById(R.id.textAdress3);
        textAdress4 = (TextView) findViewById(R.id.textAdress4);
        center_rl_address = (RelativeLayout) findViewById(R.id.center_rl_address);
        payment_payHint_text = (TextView) findViewById(R.id.payment_payHint_text);
        payment_payValue_text = (TextView) findViewById(R.id.payment_payValue_text);
        payment_payType_rel = (RelativeLayout) findViewById(R.id.payment_payType_rel);
        payment_sendTimeHint_text = (TextView) findViewById(R.id.payment_sendTimeHint_text);
        payment_sendTimeValue_text = (TextView) findViewById(R.id.payment_sendTimeValue_text);
        payment_sendTime_rel = (RelativeLayout) findViewById(R.id.payment_sendTime_rel);
        payment_invoiceHint_text = (TextView) findViewById(R.id.payment_invoiceHint_text);
        payment_invoiceValue_text = (TextView) findViewById(R.id.payment_invoiceValue_text);
        payment_invoice_rel = (RelativeLayout) findViewById(R.id.payment_invoice_rel);
        payment_remarkHint_text = (TextView) findViewById(R.id.payment_remarkHint_text);
        payment_remarkView_text = (TextView) findViewById(R.id.payment_remarkView_text);
        payment_remarkHint = (LinearLayout) findViewById(R.id.payment_remarkHint);
        payment_remarkView_empty = (TextView) findViewById(R.id.payment_remarkView_empty);
        payment_product_list = (ListView) findViewById(R.id.payment_product_list);
        shopcar_total_buycount_text = (TextView) findViewById(R.id.shopcar_total_buycount_text);
        shopcar_total_money_text = (TextView) findViewById(R.id.shopcar_total_money_text);
        ordr_submit_bottom_text = (TextView) findViewById(R.id.ordr_submit_bottom_text);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        ordr_submit_bottom_text.setOnClickListener(this);
    }

    //获取用户信息
    private void getUser() {
        USER_ID = SPUtils.getString(this, "user_id");
        TOKEN = SPUtils.getString(this, "token");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ordr_submit_bottom_text:
                mRetrofit.create(OrderDeleteApi.class).deleteOrder(mOrderListBean.getOrderid(), USER_ID, TOKEN).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        ResponseBody errorBody = response.errorBody();
                        if (errorBody != null) {
                            SnackBarUtils.SnackBarShort(ordr_submit_bottom_text, response.errorBody().toString());
                        } else {
                            SnackBarUtils.SnackBarShort(ordr_submit_bottom_text, "删除成功");
                            finish();
                            startActivityForResult(new Intent(CancelledActivity.this,OrderActivity.class),REQUSET_CODE);
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        SnackBarUtils.SnackBarShort(ordr_submit_bottom_text,"网络连接失败");
                        throwable.printStackTrace();
                    }
                });
                break;
        }
    }
}
