package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.AddressDefaultDetailApi;
import com.heima.jyl.redboy.api.OrdersumbitApi;
import com.heima.jyl.redboy.bean.AddressDefaultDetailBean;
import com.heima.jyl.redboy.bean.BackAddressBean;
import com.heima.jyl.redboy.bean.CarBean;
import com.heima.jyl.redboy.bean.InvoiceBean;
import com.heima.jyl.redboy.bean.OrdersumbitBean;
import com.heima.jyl.redboy.bean.OrdersumbitInfoBean;
import com.heima.jyl.redboy.ui.adapter.CenterAdapter;
import com.heima.jyl.redboy.ui.view.DeliverPatternDialog;
import com.heima.jyl.redboy.ui.view.ListView;
import com.heima.jyl.redboy.ui.view.PayPatternDialog;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @创建者 种金币
 * @创建时间 2016/6/15 8:55
 * @描述 生成订单
 */
public class CenterActivity extends AppCompatActivity implements View.OnClickListener {

    private long exitTime = 0;
    private Intent mIntent;
    private List<CarBean.CartBean> mDatas = new ArrayList();
    private TextView            mCenter_tv_pay;
    private RelativeLayout      mCenter_rl_address;
    private TextView            mTextAddress2;
    private TextView            mTextAdress3;
    private TextView            mTextAdress4;
    private RelativeLayout      mPayment_payType_rel;
    private TextView            mPayment_payValue_text;
    private RelativeLayout      mPayment_sendTime_rel;
    private TextView            mPayment_sendTimeValue_text;
    private RelativeLayout      mPayment_invoice_rel;
    private TextView            mPayment_remarkView_text;
    private TextView            mShopcar_total_buycount_text;
    private TextView            mShopcar_total_money_text;
    private TextView            mOrdr_submit_bottom_text;
    private ListView            mPayment_product_list;
    private CenterAdapter       mAdapter;
    private OrdersumbitInfoBean mInfoBeen;
    private String              TOKEN;
    private String              USER_ID;
    private TextView            mPayment_remarkView_empty;
    private TextView            mPayment_remarkHint_text;
    private LinearLayout        mPayment_remarkHint;
    private String mSumMoney;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        mIntent = getIntent();

        init();
    }

    //初始化信息
    private void init() {
        getUser();
        initView();
        setDefaultInfo();
        initData();
        updateListView();
        shouText();
    }

    //获取控件
    private void initView() {
        mCenter_tv_pay = (TextView) findViewById(R.id.center_tv_pay);
        mCenter_rl_address = (RelativeLayout) findViewById(R.id.center_rl_address);
        mTextAddress2 = (TextView) findViewById(R.id.textAddress2);
        mTextAdress3 = (TextView) findViewById(R.id.textAdress3);
        mTextAdress4 = (TextView) findViewById(R.id.textAdress4);
        mPayment_payType_rel = (RelativeLayout) findViewById(R.id.payment_payType_rel);
        mPayment_payValue_text = (TextView) findViewById(R.id.payment_payValue_text);
        mPayment_sendTime_rel = (RelativeLayout) findViewById(R.id.payment_sendTime_rel);
        mPayment_sendTimeValue_text = (TextView) findViewById(R.id.payment_sendTimeValue_text);
        mPayment_invoice_rel = (RelativeLayout) findViewById(R.id.payment_invoice_rel);
        mPayment_remarkView_text = (TextView) findViewById(R.id.payment_remarkView_text);
        mPayment_remarkView_empty = (TextView) findViewById(R.id.payment_remarkView_empty);
        mPayment_remarkHint_text = (TextView) findViewById(R.id.payment_remarkHint_text);
        mPayment_remarkHint = (LinearLayout) findViewById(R.id.payment_remarkHint);
        mPayment_product_list = (ListView) findViewById(R.id.payment_product_list);
        mShopcar_total_buycount_text = (TextView) findViewById(R.id.shopcar_total_buycount_text);
        mShopcar_total_money_text = (TextView) findViewById(R.id.shopcar_total_money_text);
        mOrdr_submit_bottom_text = (TextView) findViewById(R.id.ordr_submit_bottom_text);

        mCenter_rl_address.setOnClickListener(this);
        mPayment_payType_rel.setOnClickListener(this);
        mPayment_sendTime_rel.setOnClickListener(this);
        mPayment_invoice_rel.setOnClickListener(this);
        mOrdr_submit_bottom_text.setOnClickListener(this);

    }

    //设置默认属性
    private void setDefaultInfo() {
        mInfoBeen = new OrdersumbitInfoBean();
        mInfoBeen.setPaymentType(1);
        mInfoBeen.setAddressid(0);
        mInfoBeen.setDeliveryType(1);
        mInfoBeen.setInvoiceType(0);
        mInfoBeen.setInvoiceTitle("");
        mInfoBeen.setInvoiceContent("");
        getDefaultAddress();
    }

    //处理产品信息
    private void initData() {
        int i = 1;
        StringBuffer cids = new StringBuffer();
        String cid = "";
        while (true) {
            CarBean.CartBean parcelable = mIntent.getParcelableExtra(i + "");
            if (parcelable != null) {
                cids.append(parcelable.getId() + "-");
                mDatas.add(parcelable);
                i = i + 1;
            } else {
                cid = cids.substring(0, cids.length() - 1);
                mInfoBeen.setCids(cid);
                break;
            }
        }
    }

    //更新ListView
    private void updateListView() {
        mAdapter = new CenterAdapter(this, mDatas);
        mPayment_product_list.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center_rl_address:
                Intent intent = new Intent(this,AddressManageActivity.class);
                intent.putExtra("center", "center");
                startActivityForResult(intent,101);
                break;
            case R.id.payment_payType_rel:
                new PayPatternDialog(this, new PayPatternDialog.PriorityListener() {
                    @Override
                    public void refreshPriorityUI(String str, int position) {
                        mPayment_payValue_text.setText(str);
                        mInfoBeen.setPaymentType(position);
                    }
                }).show();
                break;
            case R.id.payment_sendTime_rel:
                new DeliverPatternDialog(this, new DeliverPatternDialog.PriorityListener() {
                    @Override
                    public void refreshPriorityUI(String str, int position) {
                        mPayment_sendTimeValue_text.setText(str);
                        mInfoBeen.setDeliveryType(position);
                    }
                }).show();
                break;
            case R.id.payment_invoice_rel:
                startActivityForResult(new Intent(this, BillNoteActivity.class), 100);
                break;
            case R.id.ordr_submit_bottom_text:
                if (mInfoBeen.getAddressid() == 0) {
                    Toast.makeText(this, "亲，你还没设置地址哦！！", Toast.LENGTH_SHORT).show();
                } else {
                    submit();
                    finish();
                }
                break;
        }
    }

    //提交订单
    private void submit() {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(OrdersumbitApi.class).delOrdersumbit(USER_ID, TOKEN,
                mInfoBeen.getCids(),
                mInfoBeen.getAddressid(),
                mInfoBeen.getPaymentType(),
                mInfoBeen.getDeliveryType(),
                mInfoBeen.getInvoiceType(),
                mInfoBeen.getInvoiceTitle(),
                mInfoBeen.getInvoiceContent())
                .enqueue(new Callback<OrdersumbitBean>() {
                    @Override
                    public void onResponse(Response<OrdersumbitBean> response, Retrofit retrofit) {
                        if (response.errorBody() == null) {
                            OrdersumbitBean.OrderInfoBean orderInfo = response.body().getOrderInfo();
                            Intent intent = new Intent(getApplicationContext(),PayActivity.class);
                            intent.putExtra("orderid", orderInfo.getOrderid());
                            intent.putExtra("money",mSumMoney);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(CenterActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();
                    }
                });
    }

    //设置总金额和总数量
    private void shouText() {
        int sum = 0;
        int num = 0;
        for (CarBean.CartBean data : mDatas) {
            if (data.isCheck()) {
                num = num + Integer.valueOf(data.getPnum());
                sum = sum + Integer.valueOf(data.getPnum()) * Integer.valueOf(data.getProductPrice());
            }
        }
        mSumMoney = sum + ".00";
        mCenter_tv_pay.setText(mSumMoney);
        mShopcar_total_money_text.setText(sum + ".00");
        mShopcar_total_buycount_text.setText(num + "");
    }

    //获取默认地址，进行设置
    public void getDefaultAddress() {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(AddressDefaultDetailApi.class).getAddress(USER_ID, TOKEN)
                .enqueue(new Callback<AddressDefaultDetailBean>() {
                    @Override
                    public void onResponse(Response<AddressDefaultDetailBean> response, Retrofit retrofit) {
                        if (response.errorBody() == null) {
                            AddressDefaultDetailBean.AddressBean addressBean = response.body().getAddress();
                            if (addressBean == null) {
                                return;
                            } else {
                                String name = addressBean.getName();
                                String phoneNumber = addressBean.getPhoneNumber();
                                String address = addressBean.getProvince()
                                        + addressBean.getCity()
                                        + addressBean.getAddressArea()
                                        + addressBean.getAddressDetail();
                                setAddress(name, phoneNumber, address);
                                mInfoBeen.setAddressid(addressBean.getId());
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "222", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //设置地址栏
    private void setAddress(String name, String phoneNumber, String address) {
        mTextAddress2.setText(name);
        mTextAdress3.setText(phoneNumber);
        mTextAdress4.setText(address);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            InvoiceBean invoiceBean = (InvoiceBean) data.getSerializableExtra("InvoiceBean");
            if (invoiceBean.type == 0) {
                mInfoBeen.setDeliveryType(0);
                mPayment_remarkView_empty.setVisibility(View.VISIBLE);
                mPayment_remarkHint.setVisibility(View.INVISIBLE);
            } else {
                mPayment_remarkHint.setVisibility(View.VISIBLE);
                mPayment_remarkView_empty.setVisibility(View.INVISIBLE);
                mPayment_remarkHint_text.setText(invoiceBean.head);
                mInfoBeen.setInvoiceTitle(invoiceBean.head);
                mPayment_remarkView_text.setText(invoiceBean.content);
                mInfoBeen.setInvoiceContent(invoiceBean.content);
            }
        }
        if (resultCode == 201) {
            BackAddressBean addressBean = data.getParcelableExtra("AddressBean");
            mTextAddress2.setText(addressBean.getName());
            mTextAdress3.setText(addressBean.getPhone());
            mTextAdress4.setText(addressBean.getAddress());
            mInfoBeen.setAddressid(addressBean.getAddressId());
        }
    }

    //获取用户信息
    private void getUser() {
        USER_ID = SPUtils.getString(this, "user_id");
        TOKEN = SPUtils.getString(this, "token");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "亲，你还没提交定单，却定退出吗？",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

}
