package com.heima.jyl.redboy.ui.activity;

import com.heima.jyl.redboy.R;
import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;


public class MyWelcomeActivity extends WelcomeActivity {


    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.CustomWelcomeScreenTheme)
                .defaultTitleTypefacePath("Montserrat-Bold.ttf")
                .defaultHeaderTypefacePath("Montserrat-Bold.ttf")
                .titlePage(R.mipmap.welcome, "Welcome", R.color.md_deep_orange_500_color_code)
                .basicPage(R.mipmap.colorshuluwa, "Happy to join us", "We are HuLuWa brothers!", R.color.md_blue_grey_500_color_code)
                .parallaxPage(R.layout.welcome_parallax, "Who we are", "HuLuWa,HuLuWa,HuLuWa,HuLuWa……", R.color.md_cyan_700_color_code, 0.2f, 2f)
                .basicPage(R.mipmap.baby1, "Simple to use", "Enjoy time with us！", R.color.md_blue_300_color_code)
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }

    public static String welcomeKey(){
        return "WelcomeScreen";
    }

}
