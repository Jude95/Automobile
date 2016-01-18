package com.jude.automobile.ui;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Line;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class LineViewHolder extends BaseViewHolder<Line> {
    @InjectView(R.id.avatar)
    ImageView avatar;
    @InjectView(R.id.name)
    TextView name;

    public LineViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_line);
        ButterKnife.inject(this,itemView);
    }

    @Override
    public void setData(Line data) {
        name.setText(data.getName());
        Glide.with(getContext()).load(data.getAvatar()).bitmapTransform(new CropCircleTransformation(getContext())).into(avatar);
    }
}
