package com.lessons.firebase.quotes.ui.onequote;

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

import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.di.components.OneQuoteComponent;
import com.lessons.firebase.quotes.di.modules.uimodules.OneQuoteModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.utils.listeners.FragmentLifecycle;

import static com.lessons.firebase.quotes.utils.Constants.TAG_STATE;

public class OneQuoteFragment extends BaseFragment implements OneQuoteView, FragmentLifecycle{

    private TextView mQuoteText;
    private TextView mAuthorText;
    private OneQuotePresenterImpl mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getDayComponent();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.quote_day_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                mPresenter.getQuote();
                return true;
                default:
                    break;
        }
        return false;
    }

    private OneQuoteComponent getDayComponent(){
        OneQuoteComponent component = getAppComponent().dayBuilder()
                .quoteDayModule(new OneQuoteModule(this))
                .build();
        component.inject(this);
        mPresenter = component.getPresenter();
        return component;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quote_day_fragment, container, false);
        mQuoteText = view.findViewById(R.id.quote_day);
        mAuthorText = view.findViewById(R.id.quote_author);
        mPresenter.getQuote();
        return view;
    }

    @Override
    public void onPauseFragment(Fragment fragment) {
        Log.d(TAG_STATE, "onPauseFragment: OneQuoteFragment ");
    }

    @Override
    public void onResumeFragment() {
        Log.d(TAG_STATE, "onResumeFragment: OneQuoteFragment ");
    }

    @Override
    public void showQuote(String quote, String author) {
        mQuoteText.setText(quote);
        mAuthorText.setText(author);
    }
}
