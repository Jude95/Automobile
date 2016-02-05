package com.jude.automobile.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.jude.automobile.app.APP;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.ui.LineAddActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.jude.utils.JUtils;

import java.io.File;

import rx.Observable;

/**
 * Created by zhuchenxi on 16/1/31.
 */
public class LineAddPresenter extends BeamDataActivityPresenter<LineAddActivity,Line> {

    private ImageProvider provider;
    public Line data;

    OnImageSelectListener listener = new OnImageSelectListener() {

        @Override
        public void onImageSelect() {
            getView().getExpansion().showProgressDialog("加载中");
        }

        @Override
        public void onImageLoaded(Uri uri) {
            getView().getExpansion().dismissProgressDialog();
            provider.corpImage(uri, 300, 300, new OnImageSelectListener() {
                @Override
                public void onImageSelect() {

                }

                @Override
                public void onImageLoaded(Uri uri) {
                    data.setAvatar(uri.toString());
                    publishObject(data);
                }

                @Override
                public void onError() {
                    getView().getExpansion().dismissProgressDialog();
                    JUtils.Toast("加载错误");
                }
            });

        }

        @Override
        public void onError() {
            getView().getExpansion().dismissProgressDialog();
            JUtils.Toast("加载错误");
        }
    };

    @Override
    protected void onCreate(LineAddActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        provider = new ImageProvider(getView());
        data = (Line) getView().getIntent().getSerializableExtra("data");
        if (data == null) data = new Line();
        publishObject(data);
    }

    public void editLogo(int style) {
        switch (style) {
            case 0:
                provider.getImageFromCamera(listener);
                break;
            case 1:
                provider.getImageFromAlbum(listener);
                break;
            case 2:
                provider.getImageFromNet(listener);
                break;
        }
    }

    public void publishEdit(){
        if(TextUtils.isEmpty(data.getAvatar())){
            JUtils.Toast("请选择车系标志");
            return;
        }
        if(TextUtils.isEmpty(data.getName())){
            JUtils.Toast("请填写车系名字");
            return;
        }
        if(TextUtils.isEmpty(data.getWord())){
            JUtils.Toast("请填写车系搜索关键字");
            return;
        }
        Observable.just(data)
                .flatMap(line -> {
                    if (!Uri.parse(line.getAvatar()).getScheme().equals("http")){
                        return ImageModel.getInstance().putImageSync(new File(Uri.parse(line.getAvatar()).getPath()))
                                .map(s -> {
                                    data.setAvatar(s);
                                    return data;
                                });
                    }
                    return Observable.just(line);
                })
                .flatMap(line -> DataModel.getInstance().addLine(line.getId(),line.getName(),line.getAvatar(),line.getWord()))
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform(getView(),"上传中"))
                .subscribe(v -> {
                    JUtils.Toast("上传成功");
                    getView().setResult(Activity.RESULT_OK);
                    getView().finish();
                });
    }

    public void delete(){
        if (data.getId()==0){
            getView().finish();
            return;
        }
        DataModel.getInstance().deleteLine(data.getId())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"提交中"))
                .subscribe(info -> {
                    JUtils.Toast("删除成功");
                    getView().setResult(APP.RESULT_DELETE);
                    getView().finish();
                });
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        provider.onActivityResult(requestCode, resultCode, data);
    }

}
