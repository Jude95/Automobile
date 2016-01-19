package com.jude.automobile.ui;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Type;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.tagview.TAGView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class TypeViewHolder extends BaseViewHolder<Type> {
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.line)
    TAGView line;

    private Type data;

    public TypeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_type);
        ButterKnife.inject(this,itemView);
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(),TypeActivity.class);
            i.putExtra("id",data.getId());
            i.putExtra("name",data.getName());
            getContext().startActivity(i);
        });
    }

    @Override
    public void setData(Type data) {
        this.data = data;
        name.setText(data.getName());
        line.setText(data.getLineName()+"车系");
    }
}