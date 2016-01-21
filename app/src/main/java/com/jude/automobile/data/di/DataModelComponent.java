package com.jude.automobile.data.di;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ServiceAPIModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhuchenxi on 16/1/21.
 */

@Singleton
@Component(modules = {ServiceAPIModule.class})
public interface DataModelComponent {
    void inject(DataModel model);
}
