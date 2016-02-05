package com.jude.automobile.ui;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.presenter.PartAddPresenter;
import com.jude.automobile.utils.NetImagePieceView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.jude.exgridview.PieceViewGroup;
import com.jude.utils.JUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/2/3.
 */
@RequiresPresenter(PartAddPresenter.class)
public class PartAddActivity extends BeamDataActivity<PartAddPresenter, Part> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.brand)
    TextInputLayout brand;
    @Bind(R.id.drawing_number)
    TextInputLayout drawingNumber;
    @Bind(R.id.grid_pictures)
    PieceViewGroup gridPictures;

    boolean hasInitPictures = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_add);
        ButterKnife.bind(this);
        RxPermissions.getInstance(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now

                    } else {
                        // Oups permission denied
                        JUtils.Toast("讨厌～");
                        finish();
                    }
                });
        avatar.setOnClickListener(v->{
            new MaterialDialog.Builder(PartAddActivity.this)
                    .title("选择图片来源")
                    .items(new String[]{"拍照", "相册", "网络"})
                    .itemsCallback((materialDialog, view, i, charSequence) -> getPresenter().setAvatar(i))
                    .show();
        });
        gridPictures.setOnAskViewListener(() -> new MaterialDialog.Builder(PartAddActivity.this)
                .title("选择图片来源")
                .items(new String[]{"拍照", "相册", "网络"})
                .itemsCallback((materialDialog, view, i, charSequence) -> getPresenter().setPicture(i))
                .show());
        gridPictures.setOnViewDeleteListener(getPresenter());
        tvType.setOnClickListener(v -> {
            List<String> list = DataModel.getInstance().getConstantParams().getPart();
            int index = list.indexOf(getPresenter().data.getType());
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .items(list.toArray(new String[list.size()]))
                    .itemsCallbackSingleChoice(index, (dialog, view, which, text) -> {
                        tvType.setText(text);
                        return true;
                    })
                    .positiveText("确定")
                    .show();
        });
    }

    @Override
    public void setData(Part data) {
        if (data != null) {
            if (!TextUtils.isEmpty(data.getType())){
                tvType.setText(data.getType());
            }else{
                tvType.setText((String) tvType.getTag());
            }
            if (!TextUtils.isEmpty(data.getAvatar()))
                Glide.with(this).load(data.getAvatar()).into(avatar);
            brand.getEditText().setText(data.getBrand());
            drawingNumber.getEditText().setText(data.getDrawingNumber());
            if (!hasInitPictures)initPicture(data.getPicture());
        }
    }

    public void initPicture(List<String> pictures){
        hasInitPictures = true;
        for (String s : pictures) {
            addPicture(Uri.parse(s));
        }
    }

    public void setAvatar(Uri uri){
        Glide.with(this).load(uri).into(avatar);
    }

    public void addPicture(Uri uri){
        NetImagePieceView pieceView = new NetImagePieceView(this);
        pieceView.setImage(uri);
        gridPictures.addView(pieceView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_ok,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ok){
            getPresenter().data.setType(tvType.getText().toString());
            getPresenter().data.setBrand(brand.getEditText().getText().toString());
            getPresenter().data.setDrawingNumber(drawingNumber.getEditText().getText().toString());
            getPresenter().publishEdit();
            return true;
        }else if (item.getItemId() == R.id.delete){

        }
        return super.onOptionsItemSelected(item);
    }
}
