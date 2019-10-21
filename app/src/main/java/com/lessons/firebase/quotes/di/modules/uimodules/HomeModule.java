package com.lessons.firebase.quotes.di.modules.uimodules;

import com.lessons.firebase.quotes.di.scopes.HomeScope;
import com.lessons.firebase.quotes.ui.home.HomeFragmentPresenterImpl;
import com.lessons.firebase.quotes.ui.home.HomeFragmentView;

import dagger.Module;
import dagger.Provides;

@HomeScope
@Module
public class HomeModule {

    HomeFragmentView view;

    public HomeModule(HomeFragmentView view) {
        this.view = view;
    }

    @HomeScope
    @Provides
    public HomeFragmentView view(){
        return view;
    }

    @HomeScope
    @Provides
    public HomeFragmentPresenterImpl presenter(HomeFragmentView view){
        return new HomeFragmentPresenterImpl(view);
    }

}
