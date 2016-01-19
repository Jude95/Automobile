package com.jude.automobile.presenter;

import android.os.Bundle;

import com.jude.automobile.data.DataModel;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.ui.LineActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.utils.JUtils;

import java.util.ArrayList;

import rx.functions.Action1;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class LinePresenter extends BeamListActivityPresenter<LineActivity,Type> {

    int id;
    @Override
    protected void onCreate(LineActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        id = getView().getIntent().getIntExtra("id",0);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        DataModel.getInstance().getTypeByLine(id).subscribe(new Action1<ArrayList<Type>>() {
            @Override
            public void call(ArrayList<Type> types) {
                JUtils.Log("C"+types.size());
                getAdapter().addAll(types);
            }
        });
    }
}
