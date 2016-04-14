package com.jude.automobile.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.ui.PartSelectActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/2/3.
 */
public class PartSelectPresenter extends BeamListActivityPresenter<PartSelectActivity,Part> {

    public String type;
    public String drawing_number;

    @Override
    protected void onCreate(PartSelectActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        type = getView().getIntent().getStringExtra("line");
        onRefresh();
    }

    public void selected(Part part){
        if (part == null) {
            JUtils.Toast("请选择配件");
            return;
        }
        Intent i = new Intent();
        i.putExtra("data", (Parcelable) part);
        getView().setResult(Activity.RESULT_OK,i);
        getView().finish();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getPartByType(type,drawing_number)
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .unsafeSubscribe(getRefreshSubscriber());
    }
}
