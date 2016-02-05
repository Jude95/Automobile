package com.jude.automobile.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.ui.PartAddActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.jude.exgridview.PieceViewGroup;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.jude.utils.JUtils;

import java.io.File;
import java.util.ArrayList;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhuchenxi on 16/2/3.
 */
public class PartAddPresenter extends BeamDataActivityPresenter<PartAddActivity,Part> implements PieceViewGroup.OnViewDeleteListener{
    private ImageProvider provider;
    public Part data;

    OnImageSelectListener mAvatarListener = new OnImageSelectListener() {

        @Override
        public void onImageSelect() {
            JUtils.Log("Avatar");
            getView().getExpansion().showProgressDialog("加载中");
        }

        @Override
        public void onImageLoaded(Uri uri) {
            provider.corpImage(uri, 300, 300, new OnImageSelectListener() {
                @Override
                public void onImageSelect() {

                }

                @Override
                public void onImageLoaded(Uri uri) {
                    getView().getExpansion().dismissProgressDialog();
                    data.setAvatar(uri.toString());
                    getView().setAvatar(uri);
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

    OnImageSelectListener mPicturesListener = new OnImageSelectListener() {

        @Override
        public void onImageSelect() {
            JUtils.Log("Pictures");
            getView().getExpansion().showProgressDialog("加载中");
        }

        @Override
        public void onImageLoaded(Uri uri) {
            getView().getExpansion().dismissProgressDialog();
            JUtils.Log("Pictures:"+uri.toString()+":"+uri.getPath());
            getView().addPicture(uri);
            if (data.getPicture()==null)data.setPicture(new ArrayList<>());
            data.getPicture().add(uri.toString());
        }

        @Override
        public void onError() {
            getView().getExpansion().dismissProgressDialog();
            JUtils.Toast("加载错误");
        }
    };

    @Override
    protected void onCreate(PartAddActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        provider = new ImageProvider(getView());
        data = (Part) getView().getIntent().getSerializableExtra("data");
        if (data == null)data = new Part();
        data.setType(getView().getIntent().getStringExtra("type"));
        publishObject(data);
    }

    public void setAvatar(int style){
        switch (style) {
            case 0:
                provider.getImageFromCamera(mAvatarListener);
                break;
            case 1:
                provider.getImageFromAlbum(mAvatarListener);
                break;
            case 2:
                provider.getImageFromNet(mAvatarListener);
                break;
        }
    }

    public void setPicture(int style){
        switch (style) {
            case 0:
                provider.getImageFromCamera(mPicturesListener);
                break;
            case 1:
                provider.getImageFromAlbum(mPicturesListener);
                break;
            case 2:
                provider.getImageFromNet(mPicturesListener);
                break;
        }
    }

    public void publishEdit(){
        if(TextUtils.isEmpty(data.getType())){
            JUtils.Toast("请选择配件类型");
            return;
        }
        if(TextUtils.isEmpty(data.getAvatar())){
            JUtils.Toast("请选择配件略缩图");
            return;
        }
        if (data.getPicture() == null){
            data.setPicture(new ArrayList<>());
        }
        Observable.just(data)
                .flatMap(part -> {
                    if (!Uri.parse(part.getAvatar()).getScheme().equals("http")){
                        return ImageModel.getInstance().putImageSync(new File(Uri.parse(part.getAvatar()).getPath()))
                                .map(s -> {
                                    data.setAvatar(s);
                                    return data;
                                });
                    }
                    return Observable.just(part);
                })
                .flatMapIterable(Part::getPicture)
                .doOnCompleted(() -> JUtils.Log("Completed"))
                .filter(s -> !Uri.parse(s).getScheme().equals("http"))
                .map(s2 -> {
                    File file = new File(Uri.parse(s2).getPath());
                    return file;
                })
                .toList()
                .doOnCompleted(() -> JUtils.Log("Completed2"))
                .flatMap(strings -> ImageModel.getInstance().putImageSync(strings.toArray(new File[strings.size()])))
                .doOnNext(s -> data.getPicture().add(s))
                .toList()
                .map(strings -> data)
                .flatMapIterable(Part::getPicture)
                .filter(s -> Uri.parse(s).getScheme().equals("http"))
                .toList()
                .doOnNext(strings -> data.setPicture(strings))
                .map(strings -> data)
                .flatMap((Func1<Part, Observable<?>>) part -> DataModel.getInstance().addPart(data))
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .compose(new ProgressDialogTransform<>(getView(),"提交中"))
                .subscribe(info ->{
                    JUtils.Toast("上传成功");
                    getView().setResult(Activity.RESULT_OK);
                    getView().finish();
                });
    }


    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        provider.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onViewDelete(int index) {
        data.getPicture().remove(index);
    }
}
