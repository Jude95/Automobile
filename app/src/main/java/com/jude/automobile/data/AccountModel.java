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
    private Func1<Account,Boolean> mAccountFilter = account -> account!=null&&account.equals(mAccountSubject.getValue());

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        Observable.just((Account) JFileManager.getInstance().getFolder(Dir.Object).readObjectFromFile(FILE_ACCOUNT))
                .filter(mAccountFilter)
                .subscribe(mAccountSubject);
        //账号持久化
        mAccountSubject.subscribe(account -> {
            if (account==null) JFileManager.getInstance().getFolder(Dir.Object).deleteChild(FILE_ACCOUNT);
            else JFileManager.getInstance().getFolder(Dir.Object).writeObjectToFile(account,FILE_ACCOUNT);
        });
        //token设置
        mAccountSubject.filter(account->account==null).subscribe(account1 -> {
            HeaderInterceptors.TOKEN = account1.getToken();
            HeaderInterceptors.UID = account1.getId()+"";
        });
    }

    public boolean hasLogin(){
        return mAccountSubject.getValue()!=null;
    }

    public boolean isActivity(){
        return hasLogin()&&mAccountSubject.getValue().isActivity();
    }

    public Observable<Object> login(String name, String password){
        return Observable.just(null).delay(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
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
}
