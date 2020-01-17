package com.ulan.app.quotes.ui.starred;

import com.ulan.app.quotes.adapter.QuoteAdapter;
import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.data.database.DaoStarredQuotes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;


public class StarredPresenterImpl implements StarredPresenter {

    private StarredView mView;
    private Flowable<List<QuoteModel>> mStarredQuotes;
    private CompositeDisposable mCompositeDisposable;

    public StarredPresenterImpl(StarredView view) {
        this.mView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setStarredQuotes(Flowable<List<QuoteModel>> listObservable) {
        this.mStarredQuotes = listObservable;
    }

    @Override
    public void loadStarredQuotes(){
        mCompositeDisposable.add(mStarredQuotes
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<QuoteModel>>() {
                    @Override
                    public void onNext(List<QuoteModel> quoteModels) {
                        mView.showStarredQuotes(quoteModels);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
                /*.subscribeWith(new DisposableObserver<List<QuoteModel>>() {
                    @Override
                    public void onNext(List<QuoteModel> quoteDatas) {


                    }

                    @Override
                    public void onError(Throwable error) {
                        mView.showNoStarredQuotes();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));*/
    }

    public void disposeObservable(){
        mCompositeDisposable.dispose();
    }

    @Override
    public void clearStarredQuotes(QuoteAdapter adapter, DaoStarredQuotes daoLikedQuotes) {
        if (adapter != null && daoLikedQuotes != null) {
            mView.removeAllQuotes();
        }
    }
}
