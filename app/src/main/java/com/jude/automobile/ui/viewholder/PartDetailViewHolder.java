package com.jude.automobile.ui.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.domain.entities.Part;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.tagview.TAGView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zhuchenxi on 16/2/3.
 */
public class PartDetailViewHolder extends BaseViewHolder<Part> {
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.type)
    TAGView type;
    @Bind(R.id.brand)
    TextView brand;
    @Bind(R.id.drawing_number)
    TextView drawingNumber;

    public PartDetailViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_part_detail);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(Part data) {
        Glide.with(getContext())
                .load(ImageModel.getSmallImage(data.getAvatar()))
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(avatar);
        type.setText(data.getType());
        brand.setText(data.getBrand());
        drawingNumber.setText(data.getDrawingNumber());
    }
}
