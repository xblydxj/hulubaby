package com.heima.jyl.redboy.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.CheckoutApi;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;
import com.squareup.okhttp.ResponseBody;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent   mIntent;
    private TextView mOrderid;
    private Button   mTrue;
    private Button   mFalse;
    private TextView mMoney;
    private String   mOrderid1;
    private String   mMoney1;
    private String alipaycount = "itheima@itcast.cn";
    private String alipaypwd   = "itheima123456";
    private String USER_ID;
    private String TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        mIntent = getIntent();
        init();
    }

    private void init() {
        initView();
        initData();
        getUser();
    }

    //获取控件
    private void initView() {
        mOrderid = (TextView) findViewById(R.id.pay_tv_orderid);
        mMoney = (TextView) findViewById(R.id.pay_tv_money);
        mTrue = (Button) findViewById(R.id.pay_btn_true);
        mFalse = (Button) findViewById(R.id.pay_btn_false);

        mTrue.setOnClickListener(this);
        mFalse.setOnClickListener(this);
    }

    private void initData() {
        mOrderid1 = mIntent.getStringExtra("orderid");
        mMoney1 = mIntent.getStringExtra("money");
        mOrderid.setText(mOrderid1);
        mMoney.setText(mMoney1);
    }


    private void showPayDialog() {
        //new一个Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置Dialog的标题
        builder.setTitle("快捷支付").setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                showCDialog();

            }
        })
                //设置Dialog的提示类容
                .setMessage("用户账号：" + alipaycount + "\n\n" +
                        "支付密码：" + alipaypwd)
                //设置Dialog的两个按键
                .setPositiveButton("确认支付", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        payMany();
                        initGo();
                    }
                });
        builder.setNegativeButton("取消支付", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        // 取消对屏幕的监听，只能点击dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        //show出来
        alertDialog.show();
    }

    private void showCDialog() {
        //new一个Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置Dialog的标题
        builder.setTitle("确认离开").setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        })
                //设置Dialog的提示类容
                .setMessage("亲，你还未付款，确认离开吗")
                //设置Dialog的两个按键
                .setPositiveButton("狠心离开", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initGo();
                    }
                });
        builder.setNegativeButton("再看看", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        // 取消对屏幕的监听，只能点击dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        //show出来
        alertDialog.show();
    }

    private void initGo() {
        startActivity(new Intent(this, OrderActivity.class));
        finish();
    }


    //付款
    private void payMany() {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(CheckoutApi.class).setPay(USER_ID, TOKEN, mOrderid1, alipaycount, alipaypwd, mMoney1)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        ResponseBody body = response.errorBody();
                        if (body == null) {
                            Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "支付失败", Toast.LENGTH_SHORT).show();
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
            case R.id.pay_btn_true:
                showPayDialog();
                break;
            case R.id.pay_btn_false:
                showCDialog();
                break;
        }
    }

    //获取用户信息
    private void getUser() {
        USER_ID = SPUtils.getString(this, "user_id");
        TOKEN = SPUtils.getString(this, "token");
    }

    @Override
    public void onBackPressed() {
        showCDialog();
    }
}
