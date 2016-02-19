package com.jude.automobile.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.domain.entities.ImageInfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/2/4.
 */
public class NetImageViewHolder extends BaseViewHolder<ImageInfo> {

    private ImageInfo image;

    public NetImageViewHolder(View parent) {
        super(new ImageView(parent.getContext()));
        ImageView imageView = (ImageView)itemView;
        imageView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setPadding(JUtils.dip2px(16),0,JUtils.dip2px(16),0);
    }

    @Override
    public void setData(ImageInfo data) {
        super.setData(data);
        image = data;
        int height = (int) (((float)JUtils.getScreenWidth())/data.getWidth()*data.getHeight());
        ViewGroup.LayoutParams params = itemView.getLayoutParams();
        params.height = height;
        itemView.setLayoutParams(params);
        JUtils.Log("Height:"+height);
        Glide.with(getContext()).load(ImageModel.getLargeImage(data.getUrl())).into((ImageView) itemView);
    }
}
