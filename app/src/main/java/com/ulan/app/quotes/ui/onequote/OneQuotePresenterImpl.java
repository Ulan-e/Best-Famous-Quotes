package com.ulan.app.quotes.ui.onequote;

import com.ulan.app.quotes.data.database.DaoQuotes;

import static com.ulan.app.quotes.utils.ListDataHandler.generateAuthorName;

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
        String afterAuthor = generateAuthorName(beforeAuthor);
        mView.showQuote(quote, afterAuthor);
    }
}
