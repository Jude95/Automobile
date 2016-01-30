package com.jude.automobile.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.clans.fab.FloatingActionButton;
import com.jude.automobile.R;
import com.jude.automobile.presenter.LineAllPresenter;
import com.jude.automobile.ui.viewholder.LineFullDeviderViewHolder;
import com.jude.automobile.utils.LinearLayoutManagerWithSmoothScroller;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.exgridview.ExGridView;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by zhuchenxi on 16/1/20.
 */
@RequiresPresenter(LineAllPresenter.class)
public class LineAllActivity extends BeamListActivity<LineAllPresenter, Object> {


    @Bind(R.id.navigation)
    FloatingActionButton navigation;

    private MaterialDialog navigationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getListView().setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this));
        navigation.setOnClickListener(v->createNavigationDialog());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_line_all;
    }


    public void createNavigationDialog() {
        navigationDialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_navigation, false)
                .show();
        View view = navigationDialog.getCustomView();
        ExGridView gridView = (ExGridView) view.findViewById(R.id.grid);
        gridView.setAdapter(new NumberAdapter());
    }

    public void scrollToPosition(char number){
        if (navigationDialog!=null)
            navigationDialog.dismiss();
        int position = getPresenter().getPositionByChar(number);
        if (position>=0) getListView().getRecyclerView().smoothScrollToPosition(position);
    }

    private class NumberAdapter extends BaseAdapter {
        char[] chars = {
                'A', 'B', 'C', 'D', 'E', 'F', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        @Override
        public int getCount() {
            return 26;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(LineAllActivity.this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setText(chars[position]+"");
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            textView.setOnClickListener(v->{
                scrollToPosition(chars[position]);
            });
            return textView;
        }
    }


    @Override
    public int getViewType(int position) {
        Object o = getPresenter().getAdapter().getItem(position);
        if (o instanceof Character) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new CharViewHolder(parent);
            case 1:
                return new LineFullDeviderViewHolder(parent);
        }
        return null;
    }

    private static class CharViewHolder extends BaseViewHolder<Character> {

        TextView number;

        public CharViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_char);
            number = $(R.id.number);
        }

        @Override
        public void setData(Character data) {
            number.setText(data.toString());
        }
    }


}
