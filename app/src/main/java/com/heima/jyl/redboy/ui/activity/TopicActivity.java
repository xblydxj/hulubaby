package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.STopicApi;
import com.heima.jyl.redboy.bean.STopicBean;
import com.heima.jyl.redboy.ui.adapter.TopicAdapter;
import com.heima.jyl.redboy.utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TopicActivity extends AppCompatActivity{

    private ListView lv_topic;
    private ArrayList<STopicBean.TopicBean> mData = new ArrayList<>();
    private TopicAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(STopicApi.class).getSTopic(1, 3).enqueue(new Callback<STopicBean>() {
            @Override
            public void onResponse(Response<STopicBean> response, Retrofit retrofit) {
                List<STopicBean.TopicBean> topics = response.body().getTopic();
                mData.addAll(topics);
                mAdapter = new TopicAdapter(TopicActivity.this, mData);
                lv_topic.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    private void initView() {
        lv_topic = (ListView) findViewById(R.id.lv_topic);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.right_enter, R.anim.left_exit);
    }
}
