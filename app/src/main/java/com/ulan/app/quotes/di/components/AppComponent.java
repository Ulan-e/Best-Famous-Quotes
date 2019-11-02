package com.ulan.app.quotes.di.components;

import android.app.Application;

import com.ulan.app.quotes.di.modules.AppModule;
import com.ulan.app.quotes.di.modules.RxModule;
import com.ulan.app.quotes.di.modules.source.RoomModule;
import com.ulan.app.quotes.di.scopes.AppScope;

import dagger.Component;


@AppScope
@Component(modules = {AppModule.class, RoomModule.class, RxModule.class})
public interface AppComponent {

    void inject(Application application);

    MainActivityComponent.ActivityBuilder activityBuilder();
    HomeComponent.ListBuilder listBuilder();
    OneQuoteComponent.DayBuilder dayBuilder();
    StarredComponent.LikedBuilder likedBuilder();
}

