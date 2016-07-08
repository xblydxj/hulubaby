package com.heima.jyl.redboy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.utils.PackageUtil;

/**
 * Created by ting说你跳 on 2016/6/20.
 */
public class SplashActivity extends Activity {
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_splash);
        String versionName = PackageUtil.getVersionName(this);
        textView.setText("Version:"+versionName);
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }
}
