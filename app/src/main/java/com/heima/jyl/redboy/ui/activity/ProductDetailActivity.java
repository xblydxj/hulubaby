package com.heima.jyl.redboy.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.AddFavoritesApi;
import com.heima.jyl.redboy.api.BaseUrl;
import com.heima.jyl.redboy.api.CartAddApi;
import com.heima.jyl.redboy.api.SProductApi;
import com.heima.jyl.redboy.bean.AddFavouritesBean;
import com.heima.jyl.redboy.bean.BaseBean;
import com.heima.jyl.redboy.bean.SProductBean;
import com.heima.jyl.redboy.bean.SearchBean;
import com.heima.jyl.redboy.dbdao.User;
import com.heima.jyl.redboy.dbdao.UserDBOpenHelper;
import com.heima.jyl.redboy.ui.view.PageViewCreator;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;
import com.heima.jyl.redboy.utils.SnackBarUtils;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {

    private ConvenientBanner cbar_productdetail;
    private TextView tv_detail_name;
    private TextView tv_detail_mprice;
    private TextView tv_detail_nowprice;
    private EditText et_detail_num;
    private Button bt_detail_add;
    private RatingBar rb_detail;
    private Button bt_detail_coll;
    private TextView tv_inventoryArea;
    private TextView tv_detail_comment;
    private String pId;
    private ArrayList<String> mData = new ArrayList<>();
    private Set<String> mIdSet = new HashSet<String>();
    public  SProductBean.ProductBean product = new SProductBean.ProductBean();
    private HashSet<SearchBean.ProductListBean> historyMsg = new HashSet<>();

    private Retrofit                                    mRetrofit;
    private Set<String>                                 mIdSrecord;
    private String                                      keyWord;
    private UserDBOpenHelper   mHelper;
    private Dao<User, Integer> mDao;
    private String USER_ID;
    private String TOKEN;
    private ArrayList<String> mBigPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
        Intent intent = getIntent();
        pId = intent.getStringExtra("pId");

        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUser();
    }

    private void addData(SProductBean.ProductBean product) {
        mHelper = new UserDBOpenHelper(getApplicationContext());

        try {
            mDao = mHelper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = new User(product.getPrice(),product.getId(),BaseUrl.basUrl + product.getPics().get(0),
                product.getName(),product.getMarketPrice());

        try {
            mDao.create(user);
        } catch (SQLException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
    }

    private void initData() {
        mRetrofit = RetrofitUtil.RetrofitConfig();
        mRetrofit.create(SProductApi.class).getSProduct(pId).enqueue(new Callback<SProductBean>() {
            @Override
            public void onResponse(Response<SProductBean> response, Retrofit retrofit) {
                if (!(response.errorBody() == null)) {
                    SnackBarUtils.SnackBarLong(et_detail_num,"无该商品···");
                    return;
                }
                SProductBean.ProductBean product = response.body().getProduct();
                List<String> pics = product.getPics();
                mBigPic = (ArrayList<String>) product.getBigPic();
                addData(product);//获取浏览记录的数据

                for (String path : pics) {
                    mData.add(BaseUrl.basUrl + path);
                }
                Class cls = null;
                try {
                    cls = Class.forName("com.ToxicBakery.viewpager.transforms.DepthPageTransformer");
                    ABaseTransformer transforemer = (ABaseTransformer) cls.newInstance();
                    cbar_productdetail.setPageTransformer(transforemer).setCanLoop(false);
                    PageViewCreator.create(cbar_productdetail, mData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cbar_productdetail.setcurrentitem(0);
                cbar_productdetail.setCanLoop(false);
                tv_detail_name.setText(product.getName());
                tv_detail_comment.setText("已有" + product.getCommentCount() + "条评论");
                tv_detail_mprice.setText(String.valueOf(product.getMarketPrice()));
                tv_detail_nowprice.setText(String.valueOf(product.getMarketPrice()));
                tv_inventoryArea.setText(product.getInventoryArea());
                et_detail_num.setText(String.valueOf(1));
                rb_detail.setStepSize(0.5f);
                if (product.getScore() > 5) {
                    rb_detail.setRating(product.getScore() / 2 + 0.5f);
                } else {
                    rb_detail.setRating(product.getScore() + 0.5f);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

    }

    private void initView() {
        cbar_productdetail = (ConvenientBanner) findViewById(R.id.cbar_productdetail);
        tv_detail_name = (TextView) findViewById(R.id.tv_detail_name);
        tv_detail_mprice = (TextView) findViewById(R.id.tv_detail_mprice);
        tv_detail_nowprice = (TextView) findViewById(R.id.tv_detail_nowprice);
        et_detail_num = (EditText) findViewById(R.id.et_detail_num);
        bt_detail_add = (Button) findViewById(R.id.bt_detail_add);
        rb_detail = (RatingBar) findViewById(R.id.rb_detail);
        bt_detail_coll = (Button) findViewById(R.id.bt_detail_coll);
        tv_inventoryArea = (TextView) findViewById(R.id.tv_inventoryArea);
        tv_detail_comment = (TextView) findViewById(R.id.tv_detail_comment);

        bt_detail_add.setOnClickListener(this);
        bt_detail_coll.setOnClickListener(this);
        cbar_productdetail.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_detail_add:
                String num = et_detail_num.getText().toString().trim();
                if (TextUtils.isEmpty(num)) {
                    Toast.makeText(this, "数量不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                final int pnum = Integer.parseInt(num);
                if (pnum > 10) {
                    SnackBarUtils.SnackBarShort(bt_detail_add, "最多购买10件");
                    return;
                }

                mRetrofit.create(CartAddApi.class).getCart(USER_ID, pId, pnum, 0, TOKEN).enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Response<BaseBean> response, Retrofit retrofit) {
                        if (response.body().error != null) {
                            Toast.makeText(ProductDetailActivity.this, response.body().error.msg, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ProductDetailActivity.this,LoginActivity.class));
                        } else {
                            SnackBarUtils.SnackBarShort(bt_detail_add, "添加成功,快去购物车看看吧!");
                            showTip();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        SnackBarUtils.SnackBarShort(bt_detail_add,"网络连接失败");
                        throwable.printStackTrace();
                    }
                });
                break;
            case R.id.bt_detail_coll:
                mRetrofit.create(AddFavoritesApi.class)
                        .getFavorites(pId,TOKEN,USER_ID)
                        .enqueue(new Callback<AddFavouritesBean>() {
                            @Override
                            public void onResponse(Response<AddFavouritesBean> response, Retrofit retrofit) {
                                if (response.body().error != null) {
                                    Toast.makeText(ProductDetailActivity.this, response.body().error.msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    SnackBarUtils.SnackBarShort(bt_detail_coll, "添加收藏成功!");
                                }
                            }

                            @Override
                            public void onFailure(Throwable throwable) {
                                SnackBarUtils.SnackBarShort(bt_detail_coll,"网络连接失败");
                                throwable.printStackTrace();
                            }
                        });
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void showTip() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder
                .setIcon(R.drawable.main_cart)
                .setMessage("添加成功,请选择")
                .setNegativeButton("继续购物", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setPositiveButton("去购物车", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                intent.putExtra("index", 3);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }).create();
        dialog.show();
    }

    //获取用户信息
    private void getUser() {
        USER_ID = SPUtils.getString(this, "user_id");
        TOKEN = SPUtils.getString(this, "token");
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ProductDetailActivity.this, BigPicActivity.class);
        intent.putStringArrayListExtra("bigPic", mBigPic);
        startActivity(intent);
    }
}
