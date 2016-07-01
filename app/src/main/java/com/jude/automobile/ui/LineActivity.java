package com.jude.automobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.jude.automobile.R;
import com.jude.automobile.app.APP;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.presenter.LinePresenter;
import com.jude.automobile.ui.viewholder.TypeViewHolder;
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
        setTitle(getPresenter().data.getName()+"车系");
    }

    @Override
    public BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new TypeViewHolder(parent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit){
            Intent i = new Intent(this,LineAddActivity.class);
            i.putExtra("data", (Parcelable) getPresenter().data);
            startActivityForResult(i,0);
            return true;
        }else if (item.getItemId() == R.id.add){
            Intent i = new Intent(this,TypeAddActivity.class);
            i.putExtra("line", (Parcelable) getPresenter().data);
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
