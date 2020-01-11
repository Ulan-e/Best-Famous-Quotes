package com.ulan.app.quotes.ui.starred;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

public interface StarredFragmentView {

    void showLikedQuotes(List<QuoteModel> quoteList);
    void showNoLikedQuotes();
    void clearAllQuotes();

}
