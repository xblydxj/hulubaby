package com.heima.jyl.redboy.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heima.jyl.redboy.R;

/**
 * Created by ting说你跳 on 2016/6/15.
 */
public class Listitemview extends RelativeLayout{
    public Listitemview(Context context) {
        super(context);
    }

    public Listitemview(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取属性数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Listitemview);
        //获取某个属性的值
        String title = typedArray.getString(R.styleable.Listitemview_text);
        int bgtype = typedArray.getInt(R.styleable.Listitemview_bgtype, 1);
        //底层是C语言实现的，用完得回收内存
        typedArray.recycle();
        View view = View.inflate(context, R.layout.common_list_item, null);
        TextView tv_title = (TextView) view.findViewById(R.id.view_list_item_title);
        //设置文字
        tv_title.setText(title);
        //设置背景
        switch (bgtype) {
            case 1:
                view.setBackgroundResource(R.drawable.rectangle_teal_1);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.rectangle_teal_2);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.rectangle_teal_3);
                break;
            case 4:
                view.setBackgroundResource(R.drawable.rectangle_teal_4);
                break;
            case 5:
                view.setBackgroundResource(R.drawable.rectangle_teal_5);
                break;
            default:
                break;
        }
        addView(view);
    }

    public Listitemview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
