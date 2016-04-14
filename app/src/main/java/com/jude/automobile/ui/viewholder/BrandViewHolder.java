package com.jude.automobile.ui.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.domain.entities.Brand;
import com.jude.automobile.ui.BrandActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class BrandViewHolder extends BaseViewHolder<Brand> {
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.name)
    TextView name;

    private Brand data;

    public BrandViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_brand);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(),BrandActivity.class);
            i.putExtra("data", (Parcelable) data);
            ((Activity)getContext()).startActivityForResult(i,0);
        });
    }

    @Override
    public void setData(Brand data) {
        this.data = data;
        name.setText(data.getName());
        Glide.with(getContext()).load(ImageModel.getSmallImage(data.getAvatar())).into(avatar);
    }
}
