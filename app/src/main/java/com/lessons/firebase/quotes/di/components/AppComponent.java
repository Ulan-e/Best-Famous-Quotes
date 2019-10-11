package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.di.modules.source.RoomModule;
import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.di.modules.AppModule;
import com.lessons.firebase.quotes.di.modules.RxModule;
import com.lessons.firebase.quotes.ui.mainactivity.MainActivity;

import dagger.Component;


@AppScope
@Component(modules = {AppModule.class, RxModule.class, RoomModule.class})
public interface AppComponent {

    void inject(MainActivity activity);

    ListComponent.MABuilder mainActivityComponentBuilder();
    DayQuoteComponent.DQBuilder dayQuoteComponentBuilder();
    LikedComponent.LCBuilder listComponentBuilder();
}

