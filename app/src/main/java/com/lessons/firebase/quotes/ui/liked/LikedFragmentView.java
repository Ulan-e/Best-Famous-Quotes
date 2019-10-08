package com.lessons.firebase.quotes.ui.liked;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

public interface LikedFragmentView {

    void showLikedQuotes(List<QuoteData> quoteList);
    void showNoLikedQuotes();
    void removeFromChoosedQuotes();

}
