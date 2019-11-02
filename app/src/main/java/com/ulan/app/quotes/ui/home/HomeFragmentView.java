package com.ulan.app.quotes.ui.home;

import com.ulan.app.quotes.data.QuoteData;

import java.util.List;

public interface HomeFragmentView {

    void showQuotes(List<QuoteData> listQuotes);
    void showQuotesError(Throwable e);

}
