package com.jude.automobile.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;

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
        getPresenter().getAdapter().setOnItemClickListener(position -> {
            Intent i = new Intent(PartDetailActivity.this, ImageViewActivity.class);
            ArrayList<Uri> uris = new ArrayList<Uri>();
            for (int i1 = 0; i1 < getPresenter().getAdapter().getCount(); i1++) {
                uris.add(Uri.parse(getPresenter().getAdapter().getItem(i1).getUrl()));
            }
            i.putParcelableArrayListExtra(ImageViewActivity.KEY_URIS, uris);
            i.putExtra(ImageViewActivity.KEY_INDEX,position);
            startActivity(i);
        });
    }

    public View getHeader(ViewGroup parent, Part part) {
        View view = LayoutInflater.from(this).inflate(R.layout.head_part, parent, false);
        ButterKnife.bind(this, view);
        Glide.with(this).load(ImageModel.getSmallImage(part.getAvatar())).into(imageAvatar);
        imageAvatar.setOnClickListener(v->{
            Intent i = new Intent(this,ImageViewActivity.class);
            i.putExtra(ImageViewActivity.KEY_URI, Uri.parse(part.getAvatar()));
            startActivity(i);
        });
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
        MenuItem item = menu.add("编辑");
        item.setIcon(R.drawable.edit);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setOnMenuItemClickListener(item1 -> {
            Intent i = new Intent(this, PartAddActivity.class);
            i.putExtra("data", (Parcelable) getPresenter().data);
            startActivity(i);
            return true;
        });
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) getListView().setRefreshing(true, true);
    }

}
