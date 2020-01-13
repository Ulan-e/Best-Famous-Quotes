package com.ulan.app.quotes.di.modules;

import com.ulan.app.quotes.di.modules.uimodules.MainModule;
import com.ulan.app.quotes.di.scopes.MainScope;
import com.ulan.app.quotes.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule{

    @MainScope
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity mainActivityInjector();
}
