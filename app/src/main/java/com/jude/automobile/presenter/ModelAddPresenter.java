package com.jude.automobile.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.ui.ModelAddActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/2/2.
 */
public class ModelAddPresenter extends BeamDataActivityPresenter<ModelAddActivity,Model> {
    public Model data;

    @Override
    protected void onCreate(ModelAddActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        data = (Model) getView().getIntent().getSerializableExtra("data");
        if (data == null)data = new Model();
        Type type = (Type) getView().getIntent().getSerializableExtra("type");
        if (type!=null){
            data.setTypeId(type.getId());
            data.setTypeName(type.getName());
        }
        publishObject(data);
    }

    public void refresh(){
        publishObject(data);
    }

    public void publishEdit(){
        DataModel.getInstance().addModel(data)
            .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
            .compose(new ProgressDialogTransform<>(getView(),"提交中"))
            .subscribe(info -> {
                JUtils.Toast("提交成功");
                getView().setResult(Activity.RESULT_OK);
                getView().finish();
            });
    }
}
