package com.jude.automobile.data.di;

import com.jude.automobile.data.ManagerModel;
import com.jude.automobile.data.server.ServiceAPIModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhuchenxi on 16/1/25.
 */
@Singleton
@Component(modules = {ServiceAPIModule.class})
public interface ManagerModelComponent {
    void inject(ManagerModel model);
}
