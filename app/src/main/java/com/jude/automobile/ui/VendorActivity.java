package com.jude.automobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.presenter.VendorPresenter;
import com.jude.automobile.ui.viewholder.LineViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by zhuchenxi on 16/2/19.
 */
@RequiresPresenter(VendorPresenter.class)
public class VendorActivity extends BeamListActivity<VendorPresenter,Line> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getPresenter().data.getName());
    }

    @Override
    public BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new LineViewHolder(parent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit){
            Intent i = new Intent(this,VendorAddActivity.class);
            i.putExtra("data", (Parcelable) getPresenter().data);
            startActivityForResult(i,0);
            return true;
        }else if (item.getItemId() == R.id.add){
            Intent i = new Intent(this,LineAddActivity.class);
            i.putExtra("vendor", (Parcelable) getPresenter().data);
            startActivityForResult(i,0);
        }
        return super.onOptionsItemSelected(item);
    }
}
