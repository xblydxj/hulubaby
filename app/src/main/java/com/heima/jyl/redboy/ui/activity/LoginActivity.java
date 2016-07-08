package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.LoginApi;
import com.heima.jyl.redboy.bean.LoginBean;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;
import com.heima.jyl.redboy.utils.SnackBarUtils;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button   login_back;
    private TextInputLayout login_input_Email;
    private TextInputLayout login_input_password;
    private Button   login_bt_login;
    private TextView login_bt_register;
    private EditText mLogin_et;
    private EditText mPassword_et;
    private ImageView mRedBabyLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLogin_et.setText(SPUtils.getString(this, "account"));
        mLogin_et.setSelection(SPUtils.getString(this,"account").length());
        mPassword_et.setText("");
    }

    private void login(final String username, final String password) {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(LoginApi.class)
                            .getLogin(username, password)
                            .enqueue(new Callback<LoginBean>() {
                                @Override
                                public void onResponse(Response<LoginBean> response, Retrofit retrofit) {
                        Log.d("tag", "aaa:" + username + "  " + password + "  " + response.body().getResponse());
                        if ("response".equals(response.body().getResponse())) {
                            LoginBean.UserInfoBean userInfo = response.body().getUserInfo();
                            SPUtils.putString(getApplicationContext(), "token", userInfo.getToken() + "");
                            SPUtils.putString(getApplicationContext(), "user_id", userInfo.getUser_id() + "");
                            SPUtils.putString(getApplicationContext(), "account", username);
                            Intent intent = getIntent();
                            if ("more".equals(intent.getStringExtra("where"))) {
                                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                            }
                            finish();
                        } else {
                            SnackBarUtils.SnackBarLong(login_bt_login, "账户不存在或密码错误！╥﹏╥...");
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        SnackBarUtils.SnackBarLong(login_bt_login, "网络连接失败！╥﹏╥...");
                    }
                });
    }

    private void initView() {
        mRedBabyLogo = (ImageView) findViewById(R.id.red_baby_logo);

        login_input_Email = (TextInputLayout) findViewById(R.id.login_input_Email);
        mLogin_et = login_input_Email.getEditText();
        mLogin_et.setOnClickListener(this);

        login_input_password = (TextInputLayout) findViewById(R.id.login_input_password);
        mPassword_et = login_input_password.getEditText();
        mPassword_et.setOnClickListener(this);

        login_bt_login = (Button) findViewById(R.id.login_bt_login);
        login_bt_login.setOnClickListener(this);
        login_bt_register = (TextView) findViewById(R.id.login_bt_register);
        login_bt_register.setOnClickListener(this);

    }



    private void Register() {
        getWindow().getSharedElementEnterTransition();
        getWindow().getSharedElementExitTransition();
        ActivityOptionsCompat compat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, mRedBabyLogo, "logo");
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class),compat.toBundle());
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.login_back:
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.putExtra("index", 4);
//                startActivity(intent);
//                finish();
//                break;
            case R.id.login_bt_login:
                submit();
                break;
            case R.id.login_bt_register:
                Register();
                break;
        }
    }

    private void submit() {
        String Email = mLogin_et.getText().toString().trim();
        if (TextUtils.isEmpty(Email)) {
            SnackBarUtils.SnackBarLong(login_input_Email, "请输入账号！");
            return;
        }
        String password = mPassword_et.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        login(Email, password);
    }
}
