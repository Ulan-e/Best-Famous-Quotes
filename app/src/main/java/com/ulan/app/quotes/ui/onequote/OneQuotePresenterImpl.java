package com.ulan.app.quotes.ui.onequote;

import com.ulan.app.quotes.data.database.DaoQuotes;

import static com.ulan.app.quotes.helpers.ListDataHelpers.generateAuthorName;

public class OneQuotePresenterImpl implements OneQuotePresenter {

    private OneQuoteView mView;
    private DaoQuotes mDaoQuotes;

    public OneQuotePresenterImpl(OneQuoteView mView) {
        this.mView = mView;
    }

    @Override
    public void setDao(DaoQuotes daoQuotes) {
        this.mDaoQuotes = daoQuotes;
    }

    @Override
    public void getQuote() {
        String quote = mDaoQuotes.getQuote().getQuote();
        String beforeAuthor = mDaoQuotes.getQuote().getAuthor();
        String afterAuthor = generateAuthorName(beforeAuthor);
        mView.showQuote(quote, afterAuthor);
    }
}
