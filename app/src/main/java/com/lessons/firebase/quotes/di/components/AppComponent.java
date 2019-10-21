package com.lessons.firebase.quotes.di.components;

import android.app.Application;

import com.lessons.firebase.quotes.di.modules.AppModule;
import com.lessons.firebase.quotes.di.modules.RxModule;
import com.lessons.firebase.quotes.di.modules.source.RoomModule;
import com.lessons.firebase.quotes.di.scopes.AppScope;

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

