package com.lessons.firebase.quotes.di.modules.uimodules;

import com.lessons.firebase.quotes.data.database.DaoQuotes;
import com.lessons.firebase.quotes.di.scopes.OneQuoteScope;
import com.lessons.firebase.quotes.ui.onequote.OneQuotePresenterImpl;
import com.lessons.firebase.quotes.ui.onequote.OneQuoteView;

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
