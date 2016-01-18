package com.jude.automobile.data;

import com.jude.automobile.domain.entities.Line;
import com.jude.beam.model.AbsModel;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class DataModel extends AbsModel {
    public static DataModel getInstance() {
        return getInstance(DataModel.class);
    }

    public Observable<Line> searchLine(String name){
        return Observable.from(createVirtualLines());
    }


    public ArrayList<Line> createVirtualLines(){
        ArrayList<Line> arrayList = new ArrayList();
        arrayList.add(new Line(1,"http://i2.hdslb.com/52_52/user/61175/6117592/myface.jpg","奥迪"));
        arrayList.add(new Line(1,"http://i1.hdslb.com/52_52/user/6738/673856/myface.jpg","奔驰"));
        arrayList.add(new Line(1,"http://i1.hdslb.com/account/face/1467772/e1afaf4a/myface.png","别克"));
        arrayList.add(new Line(1,"http://i0.hdslb.com/52_52/user/18494/1849483/myface.jpg","宝马"));
        arrayList.add(new Line(1,"http://i0.hdslb.com/52_52/account/face/4613528/303f4f5a/myface.png","丰田"));
        arrayList.add(new Line(1,"http://i0.hdslb.com/52_52/account/face/611203/76c02248/myface.png","凯迪拉克"));
        arrayList.add(new Line(1,"http://i2.hdslb.com/52_52/user/46230/4623018/myface.jpg","阿斯顿马丁"));
        arrayList.add(new Line(1,"http://i2.hdslb.com/52_52/user/66723/6672394/myface.jpg","法拉利"));
        arrayList.add(new Line(1,"http://i1.hdslb.com/user/3039/303946/myface.jpg","沃尔沃"));
        arrayList.add(new Line(1,"http://i2.hdslb.com/account/face/9034989/aabbc52a/myface.png","玛莎拉蒂"));
        arrayList.add(new Line(1,"http://i0.hdslb.com/account/face/1557783/8733bd7b/myface.png","宾利"));
        arrayList.add(new Line(1,"http://i2.hdslb.com/user/3716/371679/myface.jpg","世爵"));
        arrayList.add(new Line(1,"http://i1.hdslb.com/account/face/9045165/4b11d894/myface.png","迈凯轮"));
        return  arrayList;
    }
}
