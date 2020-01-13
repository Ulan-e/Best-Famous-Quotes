package com.ulan.app.quotes.ui.listeners;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Class can pass observable data between fragments
 */

public interface ShareObservableListener {

    void passObservable(Observable<List<QuoteModel>> listObservable);
}
