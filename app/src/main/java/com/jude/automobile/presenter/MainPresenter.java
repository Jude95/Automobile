package com.jude.automobile.presenter;

import android.os.Bundle;

import com.jude.automobile.data.AccountModel;
import com.jude.automobile.ui.MainActivity;
import com.jude.beam.bijection.Presenter;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class MainPresenter extends Presenter<MainActivity> {

    @Override
    protected void onCreate(MainActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        AccountModel.getInstance().checkUpdate(getView());
    }
}
