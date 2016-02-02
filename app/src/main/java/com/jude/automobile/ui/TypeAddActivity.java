package com.jude.automobile.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.presenter.TypeAddPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zhuchenxi on 16/2/1.
 */
@RequiresPresenter(TypeAddPresenter.class)
public class TypeAddActivity extends BeamDataActivity<TypeAddPresenter, Type> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.img_line_avatar)
    ImageView imgLineAvatar;
    @Bind(R.id.tv_line_name)
    TextView tvLineName;
    @Bind(R.id.view_line)
    LinearLayout viewLine;
    @Bind(R.id.name)
    TextInputLayout name;
    @Bind(R.id.word)
    TextInputLayout word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_add);
        ButterKnife.bind(this);
        if(getPresenter().type.getId() == 0){
            setTitle("添加车型");
        }else{
            setTitle("修改车型");
        }
    }

    @Override
    public void setData(Type data) {
        super.setData(data);
        if (data!=null){
            if (!TextUtils.isEmpty(data.getLineName())){
                tvLineName.setText(data.getLineName());
                Glide.with(this)
                        .load(ImageModel.getSmallImage(data.getLineAvatar()))
                        .bitmapTransform(new CropCircleTransformation(this))
                        .into(imgLineAvatar);
            }
            name.getEditText().setText(data.getName());
            word.getEditText().setText(data.getWord());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ok,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ok){
            getPresenter().type.setName(name.getEditText().getText().toString());
            getPresenter().type.setWord(word.getEditText().getText().toString());
            getPresenter().publishEdit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
