package com.jude.automobile.ui.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Vendor;
import com.jude.automobile.ui.VendorActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/2/19.
 */
public class VendorViewHolder extends BaseViewHolder<Vendor> {
    @Bind(R.id.tv_vendor)
    TextView tvVendor;

    private Vendor data;

    public VendorViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_vendor);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(), VendorActivity.class);
            i.putExtra("data", (Parcelable) data);
            ((Activity)getContext()).startActivityForResult(i,0);
        });
    }

    @Override
    public void setData(Vendor data) {
        this.data = data;
        tvVendor.setText(data.getName());
    }
}
