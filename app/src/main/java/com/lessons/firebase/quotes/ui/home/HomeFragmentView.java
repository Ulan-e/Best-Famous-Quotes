package com.lessons.firebase.quotes.ui.home;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

public interface HomeFragmentView {

    void showQuotes(List<QuoteData> listQuotes);
    void showQuotesError(Throwable e);

}
