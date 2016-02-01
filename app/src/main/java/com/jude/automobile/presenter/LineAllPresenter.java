package com.jude.automobile.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.ui.LineAllActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.utils.JUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import rx.Observable;

/**
 * Created by zhuchenxi on 16/1/20.
 */
public class LineAllPresenter extends BeamListActivityPresenter<LineAllActivity,Object> {
    HashMap<Character,Integer> positionMap = new HashMap<>();

    @Override
    protected void onCreate(LineAllActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getAllLine().flatMap(lines -> {
            ArrayList<Object> arrayList = new ArrayList<>();
            Collections.sort(lines, (lhs, rhs) -> PinyinHelper.getShortPinyin(lhs.getName()).toLowerCase().compareTo(PinyinHelper.getShortPinyin(rhs.getName()).toLowerCase()));
            char temp = ' ';
            for (Line line : lines) {
                if (!TextUtils.isEmpty(line.getName())){
                    char cur = PinyinHelper.getShortPinyin(line.getName()).charAt(0);
                    if (cur != temp){
                        temp = cur;
                        positionMap.put(Character.toUpperCase(temp),arrayList.size());
                        arrayList.add(Character.toUpperCase(temp));
                    }
                    arrayList.add(line);
                }
            }
            return Observable.just(arrayList);
        })
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
