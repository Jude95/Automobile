package com.jude.automobile.ui.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.ui.TypeActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class TypeViewHolder extends BaseViewHolder<Type> {
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.displacement)
    TextView displacement;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.engine)
    TextView engine;

    private Type data;

    public TypeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_type);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(),TypeActivity.class);
            i.putExtra("id",data.getId());
            i.putExtra("name",data.getName());
            ((Activity)getContext()).startActivityForResult(i,0);
        });
    }

    @Override
    public void setData(Type data) {
        this.data = data;
        name.setText(data.getName());
        displacement.setText(data.getDisplacementTech());
        date.setText(data.getTime());
        engine.setText(data.getEngineCode());
    }
}
