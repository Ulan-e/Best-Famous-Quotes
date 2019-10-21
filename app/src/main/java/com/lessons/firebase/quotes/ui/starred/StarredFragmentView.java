package com.lessons.firebase.quotes.ui.starred;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

public interface StarredFragmentView {

    void showLikedQuotes(List<QuoteData> quoteList);
    void showNoLikedQuotes();
    void clearAllQuotes();

}
