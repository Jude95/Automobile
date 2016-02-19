package com.jude.automobile.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.presenter.LineAddPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/2/1.
 */
@RequiresPresenter(LineAddPresenter.class)
public class LineAddActivity extends BeamDataActivity<LineAddPresenter, Line> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_line_name)
    TextView tvLineName;
    @Bind(R.id.view_line)
    LinearLayout viewLine;
    @Bind(R.id.name)
    TextInputLayout name;
    @Bind(R.id.word)
    TextInputLayout word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_add);
        ButterKnife.bind(this);
        if(getPresenter().line.getId() == 0){
            setTitle("添加车系");
        }else{
            setTitle("修改车系");
        }
    }

    @Override
    public void setData(Line data) {
        super.setData(data);
        if (data!=null){
            if (!TextUtils.isEmpty(data.getVendorName())){
                tvLineName.setText(data.getVendorName());
            }
            name.getEditText().setText(data.getName());
            word.getEditText().setText(data.getWord());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_ok,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ok){
            getPresenter().line.setName(name.getEditText().getText().toString());
            getPresenter().line.setWord(word.getEditText().getText().toString());
            getPresenter().publishEdit();
            return true;
        }else if (item.getItemId() == R.id.delete){
            new MaterialDialog.Builder(this)
                    .title("删除")
                    .content("你确定要删除本车系吗?")
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive((dialog, which) -> getPresenter().delete())
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }


}
