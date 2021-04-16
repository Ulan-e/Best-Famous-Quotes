package com.ulan.app.quotes.di.modules;

import android.content.Context;
import com.ulan.app.quotes.MainApplication;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {

    @Binds
    abstract Context provideContext(MainApplication application);
}