package com.ulan.app.quotes.ui.mainactivity;

import com.ulan.app.quotes.data.QuoteData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityView mView;
    private Observable<List<QuoteData>> mListObservable;

    @Inject
    public MainActivityPresenterImpl(MainActivityView view) {
        this.mView = view;
    }

    public void setListObservable(Observable<List<QuoteData>> listObservable ){
        mListObservable = listObservable;
    }

    @Override
    public void loadObservable() {
        mView.setListOfQuotes(mListObservable);
    }
}