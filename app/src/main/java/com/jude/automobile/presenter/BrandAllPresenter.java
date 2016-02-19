package com.jude.automobile.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.data.server.SchedulerTransform;
import com.jude.automobile.domain.entities.Brand;
import com.jude.automobile.ui.BrandAllActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import rx.Observable;

/**
 * Created by zhuchenxi on 16/1/20.
 */
public class BrandAllPresenter extends BeamListActivityPresenter<BrandAllActivity,Object> {
    HashMap<Character,Integer> positionMap = new HashMap<>();

    @Override
    protected void onCreate(BrandAllActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getAllBrand().flatMap(lines -> {
            ArrayList<Object> arrayList = new ArrayList<>();
            Collections.sort(lines, (lhs, rhs) -> PinyinHelper.getShortPinyin(lhs.getName()).toLowerCase().compareTo(PinyinHelper.getShortPinyin(rhs.getName()).toLowerCase()));
            char temp = ' ';
            for (Brand brand : lines) {
                if (!TextUtils.isEmpty(brand.getName())){
                    char cur = Character.toUpperCase(PinyinHelper.getShortPinyin(brand.getName()).charAt(0));
                    if (cur != temp){
                        temp = cur;
                        positionMap.put(temp,arrayList.size());
                        arrayList.add(temp);
                    }
                    arrayList.add(brand);
                }
            }
            return Observable.just(arrayList);
        })
                .compose(new SchedulerTransform<>())
                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
        .unsafeSubscribe(getRefreshSubscriber());
    }

    public int getPositionByChar(char number){
        if (positionMap.size()>0){
            char cur = number;
            while (cur<='Z'){
                if (positionMap.containsKey(cur)){
                    return positionMap.get(cur);
                }
                cur+=1;
            }
            cur = number;
            while (cur>='A'){
                if (positionMap.containsKey(cur)){
                    return positionMap.get(cur);
                }
                cur-=1;
            }
        }
        return -1;
    }
}
