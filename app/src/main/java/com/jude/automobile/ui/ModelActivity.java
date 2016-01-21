package com.jude.automobile.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.presenter.ModelPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by zhuchenxi on 16/1/19.
 */
@RequiresPresenter(ModelPresenter.class)
public class ModelActivity extends BeamListActivity<ModelPresenter, Part> {

    @Bind(R.id.power)
    TextView power;
    @Bind(R.id.displacement)
    TextView displacement;
    @Bind(R.id.cylinders)
    TextView cylinders;
    @Bind(R.id.valve)
    TextView valve;
    @Bind(R.id.structure)
    TextView structure;
    @Bind(R.id.drive)
    TextView drive;
    @Bind(R.id.engine)
    TextView engine;
    @Bind(R.id.fuel)
    TextView fuel;
    @Bind(R.id.fuel_feed)
    TextView fuelFeed;
    @Bind(R.id.engine_code)
    TextView engineCode;

    public View createHeadView(ViewGroup parent, Model data) {
        View view = LayoutInflater.from(this).inflate(R.layout.head_model, parent, false);
        ButterKnife.bind(this,view);
        power.setText(data.getPower());
        displacement.setText(data.getDisplacement());
        cylinders.setText(data.getCylinders());
        valve.setText(data.getValve());
        structure.setText(data.getStructure());
        drive.setText(data.getDrive());
        engine.setText(data.getEngine());
        fuel.setText(data.getFuel());
        fuelFeed.setText(data.getFuelFeed());
        engineCode.setText(data.getEngineCode());
        return view;
    }


    @Override
    public int getLayout() {
        return R.layout.activity_recyclerview;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra("name"));
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new PartViewHolder(parent);
    }
}
