package com.lessons.firebase.quotes.ui.starred;

import com.lessons.firebase.quotes.adapter.QuoteAdapter;
import com.lessons.firebase.quotes.data.database.DaoStarredQuotes;

public interface StarredPresenter {

    void loadLikedQuotes();
    void getInfoClearList(QuoteAdapter adapter, DaoStarredQuotes daoLikedQuotes);

}
