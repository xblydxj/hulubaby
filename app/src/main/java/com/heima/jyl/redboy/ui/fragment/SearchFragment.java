package com.heima.jyl.redboy.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.SerchRecommendApi;
import com.heima.jyl.redboy.bean.SearchreCommendBean;
import com.heima.jyl.redboy.ui.activity.SearchScoreActivity;
import com.heima.jyl.redboy.ui.adapter.SearchAdapter;
import com.heima.jyl.redboy.utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class SearchFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private View mSearch_view;
    public ListView mLvHotSearch;
    public ListView mLvHistorySearch;
    public Button mBt_search;
    public EditText mEt_search;
    public SearchAdapter mAdapter1;
    public SearchAdapter mAdapter2;
    public ArrayList<String> keyslist = new ArrayList<String>();
    public List<String> productList;
    private CardView mCardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mSearch_view = inflater.inflate(R.layout.fragmennt_search, container, false);
        initView();
        initData();
        return mSearch_view;
    }

    private void initData() {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(SerchRecommendApi.class).
                getSearchreCommendBean().
                enqueue(new Callback<SearchreCommendBean>() {

                    @Override
                    public void onResponse(Response<SearchreCommendBean> response, Retrofit retrofit) {
                        productList = response.body().getSearchKeywords();

                        initAdapter(productList, keyslist);

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(getContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
                    }
                });

    }


    private void initView() {
        mCardView = (CardView) mSearch_view.findViewById(R.id.search_card);
        mBt_search = (Button) mSearch_view.findViewById(R.id.bt_search);
        mEt_search = (EditText) mSearch_view.findViewById(R.id.et_search);
        mLvHotSearch = (ListView) mSearch_view.findViewById(R.id.lv_hot_search);
        mLvHistorySearch = (ListView) mSearch_view.findViewById(R.id.lv_history_search);

        mBt_search.setOnClickListener(this);
        mLvHotSearch.setOnItemClickListener(this);
        mLvHistorySearch.setOnItemClickListener(this);
        mEt_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    skip(mEt_search.getText().toString().trim());
                }
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    mEt_search.setText("");
                }
                return true;
            }
        });

    }

    public void initAdapter(List<String> productList, ArrayList<String> keyslist) {
        mAdapter1 = new SearchAdapter(getContext(), productList);

        mAdapter2 = new SearchAdapter(getContext(), keyslist);

        mLvHotSearch.setAdapter(mAdapter1);
        mLvHistorySearch.setAdapter(mAdapter2);

    }

    public void getHistory(String text) {

        if (!keyslist.contains(text)) {
            keyslist.add(text);
        }

    }

    @Override
    public void onClick(View v) {

        String text = mEt_search.getText().toString();

        if (!TextUtils.isEmpty(text)) {

            getHistory(text);
        }

        initAdapter(productList, keyslist);

        skip(text);
    }

    public void skip(String keyword) {

        if (!TextUtils.isEmpty(keyword)) {

            Intent intent = new Intent(getContext(), SearchScoreActivity.class);
            intent.putExtra("searchword", keyword);
            startActivity(intent);
        } else {
            mCardView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
            Toast.makeText(getContext(), "您的输入为空", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {

            case R.id.lv_hot_search:

                String click_hotsearch_word = productList.get(position);

                skip(click_hotsearch_word);

                break;

            case R.id.lv_history_search:

                String click_historysearch_word = keyslist.get(position);

                skip(click_historysearch_word);

                break;

        }


    }
}

