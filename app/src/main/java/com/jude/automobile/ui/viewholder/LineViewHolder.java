package com.jude.automobile.ui.viewholder;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.ui.LineActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.Bind;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class LineViewHolder extends BaseViewHolder<Line> {
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.name)
    TextView name;

    private Line data;

    public LineViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_line);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(),LineActivity.class);
            i.putExtra("id",data.getId());
            i.putExtra("name",data.getName());
            getContext().startActivity(i);
        });
    }

    @Override
    public void setData(Line data) {
        this.data = data;
        name.setText(data.getName());
        Glide.with(getContext()).load(data.getAvatar()).bitmapTransform(new CropCircleTransformation(getContext())).into(avatar);
    }
}
