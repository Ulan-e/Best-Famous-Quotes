package com.ulan.app.quotes.di.modules;

import android.content.Context;

import com.ulan.app.quotes.MainApplication;
import com.ulan.app.quotes.di.scopes.AppScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Binds
    abstract Context provideContext(MainApplication application);


}
