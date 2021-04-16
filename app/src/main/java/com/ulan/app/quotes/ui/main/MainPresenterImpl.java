package com.ulan.app.quotes.ui.main;

import com.ulan.app.quotes.data.QuoteModel;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;

public class MainPresenterImpl implements MainPresenter {

    private MainView mView;
    private Observable<List<QuoteModel>> mQuotes;

    @Inject
    public MainPresenterImpl() {}

    @Override
    public void attachView(MainView view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void loadQuotes() {
        mView.saveQuotesInPref(mQuotes);
    }

    @Override
    public void setQuotes(Observable<List<QuoteModel>> quotes) {
        mQuotes = quotes;
    }
}