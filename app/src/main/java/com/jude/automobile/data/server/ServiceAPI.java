package com.jude.automobile.data.server;


import com.jude.automobile.domain.body.Exist;
import com.jude.automobile.domain.body.Info;
import com.jude.automobile.domain.entities.Account;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Mr.Jude on 2015/11/18.
 */
public interface ServiceAPI {
    String SERVER_ADDRESS = "http://123.56.230.6/";

    @POST("check_account_exist.php")
    @FormUrlEncoded
    Observable<Exist> checkAccountExist(@Field("account") String account);

    @POST("register.php")
    @FormUrlEncoded
    Observable<Info> register(
            @Field("account") String account,
            @Field("name") String name,
            @Field("password") String password,
            @Field("code") String code);

    @POST("login.php")
    @FormUrlEncoded
    Observable<Account> login(
            @Field("account") String account,
            @Field("password") String password);

    @POST("modify_password.php")
    @FormUrlEncoded
    Observable<Info> modifyPassword(
            @Field("account") String account,
            @Field("password") String password,
            @Field("code") String code);
}
