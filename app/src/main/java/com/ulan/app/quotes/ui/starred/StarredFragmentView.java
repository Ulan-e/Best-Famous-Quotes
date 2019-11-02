package com.ulan.app.quotes.ui.starred;

import com.ulan.app.quotes.data.QuoteData;

import java.util.List;

public interface StarredFragmentView {

    void showLikedQuotes(List<QuoteData> quoteList);
    void showNoLikedQuotes();
    void clearAllQuotes();

}
