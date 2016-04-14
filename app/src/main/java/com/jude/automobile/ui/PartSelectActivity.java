package com.jude.automobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.clans.fab.FloatingActionButton;
import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.presenter.PartSelectPresenter;
import com.jude.automobile.ui.viewholder.PartSelectViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/2/3.
 */
@RequiresPresenter(PartSelectPresenter.class)
public class PartSelectActivity extends BeamListActivity<PartSelectPresenter, Part> {

    @Bind(R.id.add)
    FloatingActionButton add;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle("选择" + getIntent().getStringExtra("line"));
        add.setOnClickListener(v->{
            Intent i = new Intent(this,PartAddActivity.class);
            i.putExtra("line",getPresenter().type);
            startActivityForResult(i,0);
        });
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig().setRefreshAble(true);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_part_select;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new PartSelectViewHolder(parent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem search = menu.add("搜索");
        search.setIcon(R.drawable.search);
        search.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        search.setOnMenuItemClickListener(item1 -> {
            new MaterialDialog.Builder(this)
                    .title("配件搜索")
                    .content("输入配件图号")
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("品牌", "", (dialog, input) -> {
                        getPresenter().drawing_number = input.toString();
                        getListView().setRefreshing(true,true);
                    }).show();
            return true;
        });

        MenuItem item = menu.add("确定");
        item.setIcon(R.drawable.ok);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setOnMenuItemClickListener(item1 -> {
            getPresenter().selected(PartSelectViewHolder.mSelectSubject.getValue());
            return true;
        });
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            getListView().setRefreshing(true,true);
        }
    }
}
