package com.ulan.app.quotes.ui.mainactivity;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityView mView;
    private Observable<List<QuoteModel>> mListObservable;

    @Inject
    public MainActivityPresenterImpl(MainActivityView view) {
        this.mView = view;
    }

    public void setListObservable(Observable<List<QuoteModel>> listObservable ){
        mListObservable = listObservable;
    }

    @Override
    public void loadObservable() {
        mView.setListOfQuotes(mListObservable);
    }
}