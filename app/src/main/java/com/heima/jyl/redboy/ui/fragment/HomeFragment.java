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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.BaseUrl;
import com.heima.jyl.redboy.api.HomeApi;
import com.heima.jyl.redboy.bean.HomeBean;
import com.heima.jyl.redboy.bean.HomeCategoryBean;
import com.heima.jyl.redboy.ui.activity.LimitBuyActivity;
import com.heima.jyl.redboy.ui.activity.NewProductActivity;
import com.heima.jyl.redboy.ui.activity.RecommendBrandActivity;
import com.heima.jyl.redboy.ui.activity.SearchScoreActivity;
import com.heima.jyl.redboy.ui.activity.TopicActivity;
import com.heima.jyl.redboy.ui.adapter.HomeAdapter;
import com.heima.jyl.redboy.ui.view.PageViewCreator;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by JING on 2016/6/15.
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private View mHomeView;
    private ListView mLv_home;
    private ConvenientBanner mConvenientBanner;
    private ArrayList<String> mData;
    private HomeAdapter mHomeAdapter;
    private ArrayList<HomeCategoryBean> mHomeCategories = new ArrayList<>();
    private ImageButton mBt_homesearch;
    private EditText mEt_home_search;
    private CardView mCardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mHomeView = inflater.inflate(R.layout.fragment_home, container, false);

        initView();
        initData();

        return mHomeView;
    }

    private void initData() {

        mData = new ArrayList<>();

        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(HomeApi.class).getHome().enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Response<HomeBean> response, Retrofit retrofit) {
                List<HomeBean.HomeTopicBean> homeTopic = response.body().getHomeTopic();
                for (HomeBean.HomeTopicBean bean : homeTopic) {
                    mData.add(BaseUrl.basUrl + bean.getPic());
                }
                Class cls;
                try {
                    cls = Class.forName("com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer");
                    ABaseTransformer transforemer = (ABaseTransformer) cls.newInstance();
                    mConvenientBanner.setPageTransformer(transforemer).startTurning(2000).setCanLoop(true);
                    PageViewCreator.create(mConvenientBanner, mData);
                } catch (Exception e) {
                    SnackBarUtils.SnackBarLong(mEt_home_search, "加载错误");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                SnackBarUtils.SnackBarLong(mEt_home_search, "网络连接失败");
            }
        });

        mHomeAdapter = new HomeAdapter(getContext(), mHomeCategories);
        mLv_home.setAdapter(mHomeAdapter);
        initHomeCategories();
    }

    private void initHomeCategories() {
        mHomeCategories.clear();
        mHomeCategories.add(new HomeCategoryBean(R.drawable.ic_main_time, "限时抢购"));
        mHomeCategories.add(new HomeCategoryBean(R.drawable.ic_main_paper, "促销快报"));
        mHomeCategories.add(new HomeCategoryBean(R.drawable.ic_main_new, "新品上架"));
        mHomeCategories.add(new HomeCategoryBean(R.drawable.ic_main_star, "热门单品"));
        mHomeCategories.add(new HomeCategoryBean(R.drawable.ic_main_brand, "推荐品牌"));

        mHomeAdapter.notifyDataSetChanged();
    }


    private void initView() {

        mCardView = (CardView) mHomeView.findViewById(R.id.home_card);
        mLv_home = (ListView) mHomeView.findViewById(R.id.lv_home);
        mConvenientBanner = (ConvenientBanner) mHomeView.findViewById(R.id.convenientBanner);
        mBt_homesearch = (ImageButton) mHomeView.findViewById(R.id.bt_Homesearch);
        mEt_home_search = (EditText) mHomeView.findViewById(R.id.et_home_search);

        mLv_home.setOnItemClickListener(this);
        mBt_homesearch.setOnClickListener(this);
        mEt_home_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    submit();
                }
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    mEt_home_search.setText("");
                }
                return true;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(getContext(), LimitBuyActivity.class));
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.left_enter, R.anim.right_exit);
                break;
            case 1:
                startActivity(new Intent(getContext(), TopicActivity.class));
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.left_enter, R.anim.right_exit);
                break;
            case 2:
                Intent intent1 = new Intent(getContext(), NewProductActivity.class);
                intent1.putExtra("titleName", "新品上架");
                startActivity(intent1);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.left_enter, R.anim.right_exit);
                break;
            case 3:
                Intent intent2 = new Intent(getContext(), NewProductActivity.class);
                intent2.putExtra("titleName", "热门单品");
                startActivity(intent2);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.left_enter, R.anim.right_exit);
                break;
            case 4:
                startActivity(new Intent(getContext(), RecommendBrandActivity.class));
                getActivity().overridePendingTransition(R.anim.left_enter, R.anim.right_exit);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        submit();
    }

    private void submit() {
        String text = mEt_home_search.getText().toString();
        if (!TextUtils.isEmpty(text)) {

            Intent intent = new Intent(getContext(), SearchScoreActivity.class);
            intent.putExtra("searchword", text);
            startActivity(intent);
        } else {
            mCardView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
//            SnackBarUtils.SnackBarLong(mCardView,"您的输入为空");
            Toast.makeText(getContext(),"您的输入为空", Toast.LENGTH_SHORT).show();
        }
    }
}
