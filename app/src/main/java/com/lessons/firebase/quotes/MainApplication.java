package com.lessons.firebase.quotes;

import android.app.Application;

import com.lessons.firebase.quotes.di.components.AppComponent;
import com.lessons.firebase.quotes.di.components.DaggerAppComponent;
import com.lessons.firebase.quotes.di.modules.AppModule;

public class MainApplication extends Application {

    private static MainApplication instance;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public static MainApplication getInstance(){
        return instance;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

}
