package com.lessons.firebase.quotes.utils.listeners;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Class can pass observable data between fragments
 */

public interface ShareObservableListener {

    void passObservable(Observable<List<QuoteData>> listObservable);
}
