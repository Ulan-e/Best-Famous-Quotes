package com.ulan.app.quotes.ui.main;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

import io.reactivex.Observable;

public interface MainPresenter {

    void attachView(MainView view);
    void detachView();
    void loadQuotes();
    void setQuotes(Observable<List<QuoteModel>> quotes);

}
