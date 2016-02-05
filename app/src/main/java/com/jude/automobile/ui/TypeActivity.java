package com.jude.automobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.jude.automobile.R;
import com.jude.automobile.app.APP;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.presenter.TypePresenter;
import com.jude.automobile.ui.viewholder.ModelViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by zhuchenxi on 16/1/19.
 */
@RequiresPresenter(TypePresenter.class)
public class TypeActivity extends BeamListActivity<TypePresenter,Model> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getPresenter().data.getName()+"车型");
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
        return new ModelViewHolder(parent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit){
            Intent i = new Intent(this,TypeAddActivity.class);
            i.putExtra("data",getPresenter().data);
            startActivityForResult(i,0);
            return true;
        }else if (item.getItemId() == R.id.add){
            Intent i = new Intent(this,ModelAddActivity.class);
            i.putExtra("type",getPresenter().data);
            startActivityForResult(i,0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            getListView().setRefreshing(true,true);
        }else if (resultCode == APP.RESULT_DELETE){
            setResult(RESULT_OK);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
