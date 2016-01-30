package com.jude.automobile.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jude.automobile.R;
import com.jude.automobile.domain.entities.Account;
import com.jude.automobile.presenter.TimePresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.jude.utils.JUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.caoyue.util.time.Time;

/**
 * Created by zhuchenxi on 16/1/27.
 */
@RequiresPresenter(TimePresenter.class)
public class TimeActivity extends BeamDataActivity<TimePresenter,Account> {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.begin_time)
    TextView beginTime;
    @Bind(R.id.end_time)
    TextView endTime;

    Time time_begin;
    Time time_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(Account data) {
        super.setData(data);
        if (data.getServiceBegin()>0){
            time_begin = new Time(data.getServiceBegin());
            time_end = new Time(time_begin).add(Time.Field.year,1);

            Time.DeltaTime delta = time_end.minus(time_begin);
            int last = delta.getDay();
            if (last>0) {
                time.setText("剩余" + last + "天");
            }else {
                time.setText("已过期");
            }
            beginTime.setText(time_begin.format("yyyy.MM.dd"));
            endTime.setText(time_end.format("yyyy.MM.dd"));
        }else {
            time.setText("未授权");
            beginTime.setText("?");
            endTime.setText("?");
        }


    }
}
