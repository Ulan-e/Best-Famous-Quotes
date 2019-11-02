package com.ulan.app.quotes.di.modules.uimodules;

import com.ulan.app.quotes.data.database.DaoQuotes;
import com.ulan.app.quotes.di.scopes.OneQuoteScope;
import com.ulan.app.quotes.ui.onequote.OneQuotePresenterImpl;
import com.ulan.app.quotes.ui.onequote.OneQuoteView;

import dagger.Module;
import dagger.Provides;

@OneQuoteScope
@Module
public class OneQuoteModule {

    public OneQuoteView view;

    public OneQuoteModule(OneQuoteView view) {
        this.view = view;
    }

    @OneQuoteScope
    @Provides
    public OneQuotePresenterImpl providePresenter(DaoQuotes daoQuotes){
        return new OneQuotePresenterImpl(view, daoQuotes);
    }

}
