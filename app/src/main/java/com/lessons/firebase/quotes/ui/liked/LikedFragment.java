package com.lessons.firebase.quotes.ui.liked;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.adapter.LClickListener;
import com.lessons.firebase.quotes.adapter.QuoteAdapter;
import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.model.DaoLikedQuotes;
import com.lessons.firebase.quotes.di.components.LikedComponent;
import com.lessons.firebase.quotes.di.modules.ContextModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;

import java.util.List;

import io.reactivex.Observable;

import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;


public class LikedFragment extends BaseFragment implements LikedFragmentView {

    private RecyclerView recyclerView;
    private TextView textNoQuotes;
    private Button buttonClearTable;
    private DaoLikedQuotes daoChoosedQuotes;

    private LikedComponent mainComponent;
    private LikedPresenterImpl presenter;
    private QuoteAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMainComponent();

        Observable<List<QuoteData>> listObservable = mainComponent.getLikedDatas();
        daoChoosedQuotes = mainComponent.getDaoQuotes();
        presenter = new LikedPresenterImpl(this, listObservable);
        presenter.loadSQuotes();
    }

    private void getMainComponent(){
        mainComponent = getAppComponent().listComponentBuilder()
                .contextModule(new ContextModule(getActivity()))
                .build();
        mainComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choosed_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_choosed);
        textNoQuotes = view.findViewById(R.id.text_nochoosed_quotes);
        buttonClearTable = view.findViewById(R.id.clear_table_button);
        buttonClearTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter != null){
                    daoChoosedQuotes.deleteAll();
                    adapter.notifyDataSetChanged();
                }
                Log.d(TAG_OTHER, " ---- clear table " );
            }
        });
        return view;
    }

    @Override
    public void showChoosedQuotes(List<QuoteData> quoteList) {
        adapter = new QuoteAdapter(getActivity(), quoteList, new LClickListener() {
            @Override
            public void onPositionClicked(int position) {
                Log.d("trahtrah", "onPositionClicked in LikedFragment " + position );
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showNoChoosedQuotes() {
        textNoQuotes.setText("No Choosed Quotes");
    }

    @Override
    public void removeFromChoosedQuotes() {

    }
}
