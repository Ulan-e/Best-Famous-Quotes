package com.ulan.app.quotes.ui.home;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

import io.reactivex.Observable;

public interface HomePresenter {

    void setQuotes(Observable<List<QuoteModel>> quoteDatObser);
    void resetQuotes();
    void detachView();
}
