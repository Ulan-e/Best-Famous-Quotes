package com.ulan.app.quotes.ui.starred;

import com.ulan.app.quotes.adapter.QuoteAdapter;
import com.ulan.app.quotes.data.database.DaoStarredQuotes;

public interface StarredPresenter {

    void loadLikedQuotes();
    void getInfoClearList(QuoteAdapter adapter, DaoStarredQuotes daoLikedQuotes);

}
