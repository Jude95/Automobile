package com.jude.automobile.ui;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.presenter.LineAddPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.jude.utils.JUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/1/31.
 */
@RequiresPresenter(LineAddPresenter.class)
public class LineAddActivity extends BeamDataActivity<LineAddPresenter, Line> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.logo)
    ImageView logo;
    @Bind(R.id.name)
    TextInputLayout name;
    @Bind(R.id.word)
    TextInputLayout word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_add);
        ButterKnife.bind(this);
        logo.setOnClickListener(v->{
            new MaterialDialog.Builder(this)
                    .title("选择图片来源")
                    .items(new String[]{"拍照", "相册", "网络"})
                    .itemsCallback((materialDialog, view, i, charSequence) -> getPresenter().editLogo(i)).show();
        });
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
        if(getPresenter().data.getId() == 0){
            setTitle("添加车系");
        }else{
            setTitle("修改车系");
        }
    }

    @Override
    public void setData(Line data) {
        if (data!=null){
            Glide.with(this).load(ImageModel.getSmallImage(data.getAvatar())).placeholder(R.drawable.ex_add).into(logo);
            name.getEditText().setText(data.getName());
            word.getEditText().setText(data.getWord());
        }
    }

    public void setImage(Uri uri){
        Glide.with(this).load(uri).into(logo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_ok,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ok){
            getPresenter().data.setName(name.getEditText().getText().toString());
            getPresenter().data.setWord(word.getEditText().getText().toString());
            getPresenter().publishEdit();
            return true;
        }else if (item.getItemId() == R.id.delete){
            new MaterialDialog.Builder(this)
                    .title("删除")
                    .content("你确定要删除本车系吗?")
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive((dialog, which) -> getPresenter().delete())
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
