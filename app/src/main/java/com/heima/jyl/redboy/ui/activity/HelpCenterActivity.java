package com.heima.jyl.redboy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.ui.view.Listitemview;

/**
 * Created by ting说你跳 on 2016/6/15.
 */
public class HelpCenterActivity extends Activity implements View.OnClickListener {


    private Listitemview mLtv_guide;
    private Listitemview mLtv_service;
    private Listitemview mLtv_transportation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpcenter);
        initView();
        setListener();
    }

    private void initView() {
        mLtv_guide = (Listitemview) findViewById(R.id.ltv_guide);
        mLtv_service = (Listitemview) findViewById(R.id.ltv_service);
        mLtv_transportation = (Listitemview) findViewById(R.id.ltv_transportation);
    }

    private void setListener() {

        mLtv_guide.setOnClickListener(this);
        mLtv_service.setOnClickListener(this);
        mLtv_transportation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ltv_guide:
                startActivity(new Intent(this,ShopGuideActivity.class));
                break;
            case R.id.ltv_service:
                startActivity(new Intent(this,ServiceActivity.class));
                break;
            case R.id.ltv_transportation:
                startActivity(new Intent(this,TransportationActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
