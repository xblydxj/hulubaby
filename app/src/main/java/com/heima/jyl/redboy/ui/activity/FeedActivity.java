package com.heima.jyl.redboy.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.heima.jyl.redboy.R;

/**
 * Created by ting说你跳 on 2016/6/16.
 */
public class FeedActivity extends Activity implements View.OnClickListener {
    private Button mBtnLeft;
    private FloatingActionButton mBtnRight;
    private EditText mEdFeedAdvice;
    private EditText mEdFeedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        initView();
        setListener();
    }

    private void initView() {
        mBtnRight = (FloatingActionButton) findViewById(R.id.feed_submit);
        mEdFeedAdvice = (EditText) findViewById(R.id.ed_feed_advice);
        mEdFeedContact = (EditText) findViewById(R.id.ed_feed_contact);
        System.out.println(mEdFeedAdvice.getText().toString().trim());
    }

    private void setListener() {
        mBtnRight.setOnClickListener(this);
        mEdFeedAdvice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mEdFeedAdvice.getText().toString().trim())) {
                    mEdFeedAdvice.setGravity(Gravity.CENTER);
                }else {
                    mEdFeedAdvice.setGravity(Gravity.LEFT);
                }
            }
        });

        mEdFeedContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mEdFeedContact.getText().toString().trim())) {
                    mEdFeedContact.setGravity(Gravity.CENTER);
                }else {
                    mEdFeedContact.setGravity(Gravity.LEFT);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feed_submit:
                if (mEdFeedAdvice.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "意见不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this, "提交成功,感谢您的意见", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
