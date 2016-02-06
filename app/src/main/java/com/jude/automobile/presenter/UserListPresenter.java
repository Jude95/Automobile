package com.jude.automobile.presenter;

import android.os.Bundle;

import com.jude.automobile.data.ManagerModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Account;
import com.jude.automobile.ui.UserListActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by zhuchenxi on 16/1/27.
 */
public class UserListPresenter extends BeamListActivityPresenter<UserListActivity,Account> {

    @Override
    protected void onCreate(UserListActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ManagerModel.getInstance().getUserList()
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                .unsafeSubscribe(getRefreshSubscriber());
    }
}
