package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.data.database.DaoLikedQuotes;
import com.lessons.firebase.quotes.di.modules.uimodules.MainModule;
import com.lessons.firebase.quotes.di.scopes.ListScope;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.list.MainFragmentPresenterImpl;

import dagger.Subcomponent;

@ListScope
@Subcomponent(modules = {MainModule.class})
public interface ListComponent {

    @ListScope
    MainFragmentPresenterImpl getPresenter();

    @ListScope
    DaoLikedQuotes getDaoLikedQuotes();

    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface MABuilder{
        MABuilder mainModule(MainModule mainModule);
        ListComponent build();
    }
}
