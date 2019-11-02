package com.ulan.app.quotes.di.components;

import com.ulan.app.quotes.di.modules.uimodules.OneQuoteModule;
import com.ulan.app.quotes.di.scopes.OneQuoteScope;
import com.ulan.app.quotes.ui.base.BaseFragment;
import com.ulan.app.quotes.ui.onequote.OneQuotePresenterImpl;

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