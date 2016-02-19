package com.jude.automobile.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.jude.automobile.app.APP;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Vendor;
import com.jude.automobile.ui.LineAddActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/2/1.
 */
public class LineAddPresenter extends BeamDataActivityPresenter<LineAddActivity,Line> {
    public Line line;

    @Override
    protected void onCreate(LineAddActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        line = (Line) getView().getIntent().getParcelableExtra("data");
        if (line == null) line = new Line();
        Vendor vendor =  getView().getIntent().getParcelableExtra("vendor");
        if (vendor !=null){
            line.setVendorName(vendor.getName());
            line.setVendorId(vendor.getId());
        }
        publishObject(line);
    }

    public void publishEdit(){
        if(TextUtils.isEmpty(line.getName())){
            JUtils.Toast("请填写车型名字");
            return;
        }
        if(TextUtils.isEmpty(line.getName())){
            JUtils.Toast("请填写车型搜索关键字");
            return;
        }

        DataModel.getInstance().addLine(line.getId(), line.getVendorId(), line.getName(), line.getWord())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"提交中"))
                .subscribe(info -> {
                    JUtils.Toast("编辑成功");
                    getView().setResult(Activity.RESULT_OK);
                    getView().finish();
                });
    }

    public void delete(){
        if (line.getId()==0){
            getView().finish();
            return;
        }
        DataModel.getInstance().deleteLine(line.getId())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"提交中"))
                .subscribe(info -> {
                    JUtils.Toast("删除成功");
                    getView().setResult(APP.RESULT_DELETE);
                    getView().finish();
                });
    }
}
