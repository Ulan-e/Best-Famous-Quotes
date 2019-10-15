package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.di.modules.uimodules.QuoteDayModule;
import com.lessons.firebase.quotes.di.scopes.DayQuoteScope;
import com.lessons.firebase.quotes.di.scopes.QuoteDayScope;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.quoteofday.QuoteDayPresenterImpl;

import dagger.Subcomponent;

@QuoteDayScope
@Subcomponent(modules = {QuoteDayModule.class})
public interface DayQuoteComponent {

    QuoteDayPresenterImpl getPresenter();
    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface DQBuilder{
        DQBuilder quoteDayModule(QuoteDayModule quoteDayModule);
        DayQuoteComponent build();
    }
}