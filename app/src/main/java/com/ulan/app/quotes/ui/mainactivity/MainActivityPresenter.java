package com.ulan.app.quotes.ui.mainactivity;

import com.ulan.app.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;

public interface MainActivityPresenter {

    void setListObservable(Observable<List<QuoteData>> listObservable);
    void loadObservable();

}
