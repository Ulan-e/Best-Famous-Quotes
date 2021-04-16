package com.ulan.app.quotes.ui.home;

import com.ulan.app.quotes.data.QuoteModel;
import java.util.List;
import io.reactivex.Observable;

public interface HomePresenter {

    // ставим цитату
    void setQuotes(Observable<List<QuoteModel>> quotes);

    // удаляем цитату
    void resetQuotes();

    // открепляем View
    void detachView();
}