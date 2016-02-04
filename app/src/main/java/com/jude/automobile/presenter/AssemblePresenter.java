package com.jude.automobile.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.ui.AssembleActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.bijection.Presenter;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/2/3.
 */
public class AssemblePresenter extends Presenter<AssembleActivity> {
    private int model;
    private int part;

    @Override
    protected void onCreate(AssembleActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        model = getView().getIntent().getIntExtra("id",0);
    }

    public void publishEdit(String note){
        DataModel.getInstance().assemble(part,model,note)
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"提交中"))
                .subscribe(info -> {
                    JUtils.Toast("绑定成功");
                    getView().setResult(Activity.RESULT_OK);
                    getView().finish();
                });
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            part = ((Part)data.getSerializableExtra("data")).getId();
        }
    }
}
