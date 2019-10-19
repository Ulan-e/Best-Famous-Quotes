package com.lessons.firebase.quotes.ui.list;

import android.util.Log;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;

public class MainFragmentPresenterImpl implements MainFragmentPresenter {

    private MainFragmentView mView;
    private Observable<List<QuoteData>> listObservable;

    public MainFragmentPresenterImpl(MainFragmentView mView) {
        this.mView = mView;
    }

    @Override
    public void loadQuotes(Observable<List<QuoteData>> quoteDatObser) {
            quoteDatObser
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<QuoteData>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<QuoteData> quoteData) {
                            Log.d(TAG_OTHER, "onNext: Main Fragment " + quoteData.size());
                            mView.showQuotes(quoteData);
                        }

                        @Override
                        public void onError(Throwable error) {
                            Log.d(TAG_OTHER, "onError: Main Fragment " + error.getMessage());
                            mView.showQuotesError(error);
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG_OTHER, "onComplete: ");
                        }
                    });
    }


}
