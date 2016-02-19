package com.jude.automobile.data;

import android.content.Context;

import com.jude.automobile.data.server.DaggerServiceModelComponent;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.data.server.HeaderInterceptors;
import com.jude.automobile.data.server.SchedulerTransform;
import com.jude.automobile.data.server.ServiceAPI;
import com.jude.automobile.domain.Dir;
import com.jude.automobile.domain.body.Exist;
import com.jude.automobile.domain.body.Info;
import com.jude.automobile.domain.entities.Account;
import com.jude.beam.model.AbsModel;
import com.jude.utils.JFileManager;
import com.jude.utils.JUtils;

import javax.inject.Inject;

import cn.com.caoyue.util.time.Time;
import rx.Observable;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/**
 * Created by zhuchenxi on 16/1/21.
 */
public class AccountModel extends AbsModel{
    private static final String FILE_ACCOUNT = "account";
    @Inject
    ServiceAPI mServiceAPI;

    public static AccountModel getInstance() {
        return getInstance(AccountModel.class);
    }


    private BehaviorSubject<Account> mAccountSubject = BehaviorSubject.create();
    private Func1<Account,Boolean> mAccountFilter = account -> {
        return !(account!=null&&account.equals(mAccountSubject.getValue()));
    };

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        DaggerServiceModelComponent.builder().build().inject(this);
        //账号持久化
        mAccountSubject.subscribe(account -> {
            if (account==null) JFileManager.getInstance().getFolder(Dir.Object).deleteChild(FILE_ACCOUNT);
            else JFileManager.getInstance().getFolder(Dir.Object).writeObjectToFile(account,FILE_ACCOUNT);
        });
        //token设置
        mAccountSubject.subscribe(account1 -> {
            if (account1!=null) {
                HeaderInterceptors.TOKEN = account1.getToken();
                HeaderInterceptors.UID = account1.getId() + "";
            }else {
                HeaderInterceptors.TOKEN = "";
                HeaderInterceptors.UID = "";
            }
            JUtils.Log("Set Token:"+HeaderInterceptors.TOKEN);
        });
        //初始化账户
        Observable.just((Account) JFileManager.getInstance().getFolder(Dir.Object).readObjectFromFile(FILE_ACCOUNT))
                .doOnNext(account -> mAccountSubject.onNext(account))
                .subscribe();
    }

    public BehaviorSubject<Account> getAccountSubject(){
        return mAccountSubject;
    }

    public boolean hasLogin(){
        return mAccountSubject.getValue()!=null;
    }

    public boolean isActivity(){
        if (hasLogin()&&getAccountSubject().getValue().getServiceBegin()>0){
            return new Time(getAccountSubject().getValue().getServiceBegin()).add(Time.Field.year,1).getTimeStamp()>System.currentTimeMillis()/1000;
        }else {
            return false;
        }
    }

    public boolean isEditor(){
        return hasLogin()&&getAccountSubject().getValue().getManager()>=2;
    }
    public boolean isManager(){
        return hasLogin()&&getAccountSubject().getValue().getManager()>=3;
    }

    public Observable<Account> login(String account, String password){
        return mServiceAPI.login(account,password)
                .compose(new SchedulerTransform<>())
                .doOnNext(account1 -> mAccountSubject.onNext(account1));
    }

    public Observable<Info> register(String number, String password, String name, String code){
        return mServiceAPI.register(number, name,password, code).compose(new SchedulerTransform<>());
    }

    public Observable<Object> modifyPassword(String number,String password,String code){
        return mServiceAPI.modifyPassword(number, password, code).compose(new SchedulerTransform<>());
    }

    public Observable<Boolean> checkAccount(String number){
        return mServiceAPI.checkAccountExist(number).compose(new SchedulerTransform<>()).map(Exist::isExist);
    }

    public void refreshAccount(){
        mServiceAPI.refreshAccount()
                .compose(new SchedulerTransform<>())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.NONE))
                .filter(mAccountFilter)
                .subscribe(account -> mAccountSubject.onNext(account));
    }

    public void logout(){
        mAccountSubject.onNext(null);
    }

}
