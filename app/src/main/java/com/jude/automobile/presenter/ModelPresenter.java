package com.jude.automobile.presenter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.ui.ModelActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class ModelPresenter extends BeamListActivityPresenter<ModelActivity,Part> {
    private int id;

    @Override
    protected void onCreate(ModelActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        id = getView().getIntent().getIntExtra("id",0);
        onRefresh();
        DataModel.getInstance().getModelById(id).subscribe(model -> {
            getAdapter().addHeader(new RecyclerArrayAdapter.ItemView() {
                @Override
                public View onCreateView(ViewGroup parent) {
                    return getView().createHeadView(parent,model);
                }

                @Override
                public void onBindView(View headerView) {
                }
            });
        });
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getPartByModel(id).subscribe(getRefreshSubscriber());
    }
}