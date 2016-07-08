package com.heima.jyl.redboy.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.api.RegisterApi;
import com.heima.jyl.redboy.bean.RegisterBean;
import com.heima.jyl.redboy.utils.RetrofitUtil;
import com.heima.jyl.redboy.utils.SPUtils;
import com.heima.jyl.redboy.utils.SnackBarUtils;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class RegisterActivity extends Activity implements View.OnClickListener {
    private Button btn_back;
    private TextInputLayout register_et_account;
    private TextInputLayout register_et_password;
    private TextInputLayout register_et_confirm_password;
    private Button register_bt_register;
    private ImageView mRedBabyLogo;
    private EditText mAccount_et;
    private EditText mPassword_et;
    private TextView mBack_login;
    private EditText mConfirm_password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initTransition();
    }


    private void initTransition() {
        getWindow().getSharedElementEnterTransition();
        getWindow().getSharedElementExitTransition();
        ActivityOptionsCompat compat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, mRedBabyLogo, "logo");
    }


    private void initView() {
        mRedBabyLogo = (ImageView) findViewById(R.id.red_baby_logo);

        register_et_account = (TextInputLayout) findViewById(R.id.register_et_account);
        mAccount_et = register_et_account.getEditText();

        register_et_password = (TextInputLayout) findViewById(R.id.register_et_password);
        mPassword_et = register_et_password.getEditText();

        register_et_confirm_password = (TextInputLayout) findViewById(R.id.register_et_confirm_password);
        mConfirm_password_et = register_et_confirm_password.getEditText();

        register_bt_register = (AppCompatButton) findViewById(R.id.register_bt_register);

        mBack_login = (TextView) findViewById(R.id.register_back_login);
        mBack_login.setOnClickListener(this);

        register_bt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back_login:
                onBackPressed();
                break;
            case R.id.register_bt_register:
                submit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        super.onBackPressed();
    }

    private void submit() {
        // validate
        String account = mAccount_et.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            SnackBarUtils.SnackBarLong(register_et_account, "请输入账号！");
            return;
        } else if (account.length() > 13) {
            SnackBarUtils.SnackBarLong(register_et_account, "账号长度过长！");
            return;
        } else if (account.length() < 3) {
            SnackBarUtils.SnackBarLong(register_et_account, "账号长度不能太短！");
            return;
        }


        String password = mPassword_et.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            SnackBarUtils.SnackBarLong(register_et_account, "请输入密码！");
            return;
        } else if (password.length() < 3) {
            SnackBarUtils.SnackBarLong(register_et_account, "密码长度不能太短！");
            return;
        } else if (account.length() > 13) {
            SnackBarUtils.SnackBarLong(register_et_account, "密码过长！");
            return;
        }

        String confirm_password = mConfirm_password_et.getText().toString().trim();
        if (!password.equals(confirm_password)) {
            SnackBarUtils.SnackBarLong(register_et_confirm_password, "请确认密码！");
            return;
        }
        register(account, password);

    }

    private void register(final String account, String password) {
        Retrofit retrofit = RetrofitUtil.RetrofitConfig();
        retrofit.create(RegisterApi.class)
                .getRegister(account, password)
                .enqueue(new Callback<RegisterBean>() {
                    @Override
                    public void onResponse(Response<RegisterBean> response, Retrofit retrofit) {
                        if ("register".equals(response.body().getResponse())) {
                            SnackBarUtils.SnackBarShort(register_bt_register, "注册成功");
                            SPUtils.putString(getApplicationContext(), "account", account);
                            onBackPressed();
                        } else {
                            SnackBarUtils.SnackBarShort(register_bt_register, "注册失败！" + response.body().error.msg);

                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        SnackBarUtils.SnackBarShort(register_bt_register, "网络连接失败！");
                    }
                });
    }
}
