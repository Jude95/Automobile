package com.jude.automobile.ui;

import android.content.Intent;
import android.os.Bundle;

import com.jude.automobile.data.AccountModel;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.swipbackhelper.SwipeBackHelper;

/**
 * Created by zhuchenxi on 16/1/21.
 */
public class LauncherActivity extends BeamBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
        if (AccountModel.getInstance().hasLogin()){
            startActivity(new Intent(this,MainActivity.class));
        }else {
            startActivity(new Intent(this,LoginActivity.class));
        }
        finish();
    }
}
