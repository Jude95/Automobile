package com.jude.automobile.ui;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jude.automobile.R;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.domain.entities.Assemble;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.presenter.AssemblePresenter;
import com.jude.automobile.ui.viewholder.AssembleEditViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.List;

/**
 * Created by zhuchenxi on 16/2/3.
 */
@RequiresPresenter(AssemblePresenter.class)
public class AssembleActivity extends BeamListActivity<AssemblePresenter,Assemble> {

    @Override
    public ListConfig getConfig() {
        return super.getConfig().setRefreshAble(true);
    }

    @Override
    public BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new AssembleEditViewHolder(parent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Part part = (Part) data.getParcelableExtra("data");
            new MaterialDialog.Builder(this)
                    .title("请输入绑定备注别名")
                    .content("配件："+part.getBrand()+part.getType()+" "+part.getDrawingNumber())
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("备注别名", "", (dialog, input) -> {
                        getPresenter().publishEdit(input.toString(),part.getId());
                    }).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem add = menu.add("添加");
        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        add.setIcon(R.drawable.add);
        add.setOnMenuItemClickListener(item -> {
            List<String> list = DataModel.getInstance().getConstantParams().getPart();
            new MaterialDialog.Builder(this)
                    .title("添加配件")
                    .items(list.toArray(new String[list.size()]))
                    .itemsCallback((dialog, itemView, which, text) -> {
                        if (!TextUtils.isEmpty(text)) {
                            Intent i = new Intent(this, PartSelectActivity.class);
                            i.putExtra("line", text);
                            startActivityForResult(i, 12);
                        }
                    })
                    .show();
            return true;
        });
        return true;
    }
}
