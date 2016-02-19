package com.jude.automobile.presenter;

import android.os.Bundle;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Vendor;
import com.jude.automobile.ui.VendorActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by zhuchenxi on 16/2/19.
 */
public class VendorPresenter extends BeamListActivityPresenter<VendorActivity,Line> {

    public Vendor data;

    @Override
    protected void onCreate(VendorActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        data = getView().getIntent().getParcelableExtra("data");
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getLineByVendor(data.getId())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .subscribe(getRefreshSubscriber());
    }
}
