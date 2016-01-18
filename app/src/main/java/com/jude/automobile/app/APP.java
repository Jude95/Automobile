package com.jude.automobile.app;

import android.app.Application;

import com.jude.automobile.domain.Dir;
import com.jude.beam.Beam;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beam.expansion.overlay.ViewExpansionDelegate;
import com.jude.beam.expansion.overlay.ViewExpansionDelegateProvider;
import com.jude.utils.JFileManager;
import com.jude.utils.JUtils;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JUtils.initialize(this);
        JUtils.setDebug(true, "Automobile");
        JFileManager.getInstance().init(this, Dir.values());
        Beam.init(this);
        Beam.setViewExpansionDelegateProvider(new ViewExpansionDelegateProvider() {
            @Override
            public ViewExpansionDelegate createViewExpansionDelegate(BeamBaseActivity activity) {
                return new NewViewExpansion(activity);
            }
        });
        Beam.setActivityLifeCycleDelegateProvider(ActivityDelegate::new);

    }
}