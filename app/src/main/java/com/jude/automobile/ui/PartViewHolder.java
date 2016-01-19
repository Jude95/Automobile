package com.jude.automobile.ui;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.jude.automobile.domain.entities.Part;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class PartViewHolder extends BaseViewHolder<Part> {
    public PartViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }
}
