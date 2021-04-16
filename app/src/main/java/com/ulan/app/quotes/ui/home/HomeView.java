package com.ulan.app.quotes.ui.home;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

public interface HomeView {

    // показать цитату
    void showQuotes(List<QuoteModel> listQuotes);

    // показать ошибку
    void showNoQuotes(Throwable e);
}