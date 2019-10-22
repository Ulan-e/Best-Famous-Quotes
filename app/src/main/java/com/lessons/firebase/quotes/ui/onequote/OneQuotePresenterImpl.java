package com.lessons.firebase.quotes.ui.onequote;

import com.lessons.firebase.quotes.data.database.DaoQuotes;
import com.lessons.firebase.quotes.utils.StringUtils;

public class OneQuotePresenterImpl implements OneQuotePresenter {

    private OneQuoteView mView;
    private DaoQuotes mDaoQuotes;

    public OneQuotePresenterImpl(OneQuoteView mView, DaoQuotes mDaoQuotes) {
        this.mView = mView;
        this.mDaoQuotes = mDaoQuotes;
    }

    @Override
    public void getQuote() {
        String quote = mDaoQuotes.getQuote().getQuote();
        String beforeAuthor = mDaoQuotes.getQuote().getAuthor();
        String afterAuthor = StringUtils.generateAuthorName(beforeAuthor);
        mView.showQuote(quote, afterAuthor);
    }
}
