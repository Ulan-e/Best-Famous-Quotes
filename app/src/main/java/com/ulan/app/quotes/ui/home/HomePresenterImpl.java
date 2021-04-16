package com.ulan.app.quotes.ui.home;

import com.ulan.app.quotes.data.QuoteModel;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import static com.ulan.app.quotes.helpers.Constants.TAG_OTHER;

public class HomePresenterImpl implements HomePresenter {

    private HomeView mView;
    private CompositeDisposable mCompositeDisposable;

    public HomePresenterImpl(HomeView mView) {
        this.mView = mView;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setQuotes(Observable<List<QuoteModel>> quotes) {
        mCompositeDisposable.add(quotes
                    .retry(1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<QuoteModel>>() {
                        @Override
                        public void onNext(List<QuoteModel> quoteData) {
                            mView.showQuotes(quoteData);
                        }

                        @Override
                        public void onError(Throwable error) {
                            mView.showNoQuotes(error);
                        }

                        @Override
                        public void onComplete() { }
                    }));
    }

    @Override
    public void resetQuotes(){
        mCompositeDisposable.dispose();
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}