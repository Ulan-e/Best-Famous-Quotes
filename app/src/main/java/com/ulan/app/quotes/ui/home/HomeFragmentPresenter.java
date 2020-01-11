package com.ulan.app.quotes.ui.home;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

import io.reactivex.Observable;

public interface HomeFragmentPresenter {

    void attachRxData(Observable<List<QuoteModel>> quoteDatObser);
    void detachRxData();
    void detachView();
}
