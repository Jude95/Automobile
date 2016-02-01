package com.jude.automobile.data.di;

import com.jude.automobile.data.ImageModel;
import com.jude.automobile.data.server.ServiceAPIModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhuchenxi on 16/1/31.
 */
@Singleton
@Component(modules = {ServiceAPIModule.class})
public interface ImageModelComponent {
    void inject(ImageModel model);
}
