package com.ulan.app.quotes;

import android.app.Application;

import com.ulan.app.quotes.di.components.AppComponent;
import com.ulan.app.quotes.di.components.DaggerAppComponent;
import com.ulan.app.quotes.di.modules.AppModule;
import com.ulan.app.quotes.di.modules.RxModule;
import com.ulan.app.quotes.di.modules.source.RoomModule;

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
