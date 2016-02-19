package com.jude.automobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.app.APP;
import com.jude.automobile.domain.entities.Assemble;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.presenter.TypePresenter;
import com.jude.automobile.ui.viewholder.AssembleViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/1/19.
 */
@RequiresPresenter(TypePresenter.class)
public class TypeActivity extends BeamListActivity<TypePresenter, Assemble> {

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

    public View createHeadView(ViewGroup parent, Type data) {
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
    protected ListConfig getConfig() {
        return super.getConfig().setRefreshAble(true);
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
        return new AssembleViewHolder(parent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem edit = menu.add("编辑");
        edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        edit.setIcon(R.drawable.edit);
        edit.setOnMenuItemClickListener(item -> {
            Intent i = new Intent(this,TypeAddActivity.class);
            i.putExtra("data", (Parcelable) getPresenter().data);
            startActivityForResult(i,0);
            return true;
        });

        MenuItem bind = menu.add("绑定");
        bind.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        bind.setIcon(R.drawable.bind);
        bind.setOnMenuItemClickListener(item -> {
            Intent i = new Intent(this,AssembleActivity.class);
            i.putExtra("id",getPresenter().data.getId());
            i.putExtra("name",getPresenter().data.getName());

            ArrayList<Assemble> arrays = new ArrayList<>();
            for (int i1 = 0; i1 < getPresenter().getAdapter().getCount(); i1++) {
                arrays.add(getPresenter().getAdapter().getItem(i1));
            }
            i.putParcelableArrayListExtra("assemble",arrays);
            startActivityForResult(i,0);
            return true;
        });
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getListView().setRefreshing(true,true);
        if (resultCode == APP.RESULT_DELETE){
            setResult(RESULT_OK);
            finish();
        }
    }
}
