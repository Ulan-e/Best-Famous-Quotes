package com.ulan.app.quotes.di.modules.uimodules;

import com.ulan.app.quotes.di.scopes.MainScope;
import com.ulan.app.quotes.ui.mainactivity.MainActivityPresenterImpl;
import com.ulan.app.quotes.ui.mainactivity.MainActivityView;

import dagger.Module;
import dagger.Provides;

@MainScope
@Module
public class MainModule {

    public MainActivityView mView;

    public MainModule(MainActivityView view) {
        this.mView = view;
    }

    @MainScope
    @Provides
    public MainActivityView provideView(){
        return mView;
    }

    @MainScope
    @Provides
    public MainActivityPresenterImpl providePresenter(){
        return new MainActivityPresenterImpl(mView);
    }
}
