package com.lessons.firebase.quotes.di.modules.uimodules;

import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.di.scopes.MainScope;
import com.lessons.firebase.quotes.ui.mainactivity.MainActivityPresenterImpl;
import com.lessons.firebase.quotes.ui.mainactivity.MainActivityView;

import dagger.Module;
import dagger.Provides;

@MainScope
@Module
public class MainModulee {

    public MainActivityView mView;

    public MainModulee(MainActivityView view) {
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
