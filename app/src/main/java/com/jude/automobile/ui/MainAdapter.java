package com.jude.automobile.ui;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.domain.entities.Search;
import com.jude.automobile.domain.entities.Type;
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
        if (o instanceof Line)  return TYPE_LINE;
        else if (o instanceof Type) return TYPE_TYPE;
        else if (o instanceof Model) return TYPE_Model;
        else if (o instanceof Search) return TYPE_SEARCH;
        return super.getViewType(position);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_LINE:return new LineViewHolder(parent);
            case TYPE_TYPE:return new TypeViewHolder(parent);
            case TYPE_Model:return new ModelViewHolder(parent);
            case TYPE_SEARCH:return new SearchViewHolder(parent);
        }
        return null;
    }
}
