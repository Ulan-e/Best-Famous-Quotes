package com.lessons.firebase.quotes.ui.liked;

import android.util.Log;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;

public class LikedPresenterImpl implements LikedPresenter {

    private LikedFragmentView mView;
    private Observable<List<QuoteData>> listObservable;

    public LikedPresenterImpl(LikedFragmentView mView, Observable<List<QuoteData>> listObservable) {
        this.mView = mView;
        this.listObservable = listObservable;
    }

    @Override
    public void loadSQuotes() {
        listObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<QuoteData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<QuoteData> quoteData) {
                        Log.d(TAG_OTHER, "onNext: " + quoteData.size());
                        mView.showChoosedQuotes(quoteData);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d(TAG_OTHER, "onError: " + error.getMessage());
                        mView.showNoChoosedQuotes();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG_OTHER, "onComplete: ");
                    }
                });
    }

    @Override
    public void gotoDetailFragment() {
        //TODO
    }
}
