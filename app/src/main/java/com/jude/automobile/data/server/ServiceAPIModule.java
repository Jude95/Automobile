package com.jude.automobile.data.server;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Mr.Jude on 2015/11/18.
 */
@Module
public class ServiceAPIModule {
    @Singleton
    @Provides
    ServiceAPI provideServiceAPI() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptors())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceAPI.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(ServiceAPI.class);
    }
}
