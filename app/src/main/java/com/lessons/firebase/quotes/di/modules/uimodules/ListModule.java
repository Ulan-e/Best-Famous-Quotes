package com.lessons.firebase.quotes.di.modules.uimodules;

import com.lessons.firebase.quotes.di.scopes.ListScope;
import com.lessons.firebase.quotes.ui.list.MainFragmentPresenterImpl;
import com.lessons.firebase.quotes.ui.list.MainFragmentView;

import dagger.Module;
import dagger.Provides;

@ListScope
@Module
public class ListModule {

    MainFragmentView view;

    public ListModule(MainFragmentView view) {
        this.view = view;
    }

    @ListScope
    @Provides
    public MainFragmentView view(){
        return view;
    }

    @ListScope
    @Provides
    public MainFragmentPresenterImpl presenter(MainFragmentView view){
        return new MainFragmentPresenterImpl(view);
    }

}
