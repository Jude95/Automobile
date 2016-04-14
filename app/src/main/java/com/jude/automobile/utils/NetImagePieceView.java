package com.jude.automobile.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.exgridview.PieceView;

/**
 * Created by Mr.Jude on 2015/3/14.
 */
public class NetImagePieceView extends PieceView {
    ImageView imageView;

    public NetImagePieceView(Context context) {
        super(context);
    }

    public NetImagePieceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NetImagePieceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {
        super.initView();
        imageView = new ImageView(getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(imageView);
        setWillNotDraw(false);
    }

    public void setImage(Uri uri){
        Glide.with(getContext()).load(uri).override(300, 300).into(imageView);
    }

    public void setImage(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }


}
