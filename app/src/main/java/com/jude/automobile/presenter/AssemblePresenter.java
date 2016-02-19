package com.jude.automobile.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Assemble;
import com.jude.automobile.ui.AssembleActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/2/3.
 */
public class AssemblePresenter extends BeamListActivityPresenter<AssembleActivity,Assemble> {
    private int model;

    @Override
    protected void onCreate(AssembleActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        model = getView().getIntent().getIntExtra("id",0);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        DataModel.getInstance().getAssembleByType(model)
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .unsafeSubscribe(getRefreshSubscriber());
    }

    public void publishEdit(String note, int partId){
        if(TextUtils.isEmpty(note)){
            JUtils.Toast("请填写备注别名");
            return;
        }
        DataModel.getInstance().assemble(partId,model,note)
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"提交中"))
                .subscribe(info -> {
                    JUtils.Toast("绑定成功");
                    getView().getListView().setRefreshing(true,true);
                });
    }
}
