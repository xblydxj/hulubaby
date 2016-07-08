package com.heima.jyl.redboy.ui.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.CartApi;
import com.heima.jyl.redboy.api.CartDeleteApi;
import com.heima.jyl.redboy.bean.CarBean;
import com.heima.jyl.redboy.ui.activity.CenterActivity;
import com.heima.jyl.redboy.ui.activity.MainActivity;
import com.heima.jyl.redboy.ui.activity.ProductDetailActivity;
import com.heima.jyl.redboy.ui.adapter.ShopAdapter;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;
import com.heima.jyl.redboy.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @创建者 种金币
 * @创建时间 2016/6/15 8:55
 * @描述 有货的购物车
 */
public class ShopFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private boolean isEdit = false;
    private View              mCarView;
    private SwipeMenuListView mShopLv;
    private List<CarBean.CartBean> isProduct = new ArrayList<CarBean.CartBean>();
    private List<CarBean.CartBean> unProduct = new ArrayList<CarBean.CartBean>();
    private RelativeLayout     mLlEmpty;
    private LinearLayout       mLlFull;
    private ShopAdapter        mAdapter;
    private String             TOKEN;
    private String             USER_ID;
    private FloatingActionMenu mCart_fab_menu;
    private FloatingActionMenu mCart_fab_menu2;
    private Button             mBtnAll;
    private Button             mBtnReverse;
    private Button             mBtnNo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //将布局文件转换为View对象
        mCarView = inflater.inflate(R.layout.fragment_shop, container, false);
        init();
        //返回View对象
        return mCarView;
    }

    private void init() {
        //获取控件
        initView();
        //获取登陆信息
        getUser();

    }

    private void initView() {
        mLlEmpty = (RelativeLayout) mCarView.findViewById(R.id.shop_ll_empty);
        mLlFull = (LinearLayout) mCarView.findViewById(R.id.shop_ll_full);
        mCart_fab_menu = (FloatingActionMenu) mCarView.findViewById(R.id.cart_fab_menu);
        mCart_fab_menu2 = (FloatingActionMenu) mCarView.findViewById(R.id.cart_fab_menu2);
        FloatingActionButton mBtnEdit = (FloatingActionButton) mCarView.findViewById(R.id.brand_fab_edit);

        FloatingActionButton mBtnPay = (FloatingActionButton) mCarView.findViewById(R.id.brand_fab_settle);
        FloatingActionButton mBtnBack = (FloatingActionButton) mCarView.findViewById(R.id.brand_fab_over);
        FloatingActionButton mBtnDel = (FloatingActionButton) mCarView.findViewById(R.id.brand_fab_delete);
        Button mButton = (Button) mCarView.findViewById(R.id.button);
        mShopLv = (SwipeMenuListView) mCarView.findViewById(R.id.shop_lv);
        mBtnAll = (Button) mCarView.findViewById(R.id.shop_btn_all);
        mBtnReverse = (Button) mCarView.findViewById(R.id.shop_btn_reverse);
        mBtnNo = (Button) mCarView.findViewById(R.id.shop_btn_no);
        //条目点击
        mShopLv.setOnItemClickListener(this);
        //按钮点击
        mBtnEdit.setOnClickListener(this);
        mBtnPay.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
        mButton.setOnClickListener(this);

        handleSwipeEvent();

        mBtnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CarBean.CartBean data : isProduct) {
                    data.setCheck(true);
                }
                mAdapter.notifyDataSetChanged();
                setTitle();
            }
        });
        mBtnReverse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (CarBean.CartBean data : isProduct) {
                    data.setCheck(!data.isCheck());
                }
                mAdapter.notifyDataSetChanged();
                setTitle();
            }
        });
        mBtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CarBean.CartBean data : isProduct) {
                    data.setCheck(false);
                }
                mAdapter.notifyDataSetChanged();
                setTitle();
            }
        });
    }

    private void handleSwipeEvent() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        mShopLv.setMenuCreator(creator);
        mShopLv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        CarBean.CartBean data = isProduct.get(position - 1);
                        delCart(data.getId());
                        isProduct.remove(position - 1);
                        mAdapter.notifyDataSetChanged();
                        if (isProduct.size() == 0) {
                            mLlEmpty.setVisibility(View.VISIBLE);
                            mLlFull.setVisibility(View.INVISIBLE);
                            mCart_fab_menu.setVisibility(View.INVISIBLE);
                            mCart_fab_menu2.setVisibility(View.INVISIBLE);
                        }
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        mShopLv.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
    }

    //获取服务器数据
    private void initData() {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(CartApi.class).getCart(USER_ID, TOKEN)
                .enqueue(new Callback<CarBean>() {
                    @Override
                    public void onResponse(Response<CarBean> response, Retrofit retrofit) {
                        if (response.body().error == null) {
                            List<CarBean.CartBean> cartBeanList = response.body().getCart();
                            List<CarBean.CartBean> isProductList = new ArrayList<CarBean.CartBean>();
                            List<CarBean.CartBean> unProductList = new ArrayList<CarBean.CartBean>();
                            for (CarBean.CartBean bean : cartBeanList) {
                                if (TextUtils.equals("0", bean.getState())) {
                                    unProductList.add(bean);
                                } else {
                                    isProductList.add(bean);
                                }
                            }
                            isProduct.clear();
                            unProduct.clear();
                            isProduct.addAll(isProductList);
                            unProduct.addAll(unProductList);
                            if (cartBeanList.isEmpty()) {
                                mLlEmpty.setVisibility(View.VISIBLE);
                                mCart_fab_menu.setVisibility(View.INVISIBLE);
                                mCart_fab_menu2.setVisibility(View.INVISIBLE);
                                mLlFull.setVisibility(View.INVISIBLE);
                            } else {
                                mLlFull.setVisibility(View.VISIBLE);
                                mCart_fab_menu.setVisibility(View.VISIBLE);
                                mCart_fab_menu2.setVisibility(View.VISIBLE);
                                mLlEmpty.setVisibility(View.INVISIBLE);
                                //刷新ListView
                                updateListView();
                            }
                        } else {
                            SnackBarUtils.SnackBarLong(mCart_fab_menu, "亲，请重新登录哦！");
                            //                            Toast.makeText(getContext(), "亲，请重新登录哦", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    //更新ListView
    private void updateListView() {
        mAdapter = new ShopAdapter(getContext(), isProduct, unProduct, this.isEdit, mCarView, USER_ID, TOKEN);
        mShopLv.setAdapter(mAdapter);
    }

    //条目点击事件，进入商品详情
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CarBean.CartBean itemBean = (CarBean.CartBean) parent.getItemAtPosition(position);
        if (itemBean == null) {
            return;
        }
        Intent intent = new Intent(parent.getContext(), ProductDetailActivity.class);
        intent.putExtra("pId", itemBean.getPid());
        startActivity(intent);
    }

    //Title的点击
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.brand_fab_edit:
                isEdit = true;
                change();
                mCart_fab_menu.close(true);
                mCart_fab_menu2.setElevation(7.0f);
                mCart_fab_menu.setElevation(6.0f);
                break;
            case R.id.brand_fab_settle:
                pay();
                mCart_fab_menu.close(true);
                mCart_fab_menu.setElevation(7.0f);
                mCart_fab_menu2.setElevation(6.0f);
                break;
            case R.id.brand_fab_over:
                back();
                setTitle();
                mCart_fab_menu2.close(true);
                mCart_fab_menu.setElevation(7.0f);
                mCart_fab_menu2.setElevation(6.0f);
                break;
            case R.id.brand_fab_delete:
                delCart();
                initData();
                mCart_fab_menu2.close(true);
                mCart_fab_menu2.setElevation(7.0f);
                mCart_fab_menu.setElevation(6.0f);
                break;
            case R.id.button:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.BTChange(2);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.fl_container, new BrandFragment())
                        .commit();
                break;
        }
    }

    //启动生成订单页面
    private void pay() {
        Intent intent = new Intent(getContext(), CenterActivity.class);
        int i = 0;
        for (CarBean.CartBean data : isProduct) {
            if (data.isCheck()) {
                i = i + 1;
                intent.putExtra(i + "", data);
            }
        }
        if (i == 0) {
            Toast.makeText(getContext(), "亲，你还没有选货哦！", Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(intent);
    }

    //返回刷新
    private void back() {
        isEdit = false;
        change();
    }

    //删除购物车条目，不管删除还是支付，条目都消失
    private void delCart() {
        StringBuffer cid = new StringBuffer();
        for (CarBean.CartBean data : isProduct) {
            if (data.isCheck()) {
                cid.append(data.getId() + "-");
            }
        }
        if (cid.length() != 0) {
            String cids = cid.substring(0, cid.length() - 1);
            delCart(cids);
            initData();
        } else {
            Toast.makeText(getContext(), "亲，你还没有选货哦！", Toast.LENGTH_SHORT).show();
            mLlEmpty.setVisibility(View.VISIBLE);
            mLlFull.setVisibility(View.INVISIBLE);
            mCart_fab_menu.setVisibility(View.INVISIBLE);
            mCart_fab_menu2.setVisibility(View.INVISIBLE);
            return;
        }
    }

    //改变title的显示属性,刷新ListView
    private void change() {
        mCart_fab_menu.setVisibility(isEdit ? View.VISIBLE : View.INVISIBLE);
        mCart_fab_menu2.setVisibility(isEdit ? View.INVISIBLE : View.VISIBLE);
        initData();
    }

    //删除服务器中购物车信息
    private void delCart(String cids) {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(CartDeleteApi.class).deleteCart(USER_ID, TOKEN, cids)
                .enqueue(new Callback<CarBean>() {
                    @Override
                    public void onResponse(Response<CarBean> response, Retrofit retrofit) {
                        if (response.errorBody() == null) {
                        } else {
                            Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                    }
                });
    }

    //设置总金额和总数量
    private void setTitle() {
        TextView tvKids = (TextView) mCarView.findViewById(R.id.shop_tv_kids);
        TextView tvNum = (TextView) mCarView.findViewById(R.id.shop_tv_num);
        TextView tvSum = (TextView) mCarView.findViewById(R.id.shop_tv_sum);
        int kids = 0;
        int sum = 0;
        int num = 0;
        for (CarBean.CartBean data : isProduct) {
            if (data.isCheck()) {
                num = num + Integer.valueOf(data.getPnum());
                kids = kids + 1;
                sum = sum + Integer.valueOf(data.getPnum()) * Integer.valueOf(data.getProductPrice());
            }
        }
        tvSum.setText(sum + ".00");
        tvNum.setText(num + "");
        tvKids.setText(kids + "");
    }


    //获取用户信息
    private void getUser() {
        USER_ID = SPUtils.getString(getContext(), "user_id");
        TOKEN = SPUtils.getString(getContext(), "token");
    }

    @Override
    public void onResume() {
        super.onResume();
        if ("".equals(USER_ID)) {
            Toast.makeText(getContext(), "亲，你还没登陆哦！", Toast.LENGTH_SHORT).show();
            mLlEmpty.setVisibility(View.VISIBLE);
            mLlFull.setVisibility(View.INVISIBLE);
            mCart_fab_menu.setVisibility(View.INVISIBLE);
            mCart_fab_menu2.setVisibility(View.INVISIBLE);
            return;
        }
        back();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}

