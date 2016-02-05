package com.jude.automobile.data.server;


import com.jude.automobile.domain.body.Exist;
import com.jude.automobile.domain.body.Info;
import com.jude.automobile.domain.body.Token;
import com.jude.automobile.domain.entities.Account;
import com.jude.automobile.domain.entities.ConstantParams;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.domain.entities.Type;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

    @GET("admin_user_list.php")
    Observable<List<Account>> getUserList();

    @POST("admin_authorization.php")
    @FormUrlEncoded
    Observable<Info> authorization(
            @Field("userId") int userId);

    @GET("refresh_account.php")
    Observable<Account> refreshAccount();

    @GET("line_list.php")
    Observable<List<Line>> getLineList();

    @POST("type_list.php")
    @FormUrlEncoded
    Observable<List<Type>> getTypeList(
            @Field("id")int id
    );

    @POST("model_list.php")
    @FormUrlEncoded
    Observable<List<Model>> getModelList(
            @Field("id")int id
    );

    @POST("search_line.php")
    @FormUrlEncoded
    Observable<List<Line>> searchLine(
            @Field("word")String word
    );

    @POST("search_type.php")
    @FormUrlEncoded
    Observable<List<Type>> searchType(
            @Field("word")String word
    );

    @POST("search_model.php")
    @FormUrlEncoded
    Observable<List<Model>> searchModel(
            @Field("word")String word
    );

    @GET("qiniu.php")
    Observable<Token> getQiniuToken();

    @POST("line_add.php")
    @FormUrlEncoded
    Observable<Info> addLine(
            @Field("id")int id,
            @Field("avatar")String avatar,
            @Field("name")String name,
            @Field("word")String word
    );

    @POST("type_add.php")
    @FormUrlEncoded
    Observable<Info> addType(
            @Field("id")int id,
            @Field("line_id")int line_id,
            @Field("name")String name,
            @Field("word")String word
    );

    @POST("model_add.php")
    @FormUrlEncoded
    Observable<Info> addModel(
            @Field("id")int id,
            @Field("type_id")int line_id,
            @Field("name")String name,
            @Field("word")String word,
            @Field("power")String power,
            @Field("displacement")String displacement,
            @Field("cylinders")String cylinders,
            @Field("valve")String valve,
            @Field("structure")String structure,
            @Field("drive")String drive,
            @Field("fuel")String fuel,
            @Field("fuel_feed")String fuel_feed,
            @Field("tecdoc")String tecdoc,
            @Field("engine_code")String engine_code,
            @Field("engine")String engine,
            @Field("time")String time,
            @Field("displacement_tech")String displacement_tech);

    @GET("params.php")
    Observable<ConstantParams> refreshParams();

    @POST("model_detail.php")
    @FormUrlEncoded
    Observable<Model> getModelDetail(
            @Field("id")int id
    );

    @POST("part_add.php")
    @FormUrlEncoded
    Observable<Info> addPart(
            @Field("id")int id,
            @Field("type")String type,
            @Field("brand")String brand,
            @Field("drawing_number")String drawing_number,
            @Field("avatar")String avatar,
            @Field("picture")String picture);

    @POST("assemble.php")
    @FormUrlEncoded
    Observable<Info> assemble(
            @Field("part_id")int part_id,
            @Field("model_id")int model_id,
            @Field("note")String note
    );

    @POST("part_list_type.php")
    @FormUrlEncoded
    Observable<List<Part>> getPartListByType(
            @Field("type")String type
    );

    @POST("part_list_model.php")
    @FormUrlEncoded
    Observable<List<Part>> getPartListByModel(
            @Field("id")int model_id
    );

    @POST("part_detail.php")
    @FormUrlEncoded
    Observable<Part> getPartDetail(
            @Field("id")int id
    );

    @POST("unassemble.php")
    @FormUrlEncoded
    Observable<Info> unAssemble(
            @Field("id")int id
    );

    @POST("model_delete.php")
    @FormUrlEncoded
    Observable<Info> deleteModel(
            @Field("id")int id
    );

    @POST("type_delete.php")
    @FormUrlEncoded
    Observable<Info> deleteType(
            @Field("id")int id
    );

    @POST("line_delete.php")
    @FormUrlEncoded
    Observable<Info> deleteLine(
            @Field("id")int id
    );
}
