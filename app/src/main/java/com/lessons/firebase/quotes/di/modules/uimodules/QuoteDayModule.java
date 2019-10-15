package com.lessons.firebase.quotes.di.modules.uimodules;

import com.lessons.firebase.quotes.data.database.DaoQuotes;
import com.lessons.firebase.quotes.di.scopes.QuoteDayScope;
import com.lessons.firebase.quotes.ui.quoteofday.QuoteDayPresenterImpl;
import com.lessons.firebase.quotes.ui.quoteofday.QuoteDayView;

import dagger.Module;
import dagger.Provides;

@QuoteDayScope
@Module
public class QuoteDayModule {

    public QuoteDayView view;

    public QuoteDayModule(QuoteDayView view) {
        this.view = view;
    }

    @QuoteDayScope
    @Provides
    public QuoteDayPresenterImpl providePresenter(DaoQuotes daoQuotes){
        return new QuoteDayPresenterImpl(view, daoQuotes);
    }

}
