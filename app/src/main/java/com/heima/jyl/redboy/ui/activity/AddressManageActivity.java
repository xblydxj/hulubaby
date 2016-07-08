package com.heima.jyl.redboy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.AddressManageApi;
import com.heima.jyl.redboy.api.BaseUrl;
import com.heima.jyl.redboy.bean.AddressBean;
import com.heima.jyl.redboy.bean.BackAddressBean;
import com.heima.jyl.redboy.ui.adapter.AddressAdapter;
import com.heima.jyl.redboy.utils.SPUtils;

import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ting说你跳 on 2016/6/17.
 */
public class AddressManageActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView                          mLlAddress;
    private FloatingActionButton              mAdd;
    private List<AddressBean.AddressListBean> mList;
    private AddressAdapter                    mAdapter;
    private LinearLayout                      mLinearLayout;
    private Toolbar                           mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressmanage);
        initView();
        initData();
        setListener();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mToolbar.setTitle("地址管理");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.manager_address_toolbar);
        mLlAddress = (ListView) findViewById(R.id.ll_address);
        mAdd = (FloatingActionButton) findViewById(R.id.add_add);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_addressmanage_empty);
    }


    private void initData() {
        String token = SPUtils.getString(this, "token");
        String user_id = SPUtils.getString(this, "user_id");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl.basUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(AddressManageApi.class)
                .getAddressList(user_id, token)
                .enqueue(new Callback<AddressBean>() {
                    @Override
                    public void onResponse(Response<AddressBean> response, Retrofit retrofit) {
                        AddressBean body = response.body();
                        if (body.error == null) {
                            mList = body.getAddressList();
                            mAdapter = new AddressAdapter(getApplicationContext(), mList);
                            mLlAddress.setAdapter(mAdapter);
                            mLlAddress.setEmptyView(mLinearLayout);
                        } else {
                            Toast.makeText(getApplicationContext(), body.error.msg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setListener() {
        mAdd.setOnClickListener(this);
        mLlAddress.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_add:
                startActivity(new Intent(this, AddAddressActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String where = getIntent().getStringExtra("center");
        AddressBean.AddressListBean item = mAdapter.getItem(position);
        int adressId = item.getId();
        if ("center".equals(where)) {
            backAddress(item);
            finish();
        } else {
            Intent intent = new Intent(this, AddAddressActivity.class);
            intent.putExtra("where", "modify");
            String name = item.getName();
            String phoneNumber = item.getPhoneNumber();
            String address = item.getProvince().replace("0", "") + "\t" + item.getCity().replace("0", "") + "\t" + item.getAddressArea();
            String addressDetail = item.getAddressDetail();
            intent.putExtra("id", adressId);
            intent.putExtra("name", name);
            intent.putExtra("phoneNumber", phoneNumber);
            intent.putExtra("address", address);
            intent.putExtra("addressDetail", addressDetail);
            startActivity(intent);
        }
    }

    //返回地址
    private void backAddress(AddressBean.AddressListBean item) {
        BackAddressBean bean = new BackAddressBean();
        bean.setAddress(item.getProvince().replace("0", "") + item.getCity().replace("0", "") + item.getAddressArea());
        bean.setName(item.getName());
        bean.setAddressId(item.getId());
        bean.setPhone(item.getPhoneNumber());
        Intent intent = new Intent();
        intent.putExtra("AddressBean", bean);
        setResult(201, intent);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
