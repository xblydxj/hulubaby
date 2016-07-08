package com.heima.jyl.redboy.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public class SnackBarUtils {

    public static void SnackBarShort(View view, String show){
        Snackbar.make(view,show,Snackbar.LENGTH_SHORT).show();
    }
    public static void SnackBarLong(View view, String show){
        Snackbar.make(view,show,Snackbar.LENGTH_LONG).show();
    }
}
