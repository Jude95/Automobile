package com.jude.automobile.ui;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.automobile.domain.entities.Brand;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.domain.entities.Search;
import com.jude.automobile.ui.viewholder.BrandViewHolder;
import com.jude.automobile.ui.viewholder.TypeViewHolder;
import com.jude.automobile.ui.viewholder.SearchViewHolder;
import com.jude.automobile.ui.viewholder.LineViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class MainAdapter extends RecyclerArrayAdapter<Object> {
    private static final int TYPE_LINE = 1;
    private static final int TYPE_TYPE = 2;
    private static final int TYPE_Model = 3;
    private static final int TYPE_SEARCH = 4;

    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        Object o = getItem(position);
        if (o instanceof Brand)  return TYPE_LINE;
        else if (o instanceof Line) return TYPE_TYPE;
        else if (o instanceof Type) return TYPE_Model;
        else if (o instanceof Search) return TYPE_SEARCH;
        return super.getViewType(position);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_LINE:return new BrandViewHolder(parent);
            case TYPE_TYPE:return new LineViewHolder(parent);
            case TYPE_Model:return new TypeViewHolder(parent);
            case TYPE_SEARCH:return new SearchViewHolder(parent);
        }
        return null;
    }
}
