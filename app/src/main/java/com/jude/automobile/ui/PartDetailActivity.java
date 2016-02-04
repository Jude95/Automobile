package com.jude.automobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.domain.entities.ImageInfo;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.presenter.PartDetailPresenter;
import com.jude.automobile.ui.viewholder.NetImageViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/2/4.
 */
@RequiresPresenter(PartDetailPresenter.class)
public class PartDetailActivity extends BeamListActivity<PartDetailPresenter, ImageInfo> {

    @Bind(R.id.image_avatar)
    ImageView imageAvatar;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_brand)
    TextView tvBrand;
    @Bind(R.id.drawing_number)
    TextView drawingNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View getHeader(ViewGroup parent, Part part) {
        View view = LayoutInflater.from(this).inflate(R.layout.head_part, parent, false);
        ButterKnife.bind(this,view);
        Glide.with(this).load(ImageModel.getSmallImage(part.getAvatar())).into(imageAvatar);
        tvType.setText(part.getType());
        tvBrand.setText(part.getBrand());
        drawingNumber.setText(part.getDrawingNumber());
        return view;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig().setRefreshAble(true);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new NetImageViewHolder(parent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit){
            Intent i = new Intent(this,PartAddActivity.class);
            i.putExtra("data",getPresenter().data);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)getListView().setRefreshing(true,true);
    }

}
