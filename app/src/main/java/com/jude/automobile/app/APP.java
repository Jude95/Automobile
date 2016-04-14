package com.jude.automobile.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jude.automobile.R;
import com.jude.automobile.domain.Dir;
import com.jude.beam.Beam;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.beam.expansion.overlay.ViewExpansionDelegate;
import com.jude.beam.expansion.overlay.ViewExpansionDelegateProvider;
import com.jude.utils.JFileManager;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class APP extends Application {
    public static final int RESULT_DELETE = 12;
    @Override
    public void onCreate() {
        super.onCreate();
        JUtils.initialize(this);
        JUtils.setDebug(true, "Automobile");
        JFileManager.getInstance().init(this, Dir.values());
        Stetho.initializeWithDefaults(this);
        Beam.init(this);
        Beam.setViewExpansionDelegateProvider(new ViewExpansionDelegateProvider() {
            @Override
            public ViewExpansionDelegate createViewExpansionDelegate(BeamBaseActivity activity) {
                return new NewViewExpansion(activity);
            }
        });

        Beam.setActivityLifeCycleDelegateProvider(ActivityDelegate::new);
        ListConfig.setDefaultListConfig(new ListConfig()
                .setPaddingNavigationBarAble(true)
                .setRefreshAble(true)
                .setContainerLayoutRes(R.layout.activity_recyclerview));
    }
}
