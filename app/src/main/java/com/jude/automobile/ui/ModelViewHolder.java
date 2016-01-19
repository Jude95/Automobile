package com.jude.automobile.ui;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Model;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class ModelViewHolder extends BaseViewHolder<Model> {
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.displacement)
    TextView displacement;
    @InjectView(R.id.date)
    TextView date;
    @InjectView(R.id.engine)
    TextView engine;

    private Model data;

    public ModelViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_model);
        ButterKnife.inject(this,itemView);
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(),ModelActivity.class);
            i.putExtra("id",data.getId());
            i.putExtra("name",data.getName());
            getContext().startActivity(i);
        });
    }

    @Override
    public void setData(Model data) {
        this.data = data;
        name.setText(data.getName());
        displacement.setText(data.getDisplacementTech());
        date.setText(data.getTime());
    }
}
