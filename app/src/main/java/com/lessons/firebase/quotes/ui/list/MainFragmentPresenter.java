package com.lessons.firebase.quotes.ui.list;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;

public interface MainFragmentPresenter {

    void loadQuotes(Observable<List<QuoteData>> quoteDatObser);
}
