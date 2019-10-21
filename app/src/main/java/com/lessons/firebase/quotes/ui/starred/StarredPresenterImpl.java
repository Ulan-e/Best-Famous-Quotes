package com.lessons.firebase.quotes.ui.starred;

import com.lessons.firebase.quotes.adapter.QuoteAdapter;
import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.DaoLikedQuotes;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class StarredPresenterImpl implements StarredPresenter {

    private StarredFragmentView mView;
    private Observable<List<QuoteData>> mListQuoteData;

    public StarredPresenterImpl(StarredFragmentView view, Observable<List<QuoteData>> listObservable) {
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
                    public void onNext(List<QuoteData> quoteDatas) {
                        mView.showLikedQuotes(quoteDatas);

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

    @Override
    public void getInfoClearList(QuoteAdapter adapter, DaoLikedQuotes daoLikedQuotes) {
        if (adapter != null && daoLikedQuotes != null) {
            mView.clearAllQuotes();
        }
    }
}
