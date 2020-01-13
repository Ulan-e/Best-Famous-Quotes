package com.ulan.app.quotes.di.modules.uimodules;

import com.ulan.app.quotes.di.scopes.HomeScope;
import com.ulan.app.quotes.ui.home.HomeFragment;
import com.ulan.app.quotes.ui.home.HomePresenterImpl;
import com.ulan.app.quotes.ui.home.HomeView;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @HomeScope
    @Provides
    public HomeView homeView(HomeFragment homeFragment){
        return homeFragment;
    }

    @HomeScope
    @Provides
    public HomePresenterImpl homePresenter(HomeView homeView){
        return new HomePresenterImpl(homeView);
    }

}
