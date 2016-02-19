package com.jude.automobile.ui.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Search;
import com.jude.automobile.ui.MainActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class SearchViewHolder extends BaseViewHolder<Search> {
    @Bind(R.id.word)
    TextView word;
    @Bind(R.id.type)
    TextView type;

    private Search data;

    public SearchViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_search);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(v->{
            if (getContext() instanceof MainActivity){
                ((MainActivity)getContext()).startSearch(data);
            }
        });
    }

    @Override
    public void setData(Search data) {
        this.data = data;
        word.setText(data.getWord());
        switch (data.getType()){
            case Search.TYPE_BRAND:type.setText("品牌搜索");break;
            case Search.TYPE_LINE:type.setText("车系搜索");break;
            case Search.TYPE_TYPE:type.setText("车型搜索");break;
        }
    }
}
