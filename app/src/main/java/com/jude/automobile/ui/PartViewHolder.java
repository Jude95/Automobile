package com.jude.automobile.ui;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Part;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.tagview.TAGView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class PartViewHolder extends BaseViewHolder<Part> {
    @InjectView(R.id.type)
    TAGView type;
    @InjectView(R.id.note)
    TextView note;
    @InjectView(R.id.avatar)
    ImageView avatar;

    private Part data;

    public PartViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_part);
        ButterKnife.inject(this, itemView);

    }

    @Override
    public void setData(Part data) {
        this.data = data;
        type.setText(data.getType());
        note.setText(data.getNote());
        Glide.with(getContext())
                .load(data.getAvatar())
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(avatar);
    }
}