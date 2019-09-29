package com.lessons.firebase.quotes.ui.liked;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

public interface LikedFragmentView {

    void showChoosedQuotes(List<QuoteData> quoteList);
    void showNoChoosedQuotes();
    void removeFromChoosedQuotes();

}
