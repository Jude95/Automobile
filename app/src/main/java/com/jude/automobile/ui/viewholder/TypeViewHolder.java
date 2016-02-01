package com.jude.automobile.ui.viewholder;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.ui.TypeActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.tagview.TAGView;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class TypeViewHolder extends BaseViewHolder<Type> {
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.line)
    TAGView line;

    private Type data;

    public TypeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_type);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(),TypeActivity.class);
            i.putExtra("data",data);
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
