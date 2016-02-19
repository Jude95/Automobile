package com.jude.automobile.presenter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Assemble;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.ui.TypeActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class TypePresenter extends BeamListActivityPresenter<TypeActivity,Assemble> {
    private int id;
    public Type data;
    @Override
    protected void onCreate(TypeActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        id = getView().getIntent().getIntExtra("id",0);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getTypeDetail(id).doOnNext(model -> {
            data = model;
            getAdapter().removeAllHeader();
            getAdapter().addHeader(new RecyclerArrayAdapter.ItemView() {
                @Override
                public View onCreateView(ViewGroup parent) {
                    return getView().createHeadView(parent,model);
                }

                @Override
                public void onBindView(View headerView) {
                }
            });
            getAdapter().notifyDataSetChanged();
        })
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .flatMap(model -> DataModel.getInstance().getAssembleByType(id))
                .finallyDo(() -> getView().getListView().showRecycler())
                .unsafeSubscribe(getRefreshSubscriber());
    }


}
