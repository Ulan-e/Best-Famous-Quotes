package com.lessons.firebase.quotes;

import android.app.Application;

import com.lessons.firebase.quotes.di.components.AppComponent;
import com.lessons.firebase.quotes.di.components.DaggerAppComponent;
import com.lessons.firebase.quotes.di.modules.AppModule;
import com.lessons.firebase.quotes.di.modules.RxModule;
import com.lessons.firebase.quotes.di.modules.source.RoomModule;

public class MainApplication extends Application {

    private static MainApplication instance;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mAppComponent = DaggerAppComponent.builder()
                .roomModule(new RoomModule())
                .appModule(new AppModule(this))
                .rxModule(new RxModule())
                .build();
    }

    public static MainApplication getInstance(){
        return instance;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

}
