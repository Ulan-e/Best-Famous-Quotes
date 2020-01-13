package com.ulan.app.quotes.di.modules.uimodules;

import com.ulan.app.quotes.di.scopes.OneQuoteScope;
import com.ulan.app.quotes.ui.onequote.OneQuoteFragment;
import com.ulan.app.quotes.ui.onequote.OneQuotePresenterImpl;
import com.ulan.app.quotes.ui.onequote.OneQuoteView;

import dagger.Module;
import dagger.Provides;

@Module
public class OneQuoteModule {

    @OneQuoteScope
    @Provides
    public OneQuoteView oneQuoteView(OneQuoteFragment fragment){
        return fragment;
    }

    @OneQuoteScope
    @Provides
    public OneQuotePresenterImpl oneQuotePresenter(OneQuoteView view){
        return new OneQuotePresenterImpl(view);
    }

}
