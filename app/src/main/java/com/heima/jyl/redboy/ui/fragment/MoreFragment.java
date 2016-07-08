package com.heima.jyl.redboy.ui.fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.ui.activity.AboutActivity;
import com.heima.jyl.redboy.ui.activity.AccountActivity;
import com.heima.jyl.redboy.ui.activity.FeedActivity;
import com.heima.jyl.redboy.ui.activity.HelpCenterActivity;
import com.heima.jyl.redboy.ui.activity.LoginActivity;
import com.heima.jyl.redboy.ui.activity.LookRecordActivity;
import com.heima.jyl.redboy.ui.view.Listitemview;
import com.heima.jyl.redboy.utils.SPUtils;

/**
 * Created by JING on 2016/6/15.
 */
public class MoreFragment extends Fragment implements View.OnClickListener {

    private View mMoreView;
    private Listitemview ltvAccount;
    private Listitemview ltvRecord;
    private Listitemview ltvHelp;
    private Listitemview ltvFeed;
    private Listitemview ltvAbout;
    private Button btnMore;
    private View mMore_fab;
    private View mLogoBar;
    private ActivityOptions mFabTransition;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMoreView = inflater.inflate(R.layout.fragmennt_more, container, false);
        initView();
        setListener();
        initFabAnim();
        return mMoreView;
    }

    private void initFabAnim() {
        mFabTransition = ActivityOptions.makeSceneTransitionAnimation(this.getActivity(),
                mMore_fab,"fab"
        );
    }


    private void initView() {
        mLogoBar = mMoreView.findViewById(R.id.more_header);
        mMore_fab = mMoreView.findViewById(R.id.more_fab);
        ltvRecord = (Listitemview) mMoreView.findViewById(R.id.ltv_record);
        ltvHelp = (Listitemview) mMoreView.findViewById(R.id.ltv_help);
        ltvFeed = (Listitemview) mMoreView.findViewById(R.id.ltv_feed);
        ltvAbout = (Listitemview) mMoreView.findViewById(R.id.ltv_about);
        btnMore = (Button) mMoreView.findViewById(R.id.btn_more);
    }

    private void setListener() {
        mMore_fab.setOnClickListener(this);
        ltvRecord.setOnClickListener(this);
        ltvHelp.setOnClickListener(this);
        ltvFeed.setOnClickListener(this);
        ltvAbout.setOnClickListener(this);
        btnMore.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more_fab:
                String token = SPUtils.getString(getContext(), "token");
                if ("".equals(token)) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.putExtra("where", "more");
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getContext(), AccountActivity.class));
                }
                break;
            case R.id.ltv_record:
                Intent recordIntent = new Intent(getContext(), LookRecordActivity.class);
                startActivity(recordIntent);
                break;
            case R.id.ltv_help:
                Intent intent = new Intent(getContext(), HelpCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.ltv_feed:
                startActivity(new Intent(getContext(),FeedActivity.class));
                break;
            case R.id.ltv_about:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
            case R.id.btn_more:
                break;
            default:
                break;
        }
    }
}
