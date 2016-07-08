package com.heima.jyl.redboy.ui.view;


import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.bean.AreaBean;
import com.heima.jyl.redboy.ui.adapter.AreaAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class AddressDialog extends Dialog implements AdapterView.OnItemClickListener, View.OnClickListener {

    private static final String TABLE_PROVINCE = "province";
    private static final String TABLE_CITY = "city";
    private static final String TABLE_DISTRICT = "district";
    private File mDbFile;
    private List<AreaBean> provinceLists = new ArrayList<>();//省份的数据集合
    private List<AreaBean> cityLists = new ArrayList<>();//省份的数据集合
    private List<AreaBean> districtLists = new ArrayList<>();//省份的数据集合
    private AreaAdapter mProvinceAdapter;
    private AreaAdapter mCityAdapter;
    private AreaAdapter mDistrictAdapter;

    private int provinceSelectedPosition = -1;//用来记录省份的上一个选中条目的位置
    private int citySelectedPosition = -1;//用来记录城市的上一个选中条目的位置
    private int districtSelectedPosition = -1;//用来记录地区的上一个选中条目的位置

    public static String province;
    public static String city;
    public static String district;
    private ListView lv_Pr;
    private ListView lv_Cy;
    private ListView lv_Ds;
    private Button mBt;

    /**
     * 自定义Dialog监听器
     */
    public interface PriorityListener {
        /**
         * 回调函数，用于在Dialog的监听事件触发后刷新Activity的UI显示
         */
        void refreshPriorityUI(String string);
    }

    private PriorityListener listener;

    /**
     * 带监听器参数的构造函数
     */

    public AddressDialog(Context context, PriorityListener priorityListener) {
        // 设置Dialog样式
        super(context, R.style.AddressDialogStyle);
        // 修改Dialog的Window的LayoutParams
        Window window = getWindow();
        LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 修改好要设置上去 否则不生效
        window.setAttributes(params);
        this.listener = priorityListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_address_style);

        lv_Pr = (ListView) findViewById(R.id.lv_pr);
        lv_Cy = (ListView) findViewById(R.id.lv_cy);
        lv_Ds = (ListView) findViewById(R.id.lv_ds);
        mBt = (Button) findViewById(R.id.bt_finish);
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mBt.setOnClickListener(this);
    }

    private void initData() throws IOException {
        //初始化数据库
        initDB();

        //初始化省份的listview
        initProvinceListView();

        //listview的条目点击事件
        initListViewClick();

    }

    private void initDB() throws IOException {
        mDbFile = new File(getContext().getFilesDir(), "city.s3db");
        if (mDbFile.exists()) {
            return;
        }
        File mDbFile = this.mDbFile;
        InputStream is = getContext().getResources().openRawResource(R.raw.city);
        FileOutputStream fos = new FileOutputStream(mDbFile);
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            fos.write(b, 0, len);
            fos.flush();
        }
        is.close();
        fos.close();


    }

    private void initProvinceListView() {
        //读取省列表数据
        initProvinceData();
        if (mProvinceAdapter == null) {
            mProvinceAdapter = new AreaAdapter(getContext(), provinceLists);
            lv_Pr.setAdapter(mProvinceAdapter);
        } else {
            mProvinceAdapter.notifyDataSetChanged();
        }

    }

    private void initProvinceData() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(mDbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        String sql = "select * from " + TABLE_PROVINCE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                AreaBean aBean = new AreaBean();
                aBean.id = cursor.getInt(0);
                aBean.code = cursor.getInt(1);
                try {
                    aBean.name = new String(cursor.getBlob(2), "gbk");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                provinceLists.add(aBean);
            }
            cursor.close();
        }
    }

    private void initListViewClick() {
        lv_Pr.setOnItemClickListener(this);
        lv_Cy.setOnItemClickListener(this);
        lv_Ds.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv_pr:
                //取消上次的选择
                if (provinceSelectedPosition > -1) {
                    provinceLists.get(provinceSelectedPosition).isChecked = false;
                }
                provinceSelectedPosition = position;
                final AreaBean provinceBean = provinceLists.get(position);
                provinceBean.isChecked = true;
                city = null;
                //初始化城市的索引
                mProvinceAdapter.notifyDataSetChanged();
                citySelectedPosition = -1;
                province = provinceBean.name;
                //如果地区还在显示着就把对应列表清空
                if (mDistrictAdapter != null) {
                    districtLists.clear();
                    mDistrictAdapter.notifyDataSetChanged();
                }
                updateCityLitView(provinceBean);

                break;
            case R.id.lv_cy:
                final AreaBean cityBean = cityLists.get(position);
                cityBean.isChecked = true;
                district = null;
                if (citySelectedPosition > -1) {
                    cityLists.get(citySelectedPosition).isChecked = false;
                }
                citySelectedPosition = position;
                mCityAdapter.notifyDataSetChanged();
                updateDistrictListView(cityBean);

                districtSelectedPosition = -1;
                city = cityBean.name;
                break;
            case R.id.lv_ds:
                AreaBean districtBean = districtLists.get(position);
                districtBean.isChecked = true;
                if (districtSelectedPosition > -1) {
                    districtLists.get(districtSelectedPosition).isChecked = false;
                }
                districtSelectedPosition = position;
                mDistrictAdapter.notifyDataSetChanged();
                district = districtBean.name;
                break;
        }

    }

    private void updateDistrictListView(AreaBean areaBean) {
        readDistrictByCity(areaBean);
        if (mDistrictAdapter == null) {
            mDistrictAdapter = new AreaAdapter(getContext(), districtLists);
            lv_Ds.setAdapter(mDistrictAdapter);
        } else {
            mDistrictAdapter.notifyDataSetChanged();
        }
    }

    private void readDistrictByCity(AreaBean areaBean) {
        readAreaByPcode(areaBean, districtLists, TABLE_DISTRICT);
    }

    private void updateCityLitView(AreaBean areaBean) {
        readCityByProvince(areaBean);
        if (mCityAdapter == null) {
            mCityAdapter = new AreaAdapter(getContext(), cityLists);
            lv_Cy.setAdapter(mCityAdapter);
        } else {
            mCityAdapter.notifyDataSetChanged();
        }

    }

    private void readCityByProvince(AreaBean areaBean) {
        readAreaByPcode(areaBean, cityLists, TABLE_CITY);
    }

    //根据pCode查询地区
    private void readAreaByPcode(AreaBean bean, List<AreaBean> list, String tableName) {
        list.clear();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(mDbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        String sql = "select * from " + tableName + " where pcode = " + bean.code;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                AreaBean areaBean = new AreaBean();
                areaBean.id = cursor.getInt(0);
                areaBean.code = cursor.getInt(1);
                try {
                    areaBean.name = new String(cursor.getBlob(2), "gbk");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                list.add(areaBean);
            }
            cursor.close();
        }
    }

    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(province) || TextUtils.isEmpty(city) || TextUtils.isEmpty(district)) {
            Toast.makeText(getContext(), "您还没有设置完", Toast.LENGTH_SHORT).show();
            return;
        }
        listener.refreshPriorityUI(province + "\t" + city + "\t" + district);

        dismiss();
    }
}
