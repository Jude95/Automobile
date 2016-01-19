package com.jude.automobile.ui;

import android.os.Bundle;
import android.view.ViewGroup;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.presenter.LinePresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by zhuchenxi on 16/1/19.
 */
@RequiresPresenter(LinePresenter.class)
public class LineActivity extends BeamListActivity<LinePresenter,Type> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra("name")+"车系");
    }


    @Override
    public int getLayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new TypeViewHolder(parent);
    }
}
