package com.lessons.firebase.quotes.ui.home;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;

public interface HomeFragmentPresenter {

    void loadQuotes(Observable<List<QuoteData>> quoteDatObser);
}
