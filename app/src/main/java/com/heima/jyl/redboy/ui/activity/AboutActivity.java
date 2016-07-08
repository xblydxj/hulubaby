package com.heima.jyl.redboy.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.utils.PackageUtil;

/**
 * Created by ting说你跳 on 2016/6/16.
 */
public class AboutActivity extends Activity implements View.OnClickListener {
    private TextView mTvVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        initData();
    }

    private void initView() {
        mTvVersion = (TextView) findViewById(R.id.tv_version);
    }

    private void initData() {
        mTvVersion.setText("版本号:"+ PackageUtil.getVersionName(this));
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
