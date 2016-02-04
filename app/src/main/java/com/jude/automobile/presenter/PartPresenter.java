package com.jude.automobile.presenter;

import android.os.Bundle;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.ui.PartActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by zhuchenxi on 16/2/3.
 */
public class PartPresenter extends BeamListActivityPresenter<PartActivity,Part> {

    private String type;

    @Override
    protected void onCreate(PartActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        type = getView().getIntent().getStringExtra("type");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getPartByType(type)
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .unsafeSubscribe(getRefreshSubscriber());
    }
}
