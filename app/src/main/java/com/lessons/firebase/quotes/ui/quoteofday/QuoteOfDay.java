package com.lessons.firebase.quotes.ui.quoteofday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.data.database.model.DaoQuotes;
import com.lessons.firebase.quotes.data.database.QuoteEntity;
import com.lessons.firebase.quotes.di.components.DayQuoteComponent;
import com.lessons.firebase.quotes.di.modules.ContextModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;

public class QuoteOfDay extends BaseFragment {

    DaoQuotes daoQuotes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DayQuoteComponent component = getAppComponent().dayQuoteComponentBuilder()
                .contextModule(new ContextModule(getActivity()))
                .build();

        component.inject(this);

        daoQuotes = component.getDaoQuotes();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quote_of_the_day, container, false);
        TextView textView = view.findViewById(R.id.quote_day);
        QuoteEntity quoteEntity = daoQuotes.getQuote();
        textView.setText(quoteEntity.getQuote());
        return view;
    }
}
