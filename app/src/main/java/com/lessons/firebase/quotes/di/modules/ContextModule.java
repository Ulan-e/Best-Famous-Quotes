package com.lessons.firebase.quotes.di.modules;

import android.content.Context;

import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.di.scopes.ListScope;

import dagger.Module;
import dagger.Provides;

@ListScope
@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    public Context provideContext(){
        return context;
    }
}
