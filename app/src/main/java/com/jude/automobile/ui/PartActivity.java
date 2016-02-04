package com.jude.automobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.presenter.PartPresenter;
import com.jude.automobile.ui.viewholder.PartDetailViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by zhuchenxi on 16/2/3.
 */
@RequiresPresenter(PartPresenter.class)
public class PartActivity extends BeamListActivity<PartPresenter,Part> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra("type"));
        getPresenter().getAdapter().setOnItemClickListener(position -> {
            Intent i = new Intent();
            i.putExtra("data",getPresenter().getAdapter().getItem(position));
            setResult(RESULT_OK,i);
            finish();
        });
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
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new PartDetailViewHolder(parent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add){
            Intent i = new Intent(this,PartAddActivity.class);
            i.putExtra("type",getIntent().getStringExtra("type"));
            startActivityForResult(i,0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)getListView().setRefreshing(true,true);
    }
}
