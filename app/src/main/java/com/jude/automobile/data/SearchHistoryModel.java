package com.jude.automobile.data;

import android.content.Context;

import com.jude.automobile.domain.Dir;
import com.jude.automobile.domain.entities.Search;
import com.jude.beam.model.AbsModel;
import com.jude.utils.JFileManager;

import java.util.ArrayList;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class SearchHistoryModel extends AbsModel {
    private static final String FILE_SEARCH = "searches";
    public static SearchHistoryModel getInstance() {
        return getInstance(SearchHistoryModel.class);
    }
    private ArrayList<Search> mSearches;

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        mSearches = (ArrayList<Search>) JFileManager.getInstance().getFolder(Dir.Object).readObjectFromFile(FILE_SEARCH);
        if (mSearches == null)mSearches = new ArrayList<>();
    }

    public void clear(){
        mSearches.clear();
        JFileManager.getInstance().getFolder(Dir.Object).deleteChild(FILE_SEARCH);
    }

    public void addSearch(Search search){
        mSearches.add(0,search);
        JFileManager.getInstance().getFolder(Dir.Object).writeObjectToFile(mSearches,FILE_SEARCH);
    }

    public ArrayList<Search> getSearchHistory(){
        return mSearches;
    }
}
