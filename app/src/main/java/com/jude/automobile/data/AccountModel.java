package com.jude.automobile.data;

import android.content.Context;

import com.jude.automobile.data.server.HeaderInterceptors;
import com.jude.automobile.domain.Dir;
import com.jude.automobile.domain.entities.Account;
import com.jude.beam.model.AbsModel;
import com.jude.utils.JFileManager;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/**
 * Created by zhuchenxi on 16/1/21.
 */
public class AccountModel extends AbsModel{
    private static final String FILE_ACCOUNT = "account";

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
        return hasLogin()&&mAccountSubject.getValue().isActivity();
    }

    public Observable<Object> login(String name, String password){
        return Observable.just(createVirtualAccount())
                .doOnNext(account -> mAccountSubject.onNext(account))
                .flatMap(account -> Observable.just(null))
                .delay(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Object> register(String number,String password,String name,String code){
        return Observable.just(null).delay(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Object> modifyPassword(String number,String password,String code){
        return Observable.just(null).delay(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Object> checkAccount(String number){
        return Observable.just(null).delay(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
    }

    public void logout(){
        mAccountSubject.onNext(null);
    }

    public Account createVirtualAccount(){
        return new Account(true,(int)Math.random(),"Jude","17006695458","fsdafasdgradsgdfasdga");
    }
}
