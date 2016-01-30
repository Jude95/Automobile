package com.jude.automobile.presenter;

import android.os.Bundle;

import com.jude.automobile.data.AccountModel;
import com.jude.automobile.domain.entities.Account;
import com.jude.automobile.ui.TimeActivity;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;

/**
 * Created by zhuchenxi on 16/1/27.
 */
public class TimePresenter extends BeamDataActivityPresenter<TimeActivity,Account> {

    @Override
    protected void onCreate(TimeActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        AccountModel.getInstance().getAccountSubject().unsafeSubscribe(getDataSubscriber());
    }

    @Override
    protected void onCreateView(TimeActivity view) {
        super.onCreateView(view);
        AccountModel.getInstance().refreshAccount();
    }
}
