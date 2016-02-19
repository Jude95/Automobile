package com.jude.automobile.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.jude.automobile.app.APP;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.ui.TypeAddActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/2/2.
 */
public class TypeAddPresenter extends BeamDataActivityPresenter<TypeAddActivity,Type> {
    public Type data;

    @Override
    protected void onCreate(TypeAddActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        data = (Type) getView().getIntent().getParcelableExtra("data");
        if (data == null)data = new Type();
        Line line = (Line) getView().getIntent().getParcelableExtra("line");
        if (line !=null){
            data.setLineId(line.getId());
            data.setLineName(line.getName());
        }
        publishObject(data);
    }

    public void refresh(){
        publishObject(data);
    }

    public void publishEdit(){
        if(TextUtils.isEmpty(data.getName())){
            JUtils.Toast("请填写车款名字");
            return;
        }
        DataModel.getInstance().addType(data)
            .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
            .compose(new ProgressDialogTransform<>(getView(),"提交中"))
            .subscribe(info -> {
                JUtils.Toast("提交成功");
                getView().setResult(Activity.RESULT_OK);
                getView().finish();
            });
    }

    public void delete(){
        if (data.getId()==0){
            getView().finish();
            return;
        }
        DataModel.getInstance().deleteType(data.getId())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"提交中"))
                .subscribe(info -> {
                    JUtils.Toast("删除成功");
                    getView().setResult(APP.RESULT_DELETE);
                    getView().finish();
                });
    }
}
