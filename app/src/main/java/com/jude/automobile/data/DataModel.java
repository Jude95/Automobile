package com.jude.automobile.data;

import android.content.Context;

import com.jude.automobile.data.server.DaggerServiceModelComponent;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.data.server.SchedulerTransform;
import com.jude.automobile.data.server.ServiceAPI;
import com.jude.automobile.domain.Dir;
import com.jude.automobile.domain.body.Info;
import com.jude.automobile.domain.entities.Assemble;
import com.jude.automobile.domain.entities.Brand;
import com.jude.automobile.domain.entities.ConstantParams;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.domain.entities.Search;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.domain.entities.Vendor;
import com.jude.beam.model.AbsModel;
import com.jude.utils.JFileManager;

import java.security.InvalidParameterException;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class DataModel extends AbsModel {
    @Inject
    ServiceAPI mServiceAPI;

    private final static String FILE_PARAMS = "params";

    private ConstantParams mParams;

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        DaggerServiceModelComponent.builder().build().inject(this);
        mParams = (ConstantParams) JFileManager.getInstance().getFolder(Dir.Object).readObjectFromFile(FILE_PARAMS);
    }

    public static DataModel getInstance() {
        return getInstance(DataModel.class);
    }

    public Observable<List> dispatchSearch(Search search){
        switch (search.getType()){
            case Search.TYPE_BRAND:
                return searchBrand(search.getWord()).flatMap((Func1<List<Brand>, Observable<List>>) Observable::just);
            case Search.TYPE_LINE:
                return searchLine(search.getWord()).flatMap((Func1<List<Line>, Observable<List>>) Observable::just);
            case Search.TYPE_TYPE:
                return searchType(search.getWord()).flatMap((Func1<List<Type>, Observable<List>>) Observable::just);
                default:
                    throw new InvalidParameterException();
        }
    }

    public void refreshParams(){
        mServiceAPI.refreshParams()
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.NONE))
                .subscribeOn(Schedulers.io())
                .subscribe(params -> {
                    mParams = params;
                    JFileManager.getInstance().getFolder(Dir.Object).writeObjectToFile(params,FILE_PARAMS);
                });
    }

    public ConstantParams getConstantParams(){
        return mParams.clone();
    }

    public Observable<List<Brand>> searchBrand(String name){
        SearchHistoryModel.getInstance().addSearch(new Search(Search.TYPE_BRAND,name));
        return mServiceAPI.searchBrand(name).compose(new SchedulerTransform<>());
    }

    public Observable<List<Line>> searchLine(String name){
        SearchHistoryModel.getInstance().addSearch(new Search(Search.TYPE_LINE,name));
        return mServiceAPI.searchLine(name).compose(new SchedulerTransform<>());
    }

    public Observable<List<Type>> searchType(String name){
        SearchHistoryModel.getInstance().addSearch(new Search(Search.TYPE_TYPE,name));
        return mServiceAPI.searchType(name).compose(new SchedulerTransform<>());
    }

    public Observable<List<Brand>> getAllBrand(){
        return mServiceAPI.getBrandList();
    }

    public Observable<List<Vendor>> getVendorByBrand(int brandId){
        return mServiceAPI.getVendorList(brandId).compose(new SchedulerTransform<>());
    }

    public Observable<List<Line>> getLineByVendor(int lineId){
        return mServiceAPI.getLineList(lineId).compose(new SchedulerTransform<>());
    }

    public Observable<List<Type>> getTypeByLine(int typeId){
        return mServiceAPI.getTypeList(typeId).compose(new SchedulerTransform<>());
    }

    public Observable<Type> getTypeDetail(int modelId){
        return mServiceAPI.getTypeDetail(modelId).compose(new SchedulerTransform<>());
    }

    public Observable<Info> addBrand(int id, String name, String avatar, String word){
        return mServiceAPI.addBrand(id,avatar,name,word).compose(new SchedulerTransform<>());
    }

    public Observable<Info> addLine(int id, int  lineId, String name, String word){
        return mServiceAPI.addLine(id,lineId,name,word).compose(new SchedulerTransform<>());
    }


    public Observable<Info> addVendor(Vendor vendor){
        return mServiceAPI.addVendor(vendor.getId(),vendor.getBrandId(),vendor.getName()).compose(new SchedulerTransform<>());
    }

    public Observable<Info> addType(Type type){
        return mServiceAPI.addType(
                type.getId(),
                type.getLineId(),
                type.getName(),
                type.getWord(),
                type.getPower(),
                type.getDisplacement(),
                type.getCylinders(),
                type.getValve(),
                type.getStructure(),
                type.getDrive(),
                type.getFuel(),
                type.getFuelFeed(),
                type.getTecdoc(),
                type.getEngineCode(),
                type.getEngine(),
                type.getTime(),
                type.getDisplacementTech()
                )
                .compose(new SchedulerTransform<>());
    }

    public Observable<Info> addPart(Part part){
        String image = "";
        if (part.getPicture().size()>0){
            for (String s : part.getPicture()) {
                image+=s+",";
            }
            image = image.substring(0,image.length()-1);
        }
        return mServiceAPI.addPart(part.getId(),part.getType(),part.getBrand(),part.getDrawingNumber(),part.getAvatar(),part.getNote(),image).compose(new SchedulerTransform<>());
    }



    public Observable<Info> assemble(int part,int model,String note){
        return mServiceAPI.assemble(part,model,note).compose(new SchedulerTransform<>());
    }

    public Observable<Info> unAssemble(int assembleId){
        return mServiceAPI.unAssemble(assembleId).compose(new SchedulerTransform<>());
    }

    public Observable<List<Part>> getPartByType(String type,String drawing_number){
        return mServiceAPI.getPartListByType(type,drawing_number).compose(new SchedulerTransform<>());
    }

    public Observable<List<Assemble>> getAssembleByType(int model){
        return mServiceAPI.getAssembleListByType(model).compose(new SchedulerTransform<>());
    }

    public Observable<Part> getPartDetail(int id){
        return mServiceAPI.getPartDetail(id).compose(new SchedulerTransform<>());
    }

    public Observable<Info> deleteType(int modelId){
        return mServiceAPI.deleteType(modelId).compose(new SchedulerTransform<>());
    }
    public Observable<Info> deleteVendor(int vendorId){
        return mServiceAPI.deleteVendor(vendorId).compose(new SchedulerTransform<>());
    }
    public Observable<Info> deleteLine(int typeId){
        return mServiceAPI.deleteLine(typeId).compose(new SchedulerTransform<>());
    }
    public Observable<Info> deleteBrand(int lineId){
        return mServiceAPI.deleteBrand(lineId).compose(new SchedulerTransform<>());
    }

}
