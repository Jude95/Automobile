package com.jude.automobile.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.jude.automobile.app.APP;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.ui.TypeAddActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/2/1.
 */
public class  TypeAddPresenter extends BeamDataActivityPresenter<TypeAddActivity,Type> {
    public Type type;

    @Override
    protected void onCreate(TypeAddActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        type = (Type) getView().getIntent().getSerializableExtra("data");
        if (type == null) type = new Type();
        Line line = (Line) getView().getIntent().getSerializableExtra("line");
        if (line!=null){
            type.setLineName(line.getName());
            type.setLineAvatar(line.getAvatar());
            type.setLineId(line.getId());
        }
        publishObject(type);
    }

    public void publishEdit(){
        if(TextUtils.isEmpty(type.getName())){
            JUtils.Toast("请填写车型名字");
            return;
        }
        if(TextUtils.isEmpty(type.getName())){
            JUtils.Toast("请填写车型搜索关键字");
            return;
        }

        DataModel.getInstance().addType(type.getId(),type.getLineId(),type.getName(),type.getWord())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"提交中"))
                .subscribe(info -> {
                    JUtils.Toast("编辑成功");
                    getView().setResult(Activity.RESULT_OK);
                    getView().finish();
                });
    }

    public void delete(){
        if (type.getId()==0){
            getView().finish();
            return;
        }
        DataModel.getInstance().deleteType(type.getId())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"提交中"))
                .subscribe(info -> {
                    JUtils.Toast("删除成功");
                    getView().setResult(APP.RESULT_DELETE);
                    getView().finish();
                });
    }
}
