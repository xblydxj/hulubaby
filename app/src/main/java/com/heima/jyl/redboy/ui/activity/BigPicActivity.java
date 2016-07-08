package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.BaseUrl;

import java.util.ArrayList;

public class BigPicActivity extends AppCompatActivity {

    private ArrayList<String> mBigPic;
    private ImageView iv_bigpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_pic);
        initView();
        Intent intent = getIntent();
        mBigPic = intent.getStringArrayListExtra("bigPic");
        Glide.with(this).load(BaseUrl.basUrl+mBigPic.get(0)).placeholder(R.drawable.ic_default_adimage).into(iv_bigpic);
    }

    private void initView() {
        iv_bigpic = (ImageView) findViewById(R.id.iv_bigpic);
    }
}
