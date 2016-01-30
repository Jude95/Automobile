package com.jude.automobile.data;

import android.content.Context;

import com.jude.automobile.data.di.DaggerManagerModelComponent;
import com.jude.automobile.data.server.SchedulerTransform;
import com.jude.automobile.data.server.ServiceAPI;
import com.jude.automobile.domain.body.Info;
import com.jude.automobile.domain.entities.Account;
import com.jude.beam.model.AbsModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zhuchenxi on 16/1/27.
 */
public class ManagerModel extends AbsModel {
    public static ManagerModel getInstance() {
        return getInstance(ManagerModel.class);
    }

    @Inject
    ServiceAPI mServiceAPI;

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        DaggerManagerModelComponent.builder().build().inject(this);
    }

    public Observable<List<Account>> getUserList(){
        return mServiceAPI.getUserList().compose(new SchedulerTransform<>());
    }

    public Observable<Info> authorization(int userId){
        return mServiceAPI.authorization(userId).compose(new SchedulerTransform<>());
    }
}
