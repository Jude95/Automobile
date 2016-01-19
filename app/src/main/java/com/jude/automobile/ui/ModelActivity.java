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
import butterknife.InjectView;

/**
 * Created by zhuchenxi on 16/1/19.
 */
@RequiresPresenter(ModelPresenter.class)
public class ModelActivity extends BeamListActivity<ModelPresenter, Part> {

    @InjectView(R.id.power)
    TextView power;
    @InjectView(R.id.displacement)
    TextView displacement;
    @InjectView(R.id.cylinders)
    TextView cylinders;
    @InjectView(R.id.valve)
    TextView valve;
    @InjectView(R.id.structure)
    TextView structure;
    @InjectView(R.id.drive)
    TextView drive;
    @InjectView(R.id.engine)
    TextView engine;
    @InjectView(R.id.fuel)
    TextView fuel;
    @InjectView(R.id.fuel_feed)
    TextView fuelFeed;
    @InjectView(R.id.engine_code)
    TextView engineCode;

    public View createHeadView(ViewGroup parent, Model data) {
        View view = LayoutInflater.from(this).inflate(R.layout.head_model, parent, false);
        ButterKnife.inject(this,view);
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
