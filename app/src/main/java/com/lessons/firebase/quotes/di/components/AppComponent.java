package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.di.modules.AppModule;
import com.lessons.firebase.quotes.di.modules.rxmodules.ObservableModule;
import com.lessons.firebase.quotes.ui.mainactivity.MainActivity;

import dagger.Component;


@AppScope
@Component(modules = {AppModule.class, ObservableModule.class})
public interface AppComponent {

    void inject(MainActivity activity);

    ListComponent.MABuilder mainActivityComponentBuilder();
    DayQuoteComponent.DQBuilder dayQuoteComponentBuilder();
    LikedComponent.LCBuilder listComponentBuilder();
}

