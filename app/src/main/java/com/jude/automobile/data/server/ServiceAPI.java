package com.jude.automobile.data.server;


import com.jude.automobile.domain.body.Exist;
import com.jude.automobile.domain.body.Info;
import com.jude.automobile.domain.body.Token;
import com.jude.automobile.domain.entities.Account;
import com.jude.automobile.domain.entities.Assemble;
import com.jude.automobile.domain.entities.Brand;
import com.jude.automobile.domain.entities.ConstantParams;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.domain.entities.Vendor;

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
    String SERVER_ADDRESS = "http://123.56.230.6/2.0/";

//    ------------------------Account----------------------------------
    @POST("account/check_account_exist.php")
    @FormUrlEncoded
    Observable<Exist> checkAccountExist(@Field("account") String account);

    @POST("account/register.php")
    @FormUrlEncoded
    Observable<Info> register(
            @Field("account") String account,
            @Field("name") String name,
            @Field("password") String password,
            @Field("code") String code);

    @POST("account/login.php")
    @FormUrlEncoded
    Observable<Account> login(
            @Field("account") String account,
            @Field("password") String password);

    @POST("account/modify_password.php")
    @FormUrlEncoded
    Observable<Info> modifyPassword(
            @Field("account") String account,
            @Field("password") String password,
            @Field("code") String code);


    @GET("account/refresh_account.php")
    Observable<Account> refreshAccount();


//    ------------------------Manager----------------------------------


    @GET("manager/admin_user_list.php")
    Observable<List<Account>> getUserList();

    @POST("manager/admin_authorization.php")
    @FormUrlEncoded
    Observable<Info> authorization(
            @Field("userId") int userId);

//    ------------------------Common----------------------------------


    @GET("qiniu.php")
    Observable<Token> getQiniuToken();

    @GET("params.php")
    Observable<ConstantParams> refreshParams();


//    ------------------------Data----------------------------------


    @GET("data/brand/brand_list.php")
    Observable<List<Brand>> getBrandList();

    @POST("data/line/line_list.php")
    @FormUrlEncoded
    Observable<List<Line>> getLineList(
            @Field("id")int id
    );

    @POST("data/vendor/vendor_list.php")
    @FormUrlEncoded
    Observable<List<Vendor>> getVendorList(
            @Field("id")int id
    );

    @POST("data/type/type_list.php")
    @FormUrlEncoded
    Observable<List<Type>> getTypeList(
            @Field("id")int id
    );

    @POST("data/brand/brand_search.php")
    @FormUrlEncoded
    Observable<List<Brand>> searchBrand(
            @Field("word")String word
    );

    @POST("data/line/line_search.php")
    @FormUrlEncoded
    Observable<List<Line>> searchLine(
            @Field("word")String word
    );

    @POST("data/type/type_search.php")
    @FormUrlEncoded
    Observable<List<Type>> searchType(
            @Field("word")String word
    );


    @POST("data/brand/brand_add.php")
    @FormUrlEncoded
    Observable<Info> addBrand(
            @Field("id")int id,
            @Field("avatar")String avatar,
            @Field("name")String name,
            @Field("word")String word
    );

    @POST("data/line/line_add.php")
    @FormUrlEncoded
    Observable<Info> addLine(
            @Field("id")int id,
            @Field("vendor_id")int vendor_id,
            @Field("name")String name,
            @Field("word")String word
    );

    @POST("data/vendor/vendor_add.php")
    @FormUrlEncoded
    Observable<Info> addVendor(
            @Field("id")int id,
            @Field("line_id")int line_id,
            @Field("name")String name
    );

    @POST("data/type/type_add.php")
    @FormUrlEncoded
    Observable<Info> addType(
            @Field("id")int id,
            @Field("line_id")int line_id,
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

    @POST("data/type/type_detail.php")
    @FormUrlEncoded
    Observable<Type> getTypeDetail(
            @Field("id")int id
    );

    @POST("data/part/part_add.php")
    @FormUrlEncoded
    Observable<Info> addPart(
            @Field("id")int id,
            @Field("type")String type,
            @Field("brand")String brand,
            @Field("drawing_number")String drawing_number,
            @Field("avatar")String avatar,
            @Field("picture")String picture);

    @POST("data/part/part_assemble.php")
    @FormUrlEncoded
    Observable<Info> assemble(
            @Field("part_id")int part_id,
            @Field("type_id")int type_id,
            @Field("note")String note
    );

    @POST("data/part/part_list_type.php")
    @FormUrlEncoded
    Observable<List<Part>> getPartListByType(
            @Field("line")String type,
            @Field("brand")String brand
    );

    @POST("data/part/part_assemble_list.php")
    @FormUrlEncoded
    Observable<List<Assemble>> getAssembleListByType(
            @Field("id")int model_id
    );

    @POST("data/part/part_detail.php")
    @FormUrlEncoded
    Observable<Part> getPartDetail(
            @Field("id")int id
    );

    @POST("data/part/part_unassemble.php")
    @FormUrlEncoded
    Observable<Info> unAssemble(
            @Field("id")int id
    );

    @POST("data/type/type_delete.php")
    @FormUrlEncoded
    Observable<Info> deleteType(
            @Field("id")int id
    );

    @POST("data/line/line_delete.php")
    @FormUrlEncoded
    Observable<Info> deleteLine(
            @Field("id")int id
    );

    @POST("data/vendor/vendor_delete.php")
    @FormUrlEncoded
    Observable<Info> deleteVendor(
            @Field("id")int id
    );

    @POST("data/brand/brand_delete.php")
    @FormUrlEncoded
    Observable<Info> deleteBrand(
            @Field("id")int id
    );
}
