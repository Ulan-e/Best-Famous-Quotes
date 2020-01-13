package com.ulan.app.quotes;

import com.ulan.app.quotes.di.components.AppComponent;
import com.ulan.app.quotes.di.components.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class MainApplication extends DaggerApplication {

    private AppComponent mAppComponent;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        mAppComponent = DaggerAppComponent.builder().application(this).build();
        return mAppComponent;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
