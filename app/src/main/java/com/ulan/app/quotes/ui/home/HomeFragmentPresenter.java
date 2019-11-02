package com.ulan.app.quotes.ui.home;

import com.ulan.app.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;

public interface HomeFragmentPresenter {

    void loadQuotes(Observable<List<QuoteData>> quoteDatObser);
}
