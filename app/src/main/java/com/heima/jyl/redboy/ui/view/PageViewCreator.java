package com.heima.jyl.redboy.ui.view;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.utils.NetworkImageHolderView;

import java.util.List;

/**
 * Created by Jyl on 2016/6/14.
 */
public class PageViewCreator {
    public static void create(ConvenientBanner convenientBanner, List<String> data) {
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, data).setPageIndicator(new int[]{R.drawable.ic_round_white, R.drawable.ic_round_red})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }
}
