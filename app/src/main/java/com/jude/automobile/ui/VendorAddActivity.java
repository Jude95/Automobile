package com.jude.automobile.ui;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Vendor;
import com.jude.automobile.presenter.VendorAddPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/2/19.
 */
@RequiresPresenter(VendorAddPresenter.class)
public class VendorAddActivity extends BeamDataActivity<VendorAddPresenter, Vendor> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_vendor)
    AppCompatEditText etVendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_add);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(Vendor data) {
        etVendor.setText(data.getName());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_ok,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ok){
            getPresenter().data.setName(etVendor.getText().toString());
            getPresenter().publishEdit();
            return true;
        }else if (item.getItemId() == R.id.delete){
            new MaterialDialog.Builder(this)
                    .title("删除")
                    .content("你确定要删除本厂商吗?")
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive((dialog, which) -> getPresenter().delete())
                    .show();
        }
        return true;
    }


}
