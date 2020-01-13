package com.ulan.app.quotes.di.components;

import com.ulan.app.quotes.MainApplication;
import com.ulan.app.quotes.di.modules.ActivityBuilderModule;
import com.ulan.app.quotes.di.modules.AppModule;
import com.ulan.app.quotes.di.modules.FragmentBuilderModule;
import com.ulan.app.quotes.di.modules.ObservablesModule;
import com.ulan.app.quotes.di.modules.RoomModule;
import com.ulan.app.quotes.di.scopes.AppScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@AppScope
@Component(modules = {
        AppModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        FragmentBuilderModule.class,
        RoomModule.class,
        ObservablesModule.class})
public interface AppComponent extends AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        AppComponent.Builder application(MainApplication application);
        AppComponent build();
    }
}

