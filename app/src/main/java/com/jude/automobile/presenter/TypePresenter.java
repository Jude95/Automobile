package com.jude.automobile.presenter;

import android.os.Bundle;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.ui.TypeActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class TypePresenter extends BeamListActivityPresenter<TypeActivity,Model> {
    int id;
    @Override
    protected void onCreate(TypeActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        id = getView().getIntent().getIntExtra("id",0);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getModelByType(id).subscribe(getRefreshSubscriber());
    }
}
