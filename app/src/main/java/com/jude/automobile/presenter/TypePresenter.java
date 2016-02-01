package com.jude.automobile.presenter;

import android.os.Bundle;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.ui.TypeActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class TypePresenter extends BeamListActivityPresenter<TypeActivity,Model> {
    public Type data;
    @Override
    protected void onCreate(TypeActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        data = (Type) getView().getIntent().getSerializableExtra("data");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getModelByType(data.getId()).unsafeSubscribe(getRefreshSubscriber());
    }
}
