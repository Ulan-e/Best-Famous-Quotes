package com.ulan.app.quotes.ui.starred;

import com.ulan.app.quotes.adapter.QuoteAdapter;
import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.data.database.DaoStarredQuotes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface StarredPresenter {

    void setStarredQuotes(Flowable<List<QuoteModel>> listObservable);
    void loadStarredQuotes();
    void clearStarredQuotes(QuoteAdapter adapter, DaoStarredQuotes daoLikedQuotes);

}
