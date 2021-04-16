package com.ulan.app.quotes.ui.starred;

import com.ulan.app.quotes.data.QuoteModel;
import java.util.List;

public interface StarredView {

    // показать избранные цитаты
    void showStarredQuotes(List<QuoteModel> quoteList);

    // показать ошибку
    void showNoStarredQuotes();

    // удалить все избранные цитаты
    void removeAllQuotes();
}