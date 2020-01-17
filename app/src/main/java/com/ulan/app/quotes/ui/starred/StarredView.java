package com.ulan.app.quotes.ui.starred;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

public interface StarredView {

    void showStarredQuotes(List<QuoteModel> quoteList);
    void showNoStarredQuotes();
    void removeAllQuotes();

}
