package com.heima.jyl.redboy.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heima.jyl.redboy.R;

/**
 * Created by ting说你跳 on 2016/6/16.
 */
public class TopBarView extends RelativeLayout {
    public TopBarView(Context context) {
        super(context);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TopBarView);
        String title = array.getString(R.styleable.TopBarView_title_text);
        int left = array.getInt(R.styleable.TopBarView_btn_left, 0);
        int right = array.getInt(R.styleable.TopBarView_btn_right, 0);
        String left_text = array.getString(R.styleable.TopBarView_left_text);
        String right_text = array.getString(R.styleable.TopBarView_right_text);
        array.recycle();
        View view = View.inflate(context, R.layout.activity_top_bar, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        Button btn_back = (Button) view.findViewById(R.id.btn_back);
        Button btn_left = (Button) view.findViewById(R.id.btn_left);
        Button btn_right = (Button) view.findViewById(R.id.btn_right);
        tv_title.setText(title);
        switch (left) {
            case 0:
                btn_back.setVisibility(INVISIBLE);
                btn_back.setClickable(false);
                btn_left.setVisibility(INVISIBLE);
                btn_left.setClickable(false);
                break;
            case 1:
                btn_back.setVisibility(INVISIBLE);
                btn_back.setClickable(false);
                btn_left.setText(left_text);

                int width = 0;
                switch (left_text.length()) {
                    case 2:
                        width = 40;
                        break;
                    case 3:
                        width = 60;
                        break;
                    case 4:
                        width = 80;
                        break;
                    case 5:
                    case 6:
                        width = 100;
                        break;
                    default:
                        break;
                }
                ViewGroup.LayoutParams params = btn_left.getLayoutParams();
                params.width = width;
                btn_left.setLayoutParams(params);
                break;
            case 2:
                btn_left.setVisibility(INVISIBLE);
                btn_left.setClickable(false);
                btn_back.setText("返回");
                break;
            default:
                break;
        }
        switch (right) {
            case 0:
                btn_right.setVisibility(INVISIBLE);
                btn_right.setClickable(false);
                break;
            case 1:
                btn_right.setText(right_text);

                int width = 0;
                switch (right_text.length()) {
                    case 2:
                        width = 40;
                        break;
                    case 3:
                        width = 60;
                        break;
                    case 4:
                        width = 80;
                        break;
                    case 5:
                    case 6:
                        width = 100;
                        break;
                    default:
                        break;
                }
                ViewGroup.LayoutParams params = btn_right.getLayoutParams();
                params.width = width;
                btn_right.setLayoutParams(params);
                break;
            default:
                break;
        }
        addView(view);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
