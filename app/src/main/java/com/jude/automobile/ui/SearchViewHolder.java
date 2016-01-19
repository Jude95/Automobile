package com.jude.automobile.ui;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Search;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class SearchViewHolder extends BaseViewHolder<Search> {
    @InjectView(R.id.word)
    TextView word;
    @InjectView(R.id.type)
    TextView type;

    public SearchViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_search);
        ButterKnife.inject(this,itemView);
    }

    @Override
    public void setData(Search data) {
        word.setText(data.getWord());
        switch (data.getType()){
            case Search.TYPE_LINE:type.setText("车系搜索");break;
            case Search.TYPE_TYPE:type.setText("车型搜索");break;
            case Search.TYPE_Model:type.setText("车款搜索");break;
        }
    }
}
