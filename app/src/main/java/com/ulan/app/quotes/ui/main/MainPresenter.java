package com.ulan.app.quotes.ui.main;

import com.ulan.app.quotes.data.QuoteModel;
import java.util.List;
import io.reactivex.Observable;

public interface MainPresenter {

    // прикрепить View
    void attachView(MainView view);

    // открепить View
    void detachView();

    // загрузка цитат
    void loadQuotes();

    // ставим цитату
    void setQuotes(Observable<List<QuoteModel>> quotes);
}