package com.heima.jyl.redboy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.heima.jyl.redboy.R;
import com.heima.jyl.redboy.bean.InvoiceBean;

public class   BillNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rb_nonbill;
    private RadioButton rb_personbill;
    private RadioButton rb_unitbill;
    private EditText    et_billhead;
    private EditText    et_billcontent;
    private Button      bt_billcommit;
    private int         type;
    private InvoiceBean mInvoiceBean;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_note);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mToolbar.setTitle("发票信息");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.bill_note_toolbar);
        rb_nonbill = (RadioButton) findViewById(R.id.rb_nonbill);
        rb_personbill = (RadioButton) findViewById(R.id.rb_personbill);
        rb_unitbill = (RadioButton) findViewById(R.id.rb_unitbill);
        et_billhead = (EditText) findViewById(R.id.et_billhead);
        et_billcontent = (EditText) findViewById(R.id.et_billcontent);
        bt_billcommit = (Button) findViewById(R.id.bt_billcommit);
        bt_billcommit.setOnClickListener(this);
        rb_nonbill.setOnClickListener(this);
        rb_personbill.setOnClickListener(this);
        rb_unitbill.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_billcommit:
                submit();
                break;
            case R.id.rb_nonbill:
                type = 0;
                rb_personbill.setChecked(false);
                rb_unitbill.setChecked(false);
                break;
            case R.id.rb_personbill:
                type = 1;
                rb_nonbill.setChecked(false);
                rb_unitbill.setChecked(false);
                break;
            case R.id.rb_unitbill:
                type = 2;
                rb_nonbill.setChecked(false);
                rb_personbill.setChecked(false);
                break;
        }
    }

    private void submit() {
        if (type == 0) {
            finish();
            return;
        }
        String billhead = et_billhead.getText().toString().trim();
        if (TextUtils.isEmpty(billhead)) {
            Toast.makeText(this, "billhead不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String billcontent = et_billcontent.getText().toString().trim();
        if (TextUtils.isEmpty(billcontent)) {
            Toast.makeText(this, "billcontent不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mInvoiceBean = new InvoiceBean();
        mInvoiceBean.type = type;
        mInvoiceBean.head = billhead;
        mInvoiceBean.content = billcontent;
        Intent intent = new Intent();
        intent.putExtra("InvoiceBean", mInvoiceBean);
        setResult(200, intent);
        finish();
    }
}
