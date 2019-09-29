package com.lessons.firebase.quotes.ui.list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.adapter.LClickListener;
import com.lessons.firebase.quotes.adapter.QuoteAdapter;
import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.model.DaoLikedQuotes;
import com.lessons.firebase.quotes.di.components.ListComponent;
import com.lessons.firebase.quotes.di.modules.ContextModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;

import java.util.List;

import io.reactivex.Observable;

import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;

public class MainFragment extends BaseFragment implements MainFragmentView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ListComponent listComponent;
    private MainFragmentPresenterImpl presenter;
    private DaoLikedQuotes daoChoosedQuotes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMainComponent();

        Observable<List<QuoteData>> listObservable = listComponent.getObshiiData();
        presenter = new MainFragmentPresenterImpl(this, listObservable);
        presenter.loadQuotes();

        daoChoosedQuotes = listComponent.getDaoChoosedQuotes();
    }

    public void getMainComponent(){
        listComponent = getAppComponent().mainActivityComponentBuilder()
                .contextModule(new ContextModule(getActivity()))
                .build();
        listComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_quotes);
        return view;
    }

    @Override
    public void showQuotes(List<QuoteData> listQuotes) {
        QuoteAdapter adapter = new QuoteAdapter(getActivity(), listQuotes, new LClickListener() {
            @Override
            public void onPositionClicked(int position) {
                QuoteData quote = listQuotes.get(position);
                daoChoosedQuotes.setToTable(quote);
                Log.d("trahtrah", "position in MAin fragment#### " + String.valueOf(position));
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showQuotesError(Throwable e) {
        Log.d(TAG_OTHER, "showQuotesError: " + e.getMessage());
    }

}
