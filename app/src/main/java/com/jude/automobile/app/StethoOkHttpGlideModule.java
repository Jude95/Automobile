package com.jude.automobile.app;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.jude.automobile.data.server.DaggerServiceModelComponent;

import java.io.InputStream;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

public class StethoOkHttpGlideModule  implements GlideModule {
    @Inject
    OkHttpClient client;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) { }

    @Override
    public void registerComponents(Context context, Glide glide) {
        DaggerServiceModelComponent.builder().build().inject(this);
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
    }
}