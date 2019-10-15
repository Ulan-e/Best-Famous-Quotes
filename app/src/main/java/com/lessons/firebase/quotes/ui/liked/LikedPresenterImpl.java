package com.lessons.firebase.quotes.ui.liked;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class LikedPresenterImpl implements LikedPresenter {

    private LikedFragmentView mView;
    private Observable<List<QuoteData>> mListQuoteData;

    public LikedPresenterImpl(LikedFragmentView view, Observable<List<QuoteData>> listObservable) {
        this.mView = view;
        this.mListQuoteData = listObservable;
    }

    @Override
    public void loadLikedQuotes(){
        mListQuoteData
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<QuoteData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<QuoteData> quoteData) {
                        int size = quoteData.size();
                        mView.showLikedQuotes(quoteData);

                    }

                    @Override
                    public void onError(Throwable error) {
                        mView.showNoLikedQuotes();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
