package com.ulan.app.quotes.ui.onequote;

import com.ulan.app.quotes.data.database.DaoQuotes;

public interface OneQuotePresenter {

    // cтавить Dao
    void setDao(DaoQuotes daoQuotes);

    // ставим цитату
    void getQuote();
}