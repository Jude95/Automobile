package com.jude.automobile.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.utils.JUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/1/23.
 */
public class AboutActivity extends BeamBaseActivity {

    @Bind(R.id.version)
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        version.setText(getString(R.string.app_name)+" v"+ JUtils.getAppVersionName());
    }
}
