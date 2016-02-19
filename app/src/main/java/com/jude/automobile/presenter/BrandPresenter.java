package com.jude.automobile.presenter;

import android.os.Bundle;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.domain.entities.Brand;
import com.jude.automobile.domain.entities.Vendor;
import com.jude.automobile.ui.BrandActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class BrandPresenter extends BeamListActivityPresenter<BrandActivity,Vendor> {

    public Brand data;
    @Override
    protected void onCreate(BrandActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        data = getView().getIntent().getParcelableExtra("data");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getVendorByBrand(data.getId())
                .unsafeSubscribe(getRefreshSubscriber());
    }
}
