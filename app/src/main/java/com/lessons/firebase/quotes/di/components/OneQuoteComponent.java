package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.di.modules.uimodules.OneQuoteModule;
import com.lessons.firebase.quotes.di.scopes.OneQuoteScope;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.onequote.OneQuotePresenterImpl;

import dagger.Subcomponent;

@OneQuoteScope
@Subcomponent(modules = {OneQuoteModule.class})
public interface OneQuoteComponent {

    OneQuotePresenterImpl getPresenter();
    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface DayBuilder {
        DayBuilder quoteDayModule(OneQuoteModule quoteDayModule);
        OneQuoteComponent build();
    }
}