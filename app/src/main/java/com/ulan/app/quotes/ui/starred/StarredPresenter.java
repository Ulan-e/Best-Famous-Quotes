package com.ulan.app.quotes.ui.starred;

import com.ulan.app.quotes.adapter.QuoteAdapter;
import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.data.database.DaoStarredQuotes;
import java.util.List;
import io.reactivex.Flowable;

public interface StarredPresenter {

    // ставить цитату в избранные
    void setStarredQuotes(Flowable<List<QuoteModel>> listObservable);

    // загрузить цитату из избранных
    void loadStarredQuotes();

    // очистить избранные цитаты
    void clearStarredQuotes(QuoteAdapter adapter, DaoStarredQuotes daoLikedQuotes);
}