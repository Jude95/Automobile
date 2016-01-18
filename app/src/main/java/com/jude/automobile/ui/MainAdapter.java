package com.jude.automobile.ui;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.automobile.domain.entities.Line;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class MainAdapter extends RecyclerArrayAdapter<Object> {
    private static final int TYPE_LINE = 1;

    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if (getItem(position) instanceof Line){
            return TYPE_LINE;
        }
        JUtils.Log("FUCKING"+getItem(position).getClass().getName());

        return super.getViewType(position);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_LINE:return new LineViewHolder(parent);
        }
        JUtils.Log("FUCK");
        return null;
    }
}
