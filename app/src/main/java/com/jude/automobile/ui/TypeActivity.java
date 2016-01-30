package com.jude.automobile.ui;

import android.os.Bundle;
import android.view.ViewGroup;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.presenter.TypePresenter;
import com.jude.automobile.ui.viewholder.ModelViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by zhuchenxi on 16/1/19.
 */
@RequiresPresenter(TypePresenter.class)
public class TypeActivity extends BeamListActivity<TypePresenter,Model> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra("name")+"车型");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new ModelViewHolder(parent);
    }
}
