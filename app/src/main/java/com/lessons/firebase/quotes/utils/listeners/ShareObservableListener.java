package com.lessons.firebase.quotes.utils.listeners;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;

public interface ShareObservableListener {

    public void passObservable(Observable<List<QuoteData>> listObservable);
}
