package com.jude.automobile.presenter;

import android.os.Bundle;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.ui.LineActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class LinePresenter extends BeamListActivityPresenter<LineActivity,Type> {
    public Line data;
    @Override
    protected void onCreate(LineActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        data = (Line) getView().getIntent().getParcelableExtra("data");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getTypeByLine(data.getId())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .unsafeSubscribe(getRefreshSubscriber());
    }
}
