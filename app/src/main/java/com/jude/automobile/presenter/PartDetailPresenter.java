package com.jude.automobile.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.ImageInfo;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.ui.PartDetailActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/2/4.
 */
public class PartDetailPresenter extends BeamListActivityPresenter<PartDetailActivity,ImageInfo> {
    public Part assemble;
    public Part data;
    @Override
    protected void onCreate(PartDetailActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        assemble = (Part) getView().getIntent().getSerializableExtra("data");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getPartDetail(assemble.getId())
                .doOnNext(part -> {
                    data = part;
                    data.setAssembleId(assemble.getAssembleId());
                    data.setAssembleNote(assemble.getAssembleNote());
                    data.setModelId(assemble.getModelId());
                    data.setModelName(assemble.getModelName());

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
                .finallyDo(() -> getView().getListView().showRecycler())
                .unsafeSubscribe(getRefreshSubscriber());
    }

    public void delete(){
        DataModel.getInstance().unAssemble(data.getAssembleId())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"删除中"))
                .subscribe(v ->{
                    JUtils.Toast("删除成功");
                    getView().setResult(Activity.RESULT_OK);
                    getView().finish();
                });
    }
}
