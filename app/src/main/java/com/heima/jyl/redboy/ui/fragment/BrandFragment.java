package com.heima.jyl.redboy.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.SCategoryApi;
import com.heima.jyl.redboy.bean.SCategoryBean;
import com.heima.jyl.redboy.ui.activity.SearchScoreActivity;
import com.heima.jyl.redboy.ui.adapter.BrandCategory1Adapter;
import com.heima.jyl.redboy.ui.adapter.BrandCategory2Adapter;
import com.heima.jyl.redboy.utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class BrandFragment extends Fragment implements View.OnClickListener {
    private ListView brand_lv_1;
    private ListView brand_lv_2;
    private ListView brand_lv_3;
    private View mView;
    private int mLevel1;
    private static final int SCALE_DELAY = 30;

    private List<SCategoryBean.CategoryBean> category0 = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> category1 = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> category2 = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> category3 = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> category11 = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> category12 = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> category13 = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> category21 = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> category22 = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> category31 = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> category32 = new ArrayList<>();


    private List<SCategoryBean.CategoryBean> category_blank = new ArrayList<>();
    private List<SCategoryBean.CategoryBean> mNewCategory;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragmennt_brand, container, false);
        initView();
        initData();
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//        public void call(Subscriber<? super String> subscriber) {
//            subscriber.onStart();
//        }
//    }).doOnNext(new Action1<String>() {
//        @Override
//        public void call(String s) {
//
//        }
//    })
        return mView;

    }

    private void initData() {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(SCategoryApi.class).getCategory(0).enqueue(new Callback<SCategoryBean>() {
            @Override
            public void onResponse(Response<SCategoryBean> response, Retrofit retrofit) {
                String responseCode = response.body().getResponse();
                if ("category".equals(responseCode)) {
                    List<SCategoryBean.CategoryBean> category = response.body().getCategory();
                    initCategory(category);
                } else {
//                    SnackBarUtils.SnackBarLong(brand_back, "发生了一点未知的错误！");
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
//                SnackBarUtils.SnackBarLong(brand_back, "网络连接失败！");
            }
        });
    }


    private void initCategory(List<SCategoryBean.CategoryBean> category) {
        category0.clear();
        category1.clear();
        category2.clear();
        category3.clear();
        category11.clear();
        category12.clear();
        category13.clear();
        category21.clear();
        category22.clear();
        category31.clear();
        category32.clear();
        for (int i = 0; i < category.size(); i++) {
            int parentId = category.get(i).getParentId();
            switch (parentId) {
                case 0:
                    category0.add(category.get(i));
                    break;
                case 1:
                    category1.add(category.get(i));
                    break;
                case 2:
                    category2.add(category.get(i));
                    break;
                case 3:
                    category3.add(category.get(i));
                    break;
                case 11:
                    category11.add(category.get(i));
                    break;
                case 12:
                    category12.add(category.get(i));
                    break;
                case 13:
                    category13.add(category.get(i));
                    break;
                case 21:
                    category21.add(category.get(i));
                    break;
                case 22:
                    category22.add(category.get(i));
                    break;
                case 31:
                    category31.add(category.get(i));
                    break;
                case 32:
                    category32.add(category.get(i));
                    break;
                default:
                    break;
            }
        }
        BrandCategory1Adapter adapter = new BrandCategory1Adapter(getContext(), category0);
        brand_lv_1.setAdapter(adapter);
    }


    private void initView() {
        FloatingActionButton fab = (FloatingActionButton) mView.findViewById(R.id.brand_fab_back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brand_lv_2.setAdapter(new BrandCategory1Adapter(getContext(), category_blank));
                brand_lv_3.setAdapter(new BrandCategory1Adapter(getContext(), category_blank));
                BrandCategory1Adapter adapter = new BrandCategory1Adapter(getContext(), category0);
                brand_lv_1.setAdapter(adapter);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        brand_lv_1 = (ListView) mView.findViewById(R.id.brand_lv_1);
        brand_lv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BrandCategory2Adapter adapter2 = null;
                switch (position) {
                    case 0:
                        adapter2 = new BrandCategory2Adapter(getContext(), category1);
                        break;
                    case 1:
                        adapter2 = new BrandCategory2Adapter(getContext(), category2);
                        break;
                    case 2:
                        adapter2 = new BrandCategory2Adapter(getContext(), category3);
                        break;
                    default:
                        break;
                }
                mLevel1 = position;
                brand_lv_1.setAdapter(new BrandCategory1Adapter(getContext(), category_blank));
                brand_lv_3.setAdapter(new BrandCategory1Adapter(getContext(), category_blank));
                brand_lv_2.setAdapter(adapter2);
            }
        });
        brand_lv_2 = (ListView) mView.findViewById(R.id.brand_lv_2);
        brand_lv_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mNewCategory = new ArrayList<>();
                switch (mLevel1) {
                    case 0:
                        switch (position) {
                            case 0:
                                mNewCategory = category11;
                                break;
                            case 1:
                                mNewCategory = category12;
                                break;
                            case 2:
                                mNewCategory = category13;
                                break;
                        }
                        break;
                    case 1:
                        switch (position) {
                            case 0:
                                mNewCategory = category31;
                                break;
                            case 1:
                                mNewCategory = category32;
                                break;
                        }
                        break;
                    case 2:
                        switch (position) {
                            case 0:
                                mNewCategory = category21;
                                break;
                            case 1:
                                mNewCategory = category22;
                                break;
                        }
                        break;
                }
                BrandCategory2Adapter adapter3 = new BrandCategory2Adapter(getContext(), mNewCategory);
                brand_lv_2.setAdapter(new BrandCategory1Adapter(getContext(), category_blank));
                brand_lv_1.setAdapter(new BrandCategory1Adapter(getContext(), category_blank));
                brand_lv_3.setAdapter(adapter3);
            }

        });
        brand_lv_3 = (ListView) mView.findViewById(R.id.brand_lv_3);
        brand_lv_3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id1 = mNewCategory.get(position).getId();
                Intent intent = new Intent(getContext(), SearchScoreActivity.class);

                switch (id1){
                    case 111:
                    case 112:
                    case 121:
                    case 131:
                        intent.putExtra("searchword","孕妇");
                        break;
                    case 211:
                    case 221:
                        intent.putExtra("searchword", "男士");
                        break;
                    case 311:
                    case 321:
                    default:
                        intent.putExtra("searchword", "玩具");
                        break;

                }
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {

    }
}
