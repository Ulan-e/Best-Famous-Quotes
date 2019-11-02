package com.ulan.app.quotes.di.modules.source;

import android.content.Context;
import android.content.SharedPreferences;

import com.ulan.app.quotes.di.scopes.AppScope;
import com.ulan.app.quotes.utils.Constants;

import dagger.Module;
import dagger.Provides;

@AppScope
@Module
public class SharedPrefModule {

    public Context application;

    public SharedPrefModule(Context application) {
        this.application = application;
    }

    @AppScope
    @Provides
    public SharedPreferences sharedPreferences(Context application){
        return application.getSharedPreferences(Constants.TAG_SHARED_DATA, Context.MODE_PRIVATE);
    }
}
