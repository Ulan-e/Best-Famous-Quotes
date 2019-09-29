package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.data.database.model.DaoQuotes;
import com.lessons.firebase.quotes.di.scopes.DayQuoteScope;
import com.lessons.firebase.quotes.di.modules.ContextModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;

import dagger.Subcomponent;

@DayQuoteScope
@Subcomponent(modules = {ContextModule.class})
public interface DayQuoteComponent {

    DaoQuotes getDaoQuotes();

    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface DQBuilder{

        DQBuilder contextModule(ContextModule contextModule);
        DayQuoteComponent build();
    }

}