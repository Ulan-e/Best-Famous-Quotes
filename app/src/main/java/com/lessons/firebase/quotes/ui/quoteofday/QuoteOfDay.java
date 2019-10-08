package com.lessons.firebase.quotes.ui.quoteofday;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lessons.firebase.quotes.FragmentLifecycle;
import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.data.database.DaoQuotes;
import com.lessons.firebase.quotes.data.database.QuoteEntity;
import com.lessons.firebase.quotes.di.components.DayQuoteComponent;
import com.lessons.firebase.quotes.di.modules.ContextModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.utils.StringUtils;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.lessons.firebase.quotes.utils.Constants.TAG_STATE;

public class QuoteOfDay extends BaseFragment implements FragmentLifecycle {

    private DaoQuotes mDaoQuotes;
    private TextView mQuoteText;
    private TextView mAuthorText;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mDaoQuotes = getDayComponent().getDaoQuotes();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.day_of_quote_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                setQuote();
                return true;
                default:
                    break;
        }
        return false;
    }

    public DayQuoteComponent getDayComponent(){
        DayQuoteComponent component = getAppComponent().dayQuoteComponentBuilder()
                .contextModule(new ContextModule(getActivity()))
                .build();
        component.inject(this);
        return component;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quote_of_the_day, container, false);
        mQuoteText = view.findViewById(R.id.quote_day);
        mAuthorText = view.findViewById(R.id.quote_author);
        setQuote();
        return view;
    }

    private void setQuote(){
       mDaoQuotes.getQuote().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new SingleObserver<QuoteEntity>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onSuccess(QuoteEntity quoteEntity) {
                       mQuoteText.setText(quoteEntity.getQuote());
                       String beforeAuthor = quoteEntity.getName();
                       String afterAuthor = StringUtils.exchangeSrtings(beforeAuthor);
                       mAuthorText.setText(afterAuthor);

                   }

                   @Override
                   public void onError(Throwable e) {

                   }
               });
    }

    @Override
    public void onPauseFragment(Fragment fragment) {
        Log.d(TAG_STATE, "onPauseFragment: QuoteOfDay ");
    }

    @Override
    public void onResumeFragment() {
        Log.d(TAG_STATE, "onResumeFragment: QuoteOfDay ");
    }

    @Override
    public String toString() {
        return "QuoteOfDay{}";
    }
}
