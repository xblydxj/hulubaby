package com.heima.jyl.redboy.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.heima.jyl.redboy.api.AddressDeleteApi;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.AddAddressApi;
import com.heima.jyl.redboy.api.AddressDefaultApi;
import com.heima.jyl.redboy.api.BaseUrl;
import com.heima.jyl.redboy.bean.AddAddressBean;
import com.heima.jyl.redboy.ui.view.AddressDialog;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AddAddressActivity extends Activity implements View.OnClickListener {
    private EditText mEtAddaddressReceiver;
    private EditText mEtAddaddressPhone;
    private TextView mTvAddaddressRegion;
    private EditText mEtAddaddressDetailaddress;
    private EditText mEtAddaddressZipcode;
    private FloatingActionButton mSave;
    private String   mReceiver;
    private String   mPhone;
    private String   mRegion;
    private String   mDetail;
    private String   mZipcode;
    private TextView mTvTitle;
    private Button   mBtnDefault;
    private Button   mBtnDelete;
    private int      mId;
    private String   mToken;
    private String   mUser_id;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddressactivity);
        initView();
        iniData();
        setListener();
    }

    private void initView() {
        mSave = (FloatingActionButton) findViewById(R.id.add_save);
        mEtAddaddressReceiver = (EditText) findViewById(R.id.et_addaddress_receiver);
        mEtAddaddressPhone = (EditText) findViewById(R.id.et_addaddress_phone);
        mTvAddaddressRegion = (TextView) findViewById(R.id.tv_addaddress_region);
        mEtAddaddressDetailaddress = (EditText) findViewById(R.id.et_addaddress_detailaddress);
        mEtAddaddressZipcode = (EditText) findViewById(R.id.et_addaddress_zipcode);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mBtnDefault = (Button) findViewById(R.id.btn_address_default);
        mBtnDelete = (Button) findViewById(R.id.btn_address_delete);

        mToolbar = (Toolbar) findViewById(R.id.add_address_toolbar);

        //点击editveiw时，不弹出输入键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mTvAddaddressRegion.getWindowToken(), 0);
        mTvAddaddressRegion.setInputType(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mToolbar.setTitle("新增地址");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.md_white_color_code));
    }

    private void iniData() {

        mToken = SPUtils.getString(this, "token");
        mUser_id = SPUtils.getString(this, "user_id");
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        String where = intent.getStringExtra("where");
        if (TextUtils.equals(where, "modify")) {
            mEtAddaddressReceiver.setText(intent.getStringExtra("name"));
            mEtAddaddressPhone.setText(intent.getStringExtra("phoneNumber"));
            mTvAddaddressRegion.setText(intent.getStringExtra("address"));
            mEtAddaddressDetailaddress.setText(intent.getStringExtra("addressDetail"));
            mToolbar.setTitle("修改地址");
            mBtnDefault.setVisibility(View.VISIBLE);
            mBtnDelete.setVisibility(View.VISIBLE);
        } else {
            mBtnDefault.setEnabled(false);
            mBtnDelete.setEnabled(false);
            return;
        }
    }

    private void setListener() {
        mSave.setOnClickListener(this);
        mTvAddaddressRegion.setOnClickListener(this);
        mBtnDefault.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                finish();
                break;
            case R.id.add_save:
                save();
                break;
            case R.id.tv_addaddress_region:
                AddressDialog addressDialog = new AddressDialog(this, new AddressDialog.PriorityListener() {
                    @Override
                    public void refreshPriorityUI(final String string) {
                        mTvAddaddressRegion.setText(string);
                    }
                });
                addressDialog.setCanceledOnTouchOutside(true);
                Window window = addressDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.dimAmount = 0f; // 弹出对话框的时候背景不变暗
                addressDialog.show();
                break;
            case R.id.btn_address_default:
                setDefault();
                break;
            case R.id.btn_address_delete:
                delete();
                break;
        }
    }

    private void save() {
        mReceiver = mEtAddaddressReceiver.getText().toString().trim();
        mPhone = mEtAddaddressPhone.getText().toString().trim();
        mRegion = mTvAddaddressRegion.getText().toString().trim();
        mDetail = mEtAddaddressDetailaddress.getText().toString().trim();
        mZipcode = mEtAddaddressZipcode.getText().toString().toString().trim();
        if (mZipcode == "") {
            mZipcode = "8888888";
        }
        if (TextUtils.isEmpty(mReceiver)) {
            Toast.makeText(this, "收货人不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mPhone)) {
            Toast.makeText(this, "手机不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mRegion)) {
            Toast.makeText(this, "省市区不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mDetail)) {
            Toast.makeText(this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        submit();
    }

    private void submit() {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        String[] strings = mRegion.split("\\s");
        String city = strings[0];
        String province = strings[1];
        String area = strings[2];
        retrofit.create(AddAddressApi.class)
                .getAddresssave(mId, mToken, mUser_id, mReceiver, mPhone, city, province, area, mDetail, mZipcode)
                .enqueue(new Callback<AddAddressBean>() {
                    @Override
                    public void onResponse(Response<AddAddressBean> response, Retrofit retrofit) {
                        AddAddressBean body = response.body();
                        if (body.error == null) {
                            List<AddAddressBean.AddressListBean> addressList = body.getAddressList();
                            Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), body.error.msg.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setDefault() {
        save();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl.basUrl).build();
        retrofit.create(AddressDefaultApi.class)
                .getAddressDefault(mId, mToken, mUser_id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        ResponseBody body = response.errorBody();
                        if (body == null) {
                            Toast.makeText(getApplicationContext(), "设置成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "设置失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void delete() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl.basUrl).build();
        retrofit.create(AddressDeleteApi.class).deleteAddress(mId,mToken,mUser_id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        ResponseBody errorBody = response.errorBody();
                        if (errorBody == null) {
                            Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
