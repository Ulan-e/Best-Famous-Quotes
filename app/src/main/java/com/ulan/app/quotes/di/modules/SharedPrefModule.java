package com.ulan.app.quotes.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.ulan.app.quotes.di.scopes.AppScope;
import com.ulan.app.quotes.helpers.Constants;

import dagger.Module;
import dagger.Provides;


@Module
public class SharedPrefModule {

    @AppScope
    @Provides
    public SharedPreferences sharedPreferences(Context application){
        return application.getSharedPreferences(Constants.TAG_SHARED_DATA, Context.MODE_PRIVATE);
    }
}
