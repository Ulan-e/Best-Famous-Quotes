package com.lessons.firebase.quotes.ui.mainactivity;


import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;


public interface MainActivityView {

    void setListOfQuotes(Observable<List<QuoteData>> listOfQuotes);

}
