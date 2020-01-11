package com.ulan.app.quotes.ui.starred;

import com.ulan.app.quotes.adapter.QuoteAdapter;
import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.data.database.DaoStarredQuotes;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class StarredPresenterImpl implements StarredPresenter {

    private StarredFragmentView mView;
    private Observable<List<QuoteModel>> mListQuoteData;
    private CompositeDisposable compositeDisposable;

    public StarredPresenterImpl(StarredFragmentView view, Observable<List<QuoteModel>> listObservable) {
        this.mView = view;
        this.mListQuoteData = listObservable;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadLikedQuotes(){
        compositeDisposable.add(mListQuoteData
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<QuoteModel>>() {
                    @Override
                    public void onNext(List<QuoteModel> quoteDatas) {
                        mView.showLikedQuotes(quoteDatas);

                    }

                    @Override
                    public void onError(Throwable error) {
                        mView.showNoLikedQuotes();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void disposeObservable(){
        compositeDisposable.dispose();
    }

    @Override
    public void getInfoClearList(QuoteAdapter adapter, DaoStarredQuotes daoLikedQuotes) {
        if (adapter != null && daoLikedQuotes != null) {
            mView.clearAllQuotes();
        }
    }
}
