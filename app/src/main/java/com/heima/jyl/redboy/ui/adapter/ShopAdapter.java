package com.heima.jyl.redboy.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.BaseUrl;
import com.heima.jyl.redboy.api.CartEditApi;
import com.heima.jyl.redboy.bean.CarBean;
import com.heima.jyl.redboy.utils.RetrofitUtil;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @创建者 种金币
 * @创建时间 2016/6/15 18:33
 * @描述 购物车商品Adapter
 */
public class ShopAdapter extends BaseAdapter {

    public  Context                mContext;
    public  List<CarBean.CartBean> isProductList;
    public  List<CarBean.CartBean> unProductList;
    public  boolean                isEdit;
    public  View                   carView;
    private String                 USER_ID;
    private String                 TOKEN;


    public ShopAdapter(Context context, List<CarBean.CartBean> isProductList,
                       List<CarBean.CartBean> unProductList, boolean isEdit, View carView, String USER_ID, String TOKEN) {
        this.mContext = context;
        this.isProductList = isProductList;
        this.unProductList = unProductList;
        this.isEdit = isEdit;
        this.carView = carView;
        this.USER_ID = USER_ID;
        this.TOKEN = TOKEN;

    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == isProductList.size() + 1) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getCount() {
        return isProductList.size() + unProductList.size() + 2; // 增加两个分隔条目
    }

    @Override
    public CarBean.CartBean getItem(int position) {
        if (position == 0 || position == isProductList.size() + 1) {
            return null;
        } else {
            CarBean.CartBean info;
            if (position < isProductList.size() + 1) {
                info = isProductList.get(position - 1);
            } else {
                info = unProductList.get(position - isProductList.size() - 2);
            }
            return info;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
                convertView = setItem(position);
                break;
            case 1:
                final ViewHolder holder;
                if (convertView == null || convertView instanceof TextView) {
                    holder = new ViewHolder();
                    convertView = View.inflate(mContext, R.layout.shop_item_message, null);
                    holder.tvProductName = (TextView) convertView
                            .findViewById(R.id.item_productName);
                    holder.ivProductImageUrl = (ImageView) convertView
                            .findViewById(R.id.shop_item_productImage);
                    holder.tvPnum = (TextView) convertView
                            .findViewById(R.id.shop_item_num);
                    holder.tvProductPrice = (TextView) convertView
                            .findViewById(R.id.item_price);
                    holder.tvPriceSum = (TextView) convertView
                            .findViewById(R.id.item_sum);
                    holder.rl = (RelativeLayout) convertView
                            .findViewById(R.id.shop_item_fl_rl);
                    holder.ll = (LinearLayout) convertView
                            .findViewById(R.id.shop_item_fl_ll);
                    holder.ivMinus = (ImageView) convertView
                            .findViewById(R.id.shop_item_minus);
                    holder.edCnum = (EditText) convertView
                            .findViewById(R.id.shop_item_cnum);
                    holder.ivAdd = (ImageView) convertView
                            .findViewById(R.id.shop_item_add);
                    holder.cbCheck = (CheckBox) convertView
                            .findViewById(R.id.item_cb);
                    holder.ivCheck = (ImageView) convertView
                            .findViewById(R.id.item_check);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                final CarBean.CartBean data = getItem(position);
                if (data == null) {
                    break;
                }

                holder.tvProductName.setText(data.getProductName());
                Glide.with(mContext)
                        .load(BaseUrl.basUrl + data.getProductImageUrl())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(holder.ivProductImageUrl);
                holder.tvProductPrice.setText(data.getProductPrice());
                holder.cbCheck.setChecked(data.isCheck());

                cbOnCleck(holder, data);

                if (isEdit) {
                    isEdit(holder, data);
                } else {
                    unEdit(holder, data);
                }
                break;
        }
        return convertView;
    }

    //显示页面的设置
    private void unEdit(ViewHolder holder, CarBean.CartBean data) {
        holder.rl.setVisibility(View.VISIBLE);
        holder.ll.setVisibility(View.INVISIBLE);
        holder.tvPnum.setText(data.getPnum());
        int sum = Integer.valueOf(data.getPnum()) * Integer.valueOf(data.getProductPrice());
        holder.tvPriceSum.setText(sum + "");
    }

    //编辑页面的设置
    private void isEdit(final ViewHolder holder, final CarBean.CartBean data) {
        holder.rl.setVisibility(View.INVISIBLE);
        holder.ll.setVisibility(View.VISIBLE);
        holder.edCnum.setText(data.getPnum());

        minusClick(holder, data);
        addClick(holder, data);
        String num = holder.edCnum.getText().toString().trim();
        data.setPnum(num);
        int sum = Integer.valueOf(num) * Integer.valueOf(data.getProductPrice());
        holder.tvPriceSum.setText(sum + "");
    }

    //加好的点击
    private void addClick(final ViewHolder holder, final CarBean.CartBean data) {
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = data.getPnum();
                int cnum = Integer.valueOf(num) + 1;
                if (cnum > 10) {
                    Toast.makeText(mContext, "不许多买哦，亲！", Toast.LENGTH_SHORT).show();
                    cnum = 10;
                }
                holder.edCnum.setText(cnum + "");
                int sum = cnum * Integer.valueOf(data.getProductPrice());
                holder.tvPriceSum.setText(sum + "");
                data.setPnum(cnum + "");
                setTitle();
                putData(data.getId(), cnum + "", data.getPid());
            }
        });
    }

    //减号的点击
    private void minusClick(final ViewHolder holder, final CarBean.CartBean data) {
        holder.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = data.getPnum();
                int cnum = Integer.valueOf(num) - 1;
                if (cnum < 1) {
                    Toast.makeText(mContext, "再少就没有了，亲！", Toast.LENGTH_SHORT).show();
                    cnum = 1;
                }
                holder.edCnum.setText(cnum + "");
                int sum = cnum * Integer.valueOf(data.getProductPrice());
                holder.tvPriceSum.setText(sum + "");
                data.setPnum(cnum + "");
                setTitle();
                putData(data.getId(), cnum + "", data.getPid());
            }
        });
    }

    //cb的点击事件
    private void cbOnCleck(final ViewHolder holder, final CarBean.CartBean data) {
        holder.ivCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = !data.isCheck();
                holder.cbCheck.setChecked(check);
                data.setCheck(check);
                setTitle();
            }
        });
    }

    //设置分类条目
    private View setItem(int position) {
        View convertView;
        TextView tv = new TextView(mContext);
        tv.setTextColor(Color.BLACK);
        tv.setPadding(10, 10, 10, 10);
        tv.setElevation(10.0f);
        tv.setBackgroundResource(R.drawable.rectangle_orange_item);
        if (position == 0) {
            tv.setText("未付款商品");
        } else {
            tv.setText("已无货商品");
        }
        tv.setTextColor(mContext.getResources().getColor(R.color.md_white_color_code));
        return tv;
    }

    //上传数据到服务器
    private void putData(String cid, String pnum, String pid) {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(CartEditApi.class).getCart(USER_ID, TOKEN, cid, pnum, pid)
                .enqueue(new Callback<CarBean>() {
                    @Override
                    public void onResponse(Response<CarBean> response, Retrofit retrofit) {
                        if (response.code() == 200) {
                        } else {
                            Toast.makeText(mContext, response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                    }
                });
    }

    static class ViewHolder {
        ImageView      ivProductImageUrl;
        TextView       tvPnum;
        TextView       tvProductName;
        TextView       tvProductPrice;
        TextView       tvPriceSum;
        RelativeLayout rl;
        LinearLayout   ll;
        EditText       edCnum;
        ImageView      ivAdd;
        ImageView      ivMinus;
        CheckBox       cbCheck;
        ImageView      ivCheck;
    }

    //设置Title
    public void setTitle() {
        TextView tvKids = (TextView) carView.findViewById(R.id.shop_tv_kids);
        TextView tvNum = (TextView) carView.findViewById(R.id.shop_tv_num);
        TextView tvSum = (TextView) carView.findViewById(R.id.shop_tv_sum);
        int kids = 0;
        int sum = 0;
        int num = 0;
        for (CarBean.CartBean data : isProductList) {
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

}

