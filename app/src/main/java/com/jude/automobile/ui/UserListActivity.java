package com.jude.automobile.ui;

import android.view.ViewGroup;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Account;
import com.jude.automobile.presenter.UserListPresenter;
import com.jude.automobile.ui.viewholder.UserViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by zhuchenxi on 16/1/27.
 */
@RequiresPresenter(UserListPresenter.class)
public class UserListActivity extends BeamListActivity<UserListPresenter,Account> {

    @Override
    public ListConfig getConfig() {
        return super.getConfig().setRefreshAble(true);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    public BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(parent);
    }
}
