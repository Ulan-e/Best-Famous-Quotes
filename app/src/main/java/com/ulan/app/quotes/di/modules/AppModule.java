package com.ulan.app.quotes.di.modules;

import android.app.Application;
import android.content.Context;

import com.ulan.app.quotes.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
@AppScope
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @AppScope
    @Provides
    public Context provideContext(){
        return mApplication;
    }


}
