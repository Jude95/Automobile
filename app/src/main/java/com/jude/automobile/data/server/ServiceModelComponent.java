package com.jude.automobile.data.server;

import com.jude.automobile.data.AccountModel;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.data.ManagerModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhuchenxi on 16/1/25.
 */
@Singleton
@Component(modules = {ServiceAPIModule.class})
public interface ServiceModelComponent {
    void inject(AccountModel model);
    void inject(DataModel model);
    void inject(ImageModel model);
    void inject(ManagerModel model);
}
