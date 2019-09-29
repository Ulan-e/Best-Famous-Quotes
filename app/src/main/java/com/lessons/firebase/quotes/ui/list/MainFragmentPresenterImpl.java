package com.lessons.firebase.quotes.ui.list;

import android.util.Log;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;

public class MainFragmentPresenterImpl implements MainFragmentPresenter {

    private MainFragmentView mView;
    private Observable<List<QuoteData>> quoteDatObser;

    public MainFragmentPresenterImpl(MainFragmentView mView, Observable<List<QuoteData>> quoteDatObser) {
        this.mView = mView;
        this.quoteDatObser = quoteDatObser;
    }

    @Override
    public void gotoDetailsFragment() {
        Log.d(TAG_OTHER, "gotoDetailsFragment: ---------------->>>>>>>> ");
    }

    @Override
    public void loadQuotes() {
        mView.showProgress();
            quoteDatObser
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<QuoteData>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<QuoteData> quoteData) {
                            Log.d(TAG_OTHER, "onNext: " + quoteData.size());
                            mView.showQuotes(quoteData);
                            mView.hideProgress();
                        }

                        @Override
                        public void onError(Throwable error) {
                            Log.d(TAG_OTHER, "onError: " + error.getMessage());
                            mView.showQuotesError(error);
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG_OTHER, "onComplete: ");
                        }
                    });

    }
}
