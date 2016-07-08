package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.bean.SProductBean;
import com.heima.jyl.redboy.bean.SearchBean;
import com.heima.jyl.redboy.dbdao.User;
import com.heima.jyl.redboy.dbdao.UserDBOpenHelper;
import com.heima.jyl.redboy.ui.adapter.HistoryRecordAdapter;
import com.heima.jyl.redboy.utils.SnackBarUtils;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit.Retrofit;

public class LookRecordActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private SwipeMenuListView        mLv_lookScore;
    private Button                   mBt_more;
    private FloatingActionButton mBtClean;
    private Iterator<String>         mIterator;
    private SProductBean.ProductBean product;
    public ArrayList<SearchBean.ProductListBean> mMsgs = new ArrayList<SearchBean.ProductListBean>();
    private HistoryRecordAdapter mMAdapter;
    private Retrofit            mRetrofit;
    private UserDBOpenHelper    mHelper;
    private Dao<User, Integer>  mDao;
    private List<User>     mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_record);

        initData();
    }

    private void initData() {
        mLv_lookScore = (SwipeMenuListView) findViewById(R.id.lv_lookScore);
        mBtClean = (FloatingActionButton) findViewById(R.id.look_clear);
        mBtClean.setOnClickListener(this);
        mLv_lookScore.setOnItemClickListener(this);


        mHelper = new UserDBOpenHelper(getApplicationContext());
        try {
            mDao = mHelper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        queryDb();

        handlerSwipeEvent();

    }

    private void handlerSwipeEvent() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
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

        mLv_lookScore.setMenuCreator(creator);

        mLv_lookScore.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // delete
                        delete();
                        SnackBarUtils.SnackBarShort(mBtClean,"删除成功");
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        mLv_lookScore.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

    }

    private void queryDb() {

        try {
            mUsers = mDao.queryForAll();

            for (User user : mUsers) {
                SearchBean.ProductListBean item = new SearchBean.ProductListBean();
                item.setId(user.getPid());
                item.setPic(user.getPicRes());
                item.setName(user.getName());
                item.setPrice(user.getPrice());
                item.setMarketPrice(user.getMtPrice());
                mMsgs.add(item);
            }

            mMAdapter = new HistoryRecordAdapter(LookRecordActivity.this, mMsgs);
            mLv_lookScore.setAdapter(mMAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {

        try {
            if(mUsers.size()>0)
                mDao.delete(mUsers.get(0));

        } catch (SQLException e) {
            Toast.makeText(LookRecordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        mMsgs.clear();
        queryDb();

    }
    public void deleteAll() {

        try {
            if(mUsers.size()>0)
                mDao.delete(mUsers);

        } catch (SQLException e) {
            Toast.makeText(LookRecordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        mMsgs.clear();
        queryDb();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.look_clear:
                deleteAll();
                break;
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        SearchBean.ProductListBean item = (SearchBean.ProductListBean) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("pId",item.getId()+"");
        startActivity(intent);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
