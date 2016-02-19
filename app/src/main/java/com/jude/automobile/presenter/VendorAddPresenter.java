package com.jude.automobile.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.jude.automobile.app.APP;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Brand;
import com.jude.automobile.domain.entities.Vendor;
import com.jude.automobile.ui.VendorAddActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/2/19.
 */
public class VendorAddPresenter extends BeamDataActivityPresenter<VendorAddActivity,Vendor> {
    public Brand brand;
    public Vendor data;

    @Override
    protected void onCreate(VendorAddActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        data = getView().getIntent().getParcelableExtra("data");
        if (data == null)data = new Vendor();
        brand = getView().getIntent().getParcelableExtra("brand");
        if (brand!=null){
            data.setBrandId(brand.getId());
        }
        publishObject(data);
    }


    public void publishEdit(){
        if(TextUtils.isEmpty(data.getName())){
            JUtils.Toast("请填写厂商名字");
            return;
        }
        DataModel.getInstance().addVendor(data)
        .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
        .compose(new ProgressDialogTransform<>(getView(),"提交中"))
        .subscribe(info ->{
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
        DataModel.getInstance().deleteVendor(data.getId())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"提交中"))
                .subscribe(info -> {
                    JUtils.Toast("删除成功");
                    getView().setResult(APP.RESULT_DELETE);
                    getView().finish();
                });
    }
}
