package com.jude.automobile.ui.viewholder;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.ui.PartDetailActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.tagview.TAGView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.subjects.BehaviorSubject;

/**
 * Created by zhuchenxi on 16/2/3.
 */
public class PartSelectViewHolder extends BaseViewHolder<Part> {
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.type)
    TAGView type;
    @Bind(R.id.brand)
    TextView brand;
    @Bind(R.id.drawing_number)
    TextView drawingNumber;
    @Bind(R.id.radio)
    RadioButton radio;

    private Part data;

    public static final BehaviorSubject<Part> mSelectSubject = BehaviorSubject.create();

    public PartSelectViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_part_detail);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(), PartDetailActivity.class);
            i.putExtra("id",data.getId());
            getContext().startActivity(i);
        });
        mSelectSubject.subscribe(b->{
                radio.setChecked(b != null&&data != null && (data.getId() == b.getId()));
        });
        radio.setOnClickListener(v->mSelectSubject.onNext(data));
    }

    @Override
    public void setData(Part data) {
        this.data = data;
        Glide.with(getContext())
                .load(ImageModel.getSmallImage(data.getAvatar()))
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(avatar);
        type.setText(data.getType());
        brand.setText(data.getBrand());
        drawingNumber.setText(data.getDrawingNumber());
    }
}
