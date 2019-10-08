package com.lessons.firebase.quotes.ui.liked;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lessons.firebase.quotes.FragmentLifecycle;
import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.adapter.LClickListener;
import com.lessons.firebase.quotes.adapter.QuoteAdapter;
import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.DaoLikedQuotes;
import com.lessons.firebase.quotes.di.components.LikedComponent;
import com.lessons.firebase.quotes.di.modules.uimodules.LikedModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.list.MainFragment;

import java.util.List;

import static android.view.View.GONE;
import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;
import static com.lessons.firebase.quotes.utils.Constants.TAG_STATE;


public class LikedFragment extends BaseFragment implements LikedFragmentView, FragmentLifecycle {

    private RecyclerView recyclerView;
    private TextView textNoQuotes;
    private LikedComponent likedComponent;
    private QuoteAdapter adapter;

    private DaoLikedQuotes daoLikedQuotes;
    private LikedPresenterImpl presenter;

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMainComponent();
        setHasOptionsMenu(true);
        daoLikedQuotes = likedComponent.getDaoQuotes();
        presenter = likedComponent.getPresenter();
        presenter.loadSQuotes();

        sharedPreferences = getActivity().getSharedPreferences("ulan", Context.MODE_PRIVATE);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.liked_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_list:
                clearList();
                return true;
            default:
                break;
        }
        return false;
    }

    private void getMainComponent() {
        likedComponent = getAppComponent().listComponentBuilder()
                .likedModule(new LikedModule(this))
                .build();
        likedComponent.inject(this);
    }

    public void clearList() {
        if (adapter != null) {
            daoLikedQuotes.deleteAll();
            Toast.makeText(getActivity(), " List is Cleared ", Toast.LENGTH_SHORT).show();
            adapter.deleteAllI();
            adapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choosed_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_liked);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(false);
        textNoQuotes = view.findViewById(R.id.text_liked_quotes);
        return view;
    }

    @Override
    public void showLikedQuotes(List<QuoteData> quoteList) {
            adapter = new QuoteAdapter(getActivity(), quoteList, new LClickListener() {
                @Override
                public void onPositionClicked(int position) {
                    QuoteData quoteData = quoteList.get(position);
                    daoLikedQuotes.deleteQuote(quoteData);
                    adapter.deleteI(position);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getActivity(), "Item is Removed", Toast.LENGTH_SHORT).show();
                }
            });
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        Animation animationHide = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_hide_anim);
        Animation animationShow = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_show_anim);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && bottomNavigationView.isShown()) {
                    bottomNavigationView.setVisibility(GONE);
                    bottomNavigationView.startAnimation(animationHide);
                } else if (dy < 0/* && bottomNavigationView.getVisibility() != View.VISIBLE*/) {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.startAnimation(animationShow);
                }
            }
        });
    }

    public void setDataShared(List<QuoteData> list){
        QuoteData quoteData = new QuoteData();
        quoteData.setQuote(sharedPreferences.getString("quote", "q"));
        quoteData.setAuthor(sharedPreferences.getString("author", "a"));
        quoteData.setUrlImage(sharedPreferences.getString("image", "i"));
        quoteData.setId(sharedPreferences.getInt("id", -1));
        quoteData.setIsLiked(sharedPreferences.getInt("liked", -1));
        quoteData.setIsLiked(1);
        daoLikedQuotes.setToTable(quoteData);
        adapter.addI(quoteData);
    }

    @Override
    public void showNoLikedQuotes() {
        textNoQuotes.setText("No Choosed Quotes");
    }

    @Override
    public void removeFromChoosedQuotes() {

    }

    @Override
    public void onPauseFragment(Fragment fragment) {
        if(fragment instanceof MainFragment) {
            Log.d(TAG_STATE, "onPauseFragment: LikedFragment " + fragment);
            MainFragment mainFragment = (MainFragment) fragment;
        }
    }

    @Override
    public void onResumeFragment() {
        Log.d(TAG_OTHER, "onResumeFragment: Liked Fragment" );
    }


    @Override
    public String toString() {
        return "LikedFragment{}";
    }
}
