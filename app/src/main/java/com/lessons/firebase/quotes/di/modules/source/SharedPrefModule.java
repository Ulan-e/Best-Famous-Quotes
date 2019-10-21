package com.lessons.firebase.quotes.di.modules.source;

import android.content.Context;
import android.content.SharedPreferences;

import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.utils.Constants;

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
