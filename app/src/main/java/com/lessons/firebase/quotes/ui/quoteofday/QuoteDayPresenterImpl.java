package com.lessons.firebase.quotes.ui.quoteofday;

import com.lessons.firebase.quotes.data.database.DaoQuotes;
import com.lessons.firebase.quotes.utils.StringUtils;

public class QuoteDayPresenterImpl implements QuoteDayPresenter {

    private QuoteDayView mView;
    private DaoQuotes mDaoQuotes;

    public QuoteDayPresenterImpl(QuoteDayView mView, DaoQuotes mDaoQuotes) {
        this.mView = mView;
        this.mDaoQuotes = mDaoQuotes;
    }


    @Override
    public void getQuote() {
        String quote = mDaoQuotes.getQuote().getQuote();
        String beforeAuthor = mDaoQuotes.getQuote().getAuthor();
        String afterAuthor = StringUtils.exchangeSrtings(beforeAuthor);
        mView.showQuote(quote, afterAuthor);
    }
}
