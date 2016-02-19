package com.jude.automobile.ui.viewholder;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.domain.entities.Assemble;
import com.jude.automobile.ui.PartDetailActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.tagview.TAGView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class AssembleViewHolder extends BaseViewHolder<Assemble> {
    @Bind(R.id.type)
    TAGView type;
    @Bind(R.id.note)
    TextView note;
    @Bind(R.id.avatar)
    ImageView avatar;

    private Assemble data;

    public AssembleViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_part);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(), PartDetailActivity.class);
            i.putExtra("id",data.getPartId());
            getContext().startActivity(i);
        });
    }

    @Override
    public void setData(Assemble data) {
        this.data = data;
        type.setText(data.getPartType());
        note.setText(data.getNote());
        Glide.with(getContext())
                .load(ImageModel.getSmallImage(data.getPartAvatar()))
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(avatar);
    }
}
