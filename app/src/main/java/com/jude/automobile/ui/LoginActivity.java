package com.jude.automobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.automobile.R;
import com.jude.automobile.data.AccountModel;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.utils.JUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Jude on 2015/12/5.
 */
public class LoginActivity extends BeamBaseActivity {
    public static final int REQUEST_LOGIN = 12356;

    @Bind(R.id.tilNumber)
    TextInputLayout tilNumber;
    @Bind(R.id.tilPassword)
    TextInputLayout tilPassword;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.find)
    TextView find;
    @Bind(R.id.register)
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
        login.setOnClickListener(v -> {
            getExpansion().showProgressDialog("登录中");
            AccountModel.getInstance().login(tilNumber.getEditText().getText().toString(), tilPassword.getEditText().getText().toString())
                    .finallyDo(()->getExpansion().dismissProgressDialog())
                    .doOnError(e -> JUtils.Toast("账号或密码错误"))
                    .subscribe(a ->{
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this,MainActivity.class));
                        finish();
                    });
        });
        register.setOnClickListener(v -> startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class),REQUEST_LOGIN));
        find.setOnClickListener(v -> startActivityForResult(new Intent(LoginActivity.this, FindPasswordActivity.class),REQUEST_LOGIN));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK){
            tilNumber.getEditText().setText(data.getStringExtra("number"));
            tilPassword.getEditText().setText(data.getStringExtra("password"));
        }
    }
}
