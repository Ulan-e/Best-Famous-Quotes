package com.lessons.firebase.quotes.ui.list;

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
import android.widget.ProgressBar;
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
import com.lessons.firebase.quotes.di.components.ListComponent;
import com.lessons.firebase.quotes.di.modules.uimodules.MainModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.liked.LikedFragment;

import java.util.List;

import static android.view.View.GONE;
import static com.lessons.firebase.quotes.utils.Constants.TAG_STATE;

public class MainFragment extends BaseFragment implements MainFragmentView, FragmentLifecycle {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ListComponent listComponent;
    private MainFragmentPresenterImpl presenter;
    private DaoLikedQuotes daoLikedQuotes;
    private QuoteAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private SharedPreferences sharedPreferences;

    LikedFragment likedFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        sharedPreferences = getActivity().getSharedPreferences("ulan", Context.MODE_PRIVATE);

        getMainComponent();
        presenter = listComponent.getPresenter();
        presenter.loadQuotes();
        daoLikedQuotes = listComponent.getDaoLikedQuotes();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        switch (item.getItemId()){
            case R.id.filter:
                Toast.makeText(getActivity(), "Filter", Toast.LENGTH_SHORT).show();
                return true;
                default:
                    break;
        }
        return false;
    }

    public void getMainComponent(){
        listComponent = getAppComponent().mainActivityComponentBuilder()
                .mainModule(new MainModule(this))
                .build();
        listComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_quotes);


        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        Animation animationHide = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_hide_anim);
        Animation animationShow = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_show_anim);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.startAnimation(animationShow);
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0 && bottomNavigationView.isShown()){
                    bottomNavigationView.setVisibility(GONE);
                    bottomNavigationView.startAnimation(animationHide);
                } else if(dy < 0 && !bottomNavigationView.isShown()){
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.startAnimation(animationShow);
                }
            }
        });
        return view;
    }

    @Override
    public void showQuotes(List<QuoteData> listQuotes) {
        adapter = new QuoteAdapter(getActivity(), listQuotes, new LClickListener() {
             @Override
             public void onPositionClicked(int position) {
                 QuoteData quoteData = listQuotes.get(position);
                 sendData(quoteData);
                if (likedFragment != null){
                    likedFragment.setDataShared(listQuotes);
                }
                Toast.makeText(getActivity(), "Quote added to Liked", Toast.LENGTH_SHORT).show();
             }
         });
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void sendData(QuoteData quoteData) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", quoteData.getUrlImage());
        editor.putString("quote", quoteData.getQuote());
        editor.putString("author", quoteData.getAuthor());
        editor.putInt("id", quoteData.getId());
        editor.putInt("liked", quoteData.getIsLiked());
        editor.commit();
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showQuotesError(Throwable e) {
        /*Log.d(TAG_OTHER, "showQuotesError: " + e.getMessage());*/
    }

    @Override
    public void onPauseFragment(Fragment fragment) {
        if(fragment instanceof LikedFragment) {
            likedFragment = (LikedFragment) fragment;
            Log.d(TAG_STATE, "onPauseFragment: MainFragment " + fragment);
        }
    }

    @Override
    public void onResumeFragment() {
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
        Log.d(TAG_STATE, "onResumeFragment: MainFragment ");
    }

    @Override
    public String toString() {
        return "MainFragment{}";
    }
}
