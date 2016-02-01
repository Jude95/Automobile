package com.jude.automobile.data;

import android.content.Context;

import com.jude.automobile.data.di.DaggerDataModelComponent;
import com.jude.automobile.data.server.SchedulerTransform;
import com.jude.automobile.data.server.ServiceAPI;
import com.jude.automobile.domain.body.Info;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.domain.entities.Search;
import com.jude.automobile.domain.entities.Type;
import com.jude.beam.model.AbsModel;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class DataModel extends AbsModel {
    @Inject
    ServiceAPI mServiceAPI;

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        DaggerDataModelComponent.builder().build().inject(this);
    }

    public static DataModel getInstance() {
        return getInstance(DataModel.class);
    }

    public Observable<List> dispatchSearch(Search search){
        switch (search.getType()){
            case Search.TYPE_LINE:
                return searchLine(search.getWord()).flatMap((Func1<List<Line>, Observable<List>>) Observable::just);
            case Search.TYPE_TYPE:
                return searchType(search.getWord()).flatMap((Func1<List<Type>, Observable<List>>) Observable::just);
            case Search.TYPE_Model:
                return searchModel(search.getWord()).flatMap((Func1<List<Model>, Observable<List>>) Observable::just);
                default:
                    throw new InvalidParameterException();
        }
    }

    public Observable<List<Line>> searchLine(String name){
        SearchHistoryModel.getInstance().addSearch(new Search(Search.TYPE_LINE,name));
        return mServiceAPI.searchLine(name).compose(new SchedulerTransform<>());
    }

    public Observable<List<Type>> searchType(String name){
        SearchHistoryModel.getInstance().addSearch(new Search(Search.TYPE_TYPE,name));
        return mServiceAPI.searchType(name).compose(new SchedulerTransform<>());
    }

    public Observable<List<Model>> searchModel(String name){
        SearchHistoryModel.getInstance().addSearch(new Search(Search.TYPE_Model,name));
        return mServiceAPI.searchModel(name).compose(new SchedulerTransform<>());
    }

    public Observable<List<Line>> getAllLine(){
        return mServiceAPI.getLineList().compose(new SchedulerTransform<>());
    }

    public Observable<List<Type>> getTypeByLine(int lineId){
        return mServiceAPI.getTypeList(lineId).compose(new SchedulerTransform<>());
    }

    public Observable<List<Model>> getModelByType(int typeId){
        return mServiceAPI.getModelList(typeId).compose(new SchedulerTransform<>());
    }

    public Observable<ArrayList<Part>> getPartByModel(int modelId){
        return Observable.just(createVirtualPart()).delay(1,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Model> getModelById(int modelId){
        return Observable.just(createVirtualSingleModel()).delay(1,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Info> addLine(int id,String name,String avatar,String word){
        return mServiceAPI.addLine(id,avatar,name,word).compose(new SchedulerTransform<>());
    }

    public Observable<Info> addType(int id,int  lineId,String name,String word){
        return mServiceAPI.addType(id,lineId,name,word).compose(new SchedulerTransform<>());
    }

    public Observable<Info> addModel(Model model){
        return mServiceAPI.addModel(
                model.getId(),
                model.getTypeId(),
                model.getName(),
                model.getWord(),
                model.getPower(),
                model.getDisplacement(),
                model.getCylinders(),
                model.getStructure(),
                model.getDrive(),
                model.getFuel(),
                model.getFuelFeed(),
                model.getTecdoc(),
                model.getEngineCode(),
                model.getTime(),
                model.getDisplacement(),
                model.getDisplacementTech()
                );
    }
//    public ArrayList<Line> createVirtualLines(){
//        ArrayList<Line> arrayList = new ArrayList();
//        arrayList.add(new Line(1,"http://i2.hdslb.com/52_52/user/61175/6117592/myface.jpg","奥迪"));
//        arrayList.add(new Line(1,"http://i1.hdslb.com/52_52/user/6738/673856/myface.jpg","奔驰"));
//        arrayList.add(new Line(1,"http://i1.hdslb.com/account/face/1467772/e1afaf4a/myface.png","别克"));
//        arrayList.add(new Line(1,"http://i0.hdslb.com/52_52/user/18494/1849483/myface.jpg","宝马"));
//        arrayList.add(new Line(1,"http://i0.hdslb.com/52_52/account/face/4613528/303f4f5a/myface.png","丰田"));
//        arrayList.add(new Line(1,"http://i0.hdslb.com/52_52/account/face/611203/76c02248/myface.png","凯迪拉克"));
//        arrayList.add(new Line(1,"http://i2.hdslb.com/52_52/user/46230/4623018/myface.jpg","阿斯顿马丁"));
//        arrayList.add(new Line(1,"http://i2.hdslb.com/52_52/user/66723/6672394/myface.jpg","法拉利"));
//        arrayList.add(new Line(1,"http://i1.hdslb.com/user/3039/303946/myface.jpg","沃尔沃"));
//        arrayList.add(new Line(1,"http://i2.hdslb.com/account/face/9034989/aabbc52a/myface.png","玛莎拉蒂"));
//        arrayList.add(new Line(1,"http://i0.hdslb.com/account/face/1557783/8733bd7b/myface.png","宾利"));
//        arrayList.add(new Line(1,"http://i2.hdslb.com/user/3716/371679/myface.jpg","世爵"));
//        arrayList.add(new Line(1,"http://i1.hdslb.com/account/face/9045165/4b11d894/myface.png","迈凯轮"));
//        return  arrayList;
//    }

    public ArrayList<Type> createVirtualType(){
        ArrayList<Type> arrayList = new ArrayList<>();
        arrayList.add(new Type(1,2,"长安","悦翔V3"));
        arrayList.add(new Type(1,2,"长安","悦翔V5"));
        arrayList.add(new Type(1,2,"长安","悦翔V8"));
        arrayList.add(new Type(1,2,"奥迪","奥迪X5"));
        arrayList.add(new Type(1,2,"奥迪","奥迪X6"));
        arrayList.add(new Type(1,2,"奥迪","奥迪X7"));
        return arrayList;
    }

    public ArrayList<Model> createVirtualModel(){
        ArrayList<Model> arrayList = new ArrayList<>();
        arrayList.add(new Model("4","1206 毫升 / 1.2 升 ","1.2","后驱动","点燃式发动机","HH412Q/P-B","汽油","进气管内喷射/汽化器",1,"成功汽车 K1 1,2","63 千瓦 / 86 马力","载货平台/底盘","2333","2013.8 - 今",2,"长安","4"));
        arrayList.add(new Model("4","1206 毫升 / 1.2 升 ","1.2","后驱动","点燃式发动机","HH412Q/P-B","汽油","进气管内喷射/汽化器",1,"成功汽车 K1 1,2","63 千瓦 / 86 马力","载货平台/底盘","2333","2013.8 - 今",2,"长安","4"));
        arrayList.add(new Model("4","1206 毫升 / 1.2 升 ","1.2","后驱动","点燃式发动机","HH412Q/P-B","汽油","进气管内喷射/汽化器",1,"成功汽车 K1 1,2","63 千瓦 / 86 马力","载货平台/底盘","2333","2013.8 - 今",2,"长安","4"));
        arrayList.add(new Model("4","1206 毫升 / 1.2 升 ","1.2","后驱动","点燃式发动机","HH412Q/P-B","汽油","进气管内喷射/汽化器",1,"成功汽车 K1 1,2","63 千瓦 / 86 马力","载货平台/底盘","2333","2013.8 - 今",2,"长安","4"));
        arrayList.add(new Model("4","1206 毫升 / 1.2 升 ","1.2","后驱动","点燃式发动机","HH412Q/P-B","汽油","进气管内喷射/汽化器",1,"成功汽车 K1 1,2","63 千瓦 / 86 马力","载货平台/底盘","2333","2013.8 - 今",2,"长安","4"));
        arrayList.add(new Model("4","1206 毫升 / 1.2 升 ","1.2","后驱动","点燃式发动机","HH412Q/P-B","汽油","进气管内喷射/汽化器",1,"成功汽车 K1 1,2","63 千瓦 / 86 马力","载货平台/底盘","2333","2013.8 - 今",2,"长安","4"));
        arrayList.add(new Model("4","1206 毫升 / 1.2 升 ","1.2","后驱动","点燃式发动机","HH412Q/P-B","汽油","进气管内喷射/汽化器",1,"成功汽车 K1 1,2","63 千瓦 / 86 马力","载货平台/底盘","2333","2013.8 - 今",2,"长安","4"));
        return arrayList;
    }

    public Model createVirtualSingleModel(){
        return new Model("4","1206 毫升 / 1.2 升 ","1.2","后驱动","点燃式发动机","HH412Q/P-B","汽油","进气管内喷射/汽化器",1,"成功汽车 K1 1,2","63 千瓦 / 86 马力","载货平台/底盘","2333","2013.8 - 今",2,"长安","4");
    }

    public ArrayList<Part> createVirtualPart(){
        ArrayList<Part> arrayList = new ArrayList<>();
        arrayList.add(new Part("http://i2.hdslb.com/user/35900/3590066/myface.gif","铁牛","2333333333",1, Arrays.asList(new String[]{
                "http://i0.hdslb.com/u_user/7eeba663e9ca1823492d29cc25bef3f4.jpg",
                "http://i1.hdslb.com/u_user/59accbdc10483e33af559f7c3cea3e6d.jpg"
        }),"发动鸡","6s真男人"));
        arrayList.add(new Part("http://i2.hdslb.com/user/35900/3590066/myface.gif","铁牛","2333333333",1, Arrays.asList(new String[]{
                "http://i0.hdslb.com/u_user/7eeba663e9ca1823492d29cc25bef3f4.jpg",
                "http://i1.hdslb.com/u_user/59accbdc10483e33af559f7c3cea3e6d.jpg"
        }),"起动鸡","大码力"));
        arrayList.add(new Part("http://i2.hdslb.com/user/35900/3590066/myface.gif","铁牛","2333333333",1, Arrays.asList(new String[]{
                "http://i0.hdslb.com/u_user/7eeba663e9ca1823492d29cc25bef3f4.jpg",
                "http://i1.hdslb.com/u_user/59accbdc10483e33af559f7c3cea3e6d.jpg"
        }),"涡轮增压鸡","10G加速度"));
        arrayList.add(new Part("http://i2.hdslb.com/user/35900/3590066/myface.gif","铁牛","2333333333",1, Arrays.asList(new String[]{
                "http://i0.hdslb.com/u_user/7eeba663e9ca1823492d29cc25bef3f4.jpg",
                "http://i1.hdslb.com/u_user/59accbdc10483e33af559f7c3cea3e6d.jpg"
        }),"减震鸡","哦～"));
        arrayList.add(new Part("http://i2.hdslb.com/user/35900/3590066/myface.gif","铁牛","2333333333",1, Arrays.asList(new String[]{
                "http://i0.hdslb.com/u_user/7eeba663e9ca1823492d29cc25bef3f4.jpg",
                "http://i1.hdslb.com/u_user/59accbdc10483e33af559f7c3cea3e6d.jpg"
        }),"肯打鸡","纯洁的鸡"));
        return arrayList;
    }
}
