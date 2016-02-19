package com.jude.automobile.ui.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.ui.LineActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.tagview.TAGView;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class LineViewHolder extends BaseViewHolder<Line> {
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.line)
    TAGView line;

    private Line data;

    public LineViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_type);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(),LineActivity.class);
            i.putExtra("data", (Parcelable) data);
            ((Activity)getContext()).startActivityForResult(i,0);
        });
    }

    @Override
    public void setData(Line data) {
        this.data = data;
        name.setText(data.getName());
        line.setText(data.getVendorName()+"车系");
    }
}
