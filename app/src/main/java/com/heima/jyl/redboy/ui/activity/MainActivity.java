package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.ui.fragment.BrandFragment;
import com.heima.jyl.redboy.ui.fragment.HomeFragment;
import com.heima.jyl.redboy.ui.fragment.MoreFragment;
import com.heima.jyl.redboy.ui.fragment.SearchFragment;
import com.heima.jyl.redboy.ui.fragment.ShopFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.stephentuso.welcome.WelcomeScreenHelper;
import com.stephentuso.welcome.ui.WelcomeActivity;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private long exitTime = 0;
    private ArrayList<Fragment> mFragments;

    private WelcomeScreenHelper welcomeScreen;
    private int index;
    private BottomBar mBottomBar;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initIntent();
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        welcomeScreen = new WelcomeScreenHelper(this, MyWelcomeActivity.class);
        welcomeScreen.show(savedInstanceState);
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noNavBarGoodness();
        mBottomBar.ignoreNightMode();
        initBottomBar();
    }

    private void initIntent() {
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 5);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == WelcomeScreenHelper.DEFAULT_WELCOME_SCREEN_REQUEST) {
            String welcomeKey = data.getStringExtra(WelcomeActivity.WELCOME_SCREEN_KEY);

            if (resultCode == RESULT_OK) {
//                Toast.makeText(getApplicationContext(), welcomeKey + " 欢迎使用", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(getApplicationContext(), welcomeKey + " canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BTChange(int index){
        switch (index) {
            case 0:
                mToolbar.setBackgroundColor(getResources().getColor(R.color.md_red_500_color_code));
                mToolbar.setTitle("");
                mBottomBar.selectTabAtPosition(0,true);

                break;
            case 1:
                mToolbar.setBackgroundResource(R.color.md_blue_600_color_code);
                mToolbar.setTitle("搜索");
                mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                mBottomBar.selectTabAtPosition(1,true);
                break;
            case 2:
                mToolbar.setBackgroundResource(R.color.md_brown_500_color_code);
                mToolbar.setTitle("商品列表");
                mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                mBottomBar.selectTabAtPosition(2,true);
                break;
            case 3:
                mToolbar.setBackgroundResource(R.color.md_deep_orange_500_color_code);
                mToolbar.setTitle("购物车");
                mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                mBottomBar.selectTabAtPosition(3,true);
                break;
            case 4:
                mToolbar.setBackgroundResource(R.color.md_teal_500_color_code);
                mToolbar.setTitle("更多");
                mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                mBottomBar.selectTabAtPosition(4,true);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

    private void initBottomBar() {

        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItem_home) {
                    changeFragment(0);
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.md_red_500_color_code));
                    mToolbar.setTitle("");
                } else if (menuItemId == R.id.bottomBarItem_search) {
                    changeFragment(1);
                    mToolbar.setBackgroundResource(R.color.md_blue_600_color_code);
                    mToolbar.setTitle("搜索");
                    mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                } else if (menuItemId == R.id.bottomBarItem_brand) {
                    changeFragment(2);
                    mToolbar.setBackgroundResource(R.color.md_brown_500_color_code);
                    mToolbar.setTitle("商品列表");
                    mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                } else if (menuItemId == R.id.bottomBarItem_cart) {
                    changeFragment(3);
                    mToolbar.setBackgroundResource(R.color.md_deep_orange_500_color_code);
                    mToolbar.setTitle("购物车");
                    mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                } else if (menuItemId == R.id.bottomBarItem_more) {
                    changeFragment(4);
                    mToolbar.setBackgroundResource(R.color.md_teal_500_color_code);
                    mToolbar.setTitle("更多");
                    mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItem_home) {
//                    changeFragment(0);
                } else if (menuItemId == R.id.bottomBarItem_search) {
//                    changeFragment(1);
                } else if (menuItemId == R.id.bottomBarItem_brand) {
//                    changeFragment(2);
                } else if (menuItemId == R.id.bottomBarItem_cart) {
//                    changeFragment(3);
                } else if (menuItemId == R.id.bottomBarItem_more) {
//                    changeFragment(4);
                }
            }
        });


        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.md_red_500_color_code));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.md_blue_600_color_code));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.md_brown_500_color_code));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.md_deep_orange_500_color_code));
        mBottomBar.mapColorForTab(4, ContextCompat.getColor(this, R.color.md_teal_500_color_code));
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        //创建出每个按钮的界面
        HomeFragment homeFragment = new HomeFragment();
        SearchFragment searchFragment = new SearchFragment();
        BrandFragment brandFragment = new BrandFragment();
        ShopFragment shoppingFragment = new ShopFragment();
        MoreFragment moreFragment = new MoreFragment();

        mFragments.add(homeFragment);
        mFragments.add(searchFragment);
        mFragments.add(brandFragment);
        mFragments.add(shoppingFragment);
        mFragments.add(moreFragment);

        changeFragment(0);
    }

    private void changeFragment(int i) {
        Fragment fragment = mFragments.get(i);
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fl_container, fragment)
                .commit();
    }


    @Override
    protected void onResume() {
        super.onResume();

        switch (index) {
            case 0:
                changeFragment(0);
                mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.md_red_500_color_code));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.md_red_500_color_code));
                mToolbar.setTitle("");
                mBottomBar.selectTabAtPosition(0,true);
                break;
            case 1:
                changeFragment(1);
                mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.md_blue_600_color_code));
                mToolbar.setBackgroundResource(R.color.md_blue_600_color_code);
                mToolbar.setTitle("搜索");
                mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                mBottomBar.selectTabAtPosition(1,true);
                break;
            case 2:
                changeFragment(2);
                mToolbar.setBackgroundResource(R.color.md_brown_500_color_code);
                mToolbar.setTitle("商品列表");
                mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.md_brown_500_color_code));
                mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                mBottomBar.selectTabAtPosition(2,true);
                break;
            case 3:
                changeFragment(3);
                mToolbar.setBackgroundResource(R.color.md_deep_orange_500_color_code);
                mToolbar.setTitle("购物车");
                mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                mBottomBar.selectTabAtPosition(3,true);
                break;
            case 4:
                changeFragment(4);
                mToolbar.setBackgroundResource(R.color.md_teal_500_color_code);
                mToolbar.setTitle("更多");
                mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
                mBottomBar.selectTabAtPosition(4,true);
                mBottomBar.mapColorForTab(4, ContextCompat.getColor(this, R.color.md_teal_500_color_code));
                break;
            default:
                break;

        }
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.md_red_500_color_code));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.md_blue_600_color_code));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.md_brown_500_color_code));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.md_deep_orange_500_color_code));
        mBottomBar.mapColorForTab(4, ContextCompat.getColor(this, R.color.md_teal_500_color_code));
        index = 5;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

}

