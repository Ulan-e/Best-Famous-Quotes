package com.ulan.app.quotes.ui.mainactivity;


import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

import io.reactivex.Observable;


public interface MainActivityView {

    void setListOfQuotes(Observable<List<QuoteModel>> listOfQuotes);

}
