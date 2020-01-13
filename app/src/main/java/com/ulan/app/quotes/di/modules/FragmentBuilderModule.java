package com.ulan.app.quotes.di.modules;

import com.ulan.app.quotes.di.modules.uimodules.HomeModule;
import com.ulan.app.quotes.di.modules.uimodules.OneQuoteModule;
import com.ulan.app.quotes.di.modules.uimodules.StarredModule;
import com.ulan.app.quotes.di.scopes.HomeScope;
import com.ulan.app.quotes.di.scopes.OneQuoteScope;
import com.ulan.app.quotes.di.scopes.StarredScope;
import com.ulan.app.quotes.ui.home.HomeFragment;
import com.ulan.app.quotes.ui.onequote.OneQuoteFragment;
import com.ulan.app.quotes.ui.starred.StarredFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {SharedPrefModule.class, ObservablesModule.class})
public abstract class FragmentBuilderModule {

    @HomeScope
    @ContributesAndroidInjector(modules = {HomeModule.class})
    abstract HomeFragment homeFragment();

    @StarredScope
    @ContributesAndroidInjector(modules = {StarredModule.class})
    abstract StarredFragment starredFragment();

    @OneQuoteScope
    @ContributesAndroidInjector(modules = {OneQuoteModule.class, RoomModule.class})
    abstract OneQuoteFragment oneQuoteFragment();
}
