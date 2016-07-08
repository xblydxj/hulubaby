package com.heima.jyl.redboy.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * @创建者 种金币
 * @创建时间 2016/6/18 18:56
 * @描述 ListView滑动
 */



public class ListView extends android.widget.ListView{

        public ListView(Context context) {
            super(context);
        }
        public ListView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ListView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            if(ev.getAction() == MotionEvent.ACTION_MOVE){
                return true;
            }
            return super.dispatchTouchEvent(ev);
        }

}

