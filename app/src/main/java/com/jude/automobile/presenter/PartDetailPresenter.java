package com.jude.automobile.presenter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.ImageInfo;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.ui.PartDetailActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by zhuchenxi on 16/2/4.
 */
public class PartDetailPresenter extends BeamListActivityPresenter<PartDetailActivity,ImageInfo> {
    public Part data;
    @Override
    protected void onCreate(PartDetailActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getPartDetail(getView().getIntent().getIntExtra("id",0))
                .doOnNext(part -> {
                    data = part;
                    getAdapter().removeAllHeader();
                    getAdapter().addHeader(new RecyclerArrayAdapter.ItemView() {
                        @Override
                        public View onCreateView(ViewGroup parent) {
                            return getView().getHeader(parent,part);
                        }

                        @Override
                        public void onBindView(View headerView) {

                        }
                    });
                })
                .map(Part::getPictureFull)
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .finallyDo(() -> getView().getListView().showRecycler())
                .unsafeSubscribe(getRefreshSubscriber());
    }
}
