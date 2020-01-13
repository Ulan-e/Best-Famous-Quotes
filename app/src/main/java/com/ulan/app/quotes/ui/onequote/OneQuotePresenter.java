package com.ulan.app.quotes.ui.onequote;

import com.ulan.app.quotes.data.database.DaoQuotes;

public interface OneQuotePresenter {

    void setDao(DaoQuotes daoQuotes);
    void getQuote();
}
