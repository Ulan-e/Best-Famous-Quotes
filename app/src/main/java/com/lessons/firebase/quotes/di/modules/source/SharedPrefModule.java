package com.lessons.firebase.quotes.di.modules.source;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lessons.firebase.quotes.di.scopes.AppScope;

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
        return application.getSharedPreferences("ulan", Context.MODE_PRIVATE);
    }
}
