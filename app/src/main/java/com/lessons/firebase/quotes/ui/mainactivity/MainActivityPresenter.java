package com.lessons.firebase.quotes.ui.mainactivity;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;

public interface MainActivityPresenter {

    void setListObservable(Observable<List<QuoteData>> listObservable);
    void loadObservable();

}
