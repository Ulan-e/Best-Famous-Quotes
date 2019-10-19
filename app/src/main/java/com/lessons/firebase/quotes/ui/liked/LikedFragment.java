package com.lessons.firebase.quotes.ui.liked;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lessons.firebase.quotes.utils.listeners.FragmentLifecycle;
import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.utils.listeners.OnPositionClickListener;
import com.lessons.firebase.quotes.adapter.QuoteAdapter;
import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.DaoLikedQuotes;
import com.lessons.firebase.quotes.di.components.LikedComponent;
import com.lessons.firebase.quotes.di.modules.source.SharedPrefModule;
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
        sharedPreferences = likedComponent.getSharedPreferences();

        presenter.loadLikedQuotes();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.liked_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_list:
                int size = daoLikedQuotes.getLikedQuotes().size();
                if(size > 0){
                    clearList();
                }else {
                    Toast.makeText(getActivity(), "List is Empty", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                break;
        }
        return false;
    }

    private void getMainComponent() {
        likedComponent = getAppComponent().likedComponentBuilder()
                .likedModule(new LikedModule(this))
                .sharedModule(new SharedPrefModule(getActivity()))
                .build();
        likedComponent.inject(this);
    }

    private void clearList() {
        if (adapter != null) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            dialogBuilder.setTitle("Are sure clear all liked list")
                    .setIcon(R.drawable.ic_clear_all_black_24dp)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            daoLikedQuotes.deleteAll();
                            adapter.deleteAllI();
                            Toast.makeText(getActivity(), " List is Cleared ", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            dialogBuilder.show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.liked_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_liked);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(false);
        textNoQuotes = view.findViewById(R.id.text_liked_quotes);
        return view;
    }

    @Override
    public void showLikedQuotes(List<QuoteData> quoteList) {
        adapter = new QuoteAdapter(getActivity(), new OnPositionClickListener() {
                @Override
                public void onPositionClicked(int position) {
                    QuoteData quoteData = quoteList.get(position);
                    if(quoteData != null){
                        daoLikedQuotes.deleteQuote(quoteData);
                        adapter.deleteI(position);
                        recyclerView.removeViewAt(position);
                    }

                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Item is Removed", Toast.LENGTH_SHORT).show();
                }
            });
        adapter.setList(quoteList);
        recyclerView.setAdapter(adapter);
        recyclerView.invalidate();

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

    public void setDataShared(){
        if(adapter != null) {
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
    }

    @Override
    public void showNoLikedQuotes() {
        recyclerView.setVisibility(GONE);
        textNoQuotes.setVisibility(View.VISIBLE);
        textNoQuotes.setText(" No Liked list ");
    }

    @Override
    public void removeFromLikedQuotes() {

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

}
