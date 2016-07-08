package com.heima.jyl.redboy.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.SBrandApi;
import com.heima.jyl.redboy.bean.SBrandBean;
import com.heima.jyl.redboy.ui.adapter.RecommendBrandAdapter;
import com.heima.jyl.redboy.utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RecommendBrandActivity extends AppCompatActivity{

    private GridView mGd_motherZone;
    private SimpleAdapter sim_adapter;
    private ArrayList<SBrandBean.BrandBean> mData = new ArrayList<>();
    private List<Map<String, Object>> data_list = new ArrayList<>();
    private ArrayList<SBrandBean.BrandBean.ValueBean> mFoodDatas = new ArrayList<>();
    private ArrayList<SBrandBean.BrandBean.ValueBean> mMotherDatas = new ArrayList<>();
    private GridView mGd_foodZone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_brand_product);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(SBrandApi.class).getSBrand().enqueue(new Callback<SBrandBean>() {
            @Override
            public void onResponse(Response<SBrandBean> response, Retrofit retrofit) {
                List<SBrandBean.BrandBean> brand = response.body().getBrand();

                mData.addAll(brand);
                System.out.println("mData"+ brand.toString());

                for (SBrandBean.BrandBean bean : mData) {
                    if (bean.getKey().equals("营养食品")) {

                            mFoodDatas.addAll(bean.getValue());

                    } else if (bean.getKey().equals("孕妈专区")) {

                            mMotherDatas.addAll(bean.getValue());
                    }
                }
                RecommendBrandAdapter foodadapter = new RecommendBrandAdapter(RecommendBrandActivity.this,mFoodDatas);
                RecommendBrandAdapter motherAdapter = new RecommendBrandAdapter(RecommendBrandActivity.this,mMotherDatas);
                mGd_foodZone.setAdapter(foodadapter);
                mGd_motherZone.setAdapter(motherAdapter);
            }
            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    private void initView() {
        mGd_motherZone = (GridView) findViewById(R.id.gd_mother);
        mGd_foodZone = (GridView) findViewById(R.id.gd_food);

    }


}
