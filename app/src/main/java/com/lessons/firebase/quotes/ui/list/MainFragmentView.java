package com.lessons.firebase.quotes.ui.list;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

public interface MainFragmentView {

    void showQuotes(List<QuoteData> listQuotes);
    void showProgress();
    void hideProgress();
    void showQuotesError(Throwable e);

}
