package com.lessons.firebase.quotes.ui.home;

import android.util.Log;

import com.lessons.firebase.quotes.data.QuoteData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;

public class HomeFragmentPresenterImpl implements HomeFragmentPresenter {

    private HomeFragmentView mView;
    private CompositeDisposable compositeDisposable;

    public HomeFragmentPresenterImpl(HomeFragmentView mView) {
        this.mView = mView;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadQuotes(Observable<List<QuoteData>> listObservable) {
        compositeDisposable.add(listObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<QuoteData>>() {
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
                    }));
    }

    public void disposeObservable(){
        compositeDisposable.dispose();
    }


}


/*

        listObservable
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
        });*/
