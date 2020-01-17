package com.ulan.app.quotes.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ulan.app.quotes.R;
import com.ulan.app.quotes.adapter.QuoteAdapter;
import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.di.qualifires.filters.FilterRandom;
import com.ulan.app.quotes.ui.base.BaseFragment;
import com.ulan.app.quotes.ui.listeners.FragmentLifecycle;
import com.ulan.app.quotes.ui.listeners.ShareQuotesListener;
import com.ulan.app.quotes.ui.main.MainActivity;
import com.ulan.app.quotes.ui.starred.StarredFragment;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static android.view.View.GONE;
import static com.ulan.app.quotes.helpers.Constants.TAG_OTHER;
import static com.ulan.app.quotes.helpers.Constants.TAG_STATE;

public class HomeFragment extends BaseFragment implements HomeView, FragmentLifecycle, ShareQuotesListener {

    private StarredFragment mStarredFragment;
    private RecyclerView mRecyclerView;
    private QuoteAdapter mAdapter;

    private BottomNavigationView mBottomNavigationView;
    private Animation mAnimationBottomHide;
    private Animation mAnimationBottomShow;
    private LinearLayout mLinearLayout;

    @FilterRandom
    @Inject
    public Observable<List<QuoteModel>> mQuotes;

    @Inject
    public HomePresenterImpl mPresenter;

    @Inject
    public SharedPreferences mPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).setOnSendListener(this);
        mPresenter.setQuotes(mQuotes);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        mLinearLayout = view.findViewById(R.id.error_layout);
        Button retryButton = view.findViewById(R.id.retry_button);
        mRecyclerView = view.findViewById(R.id.recycler_view_quotes);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mQuotes != null) {
                    mPresenter.setQuotes(mQuotes);
                }
                mLinearLayout.setVisibility(GONE);
            }
        });
        initAnimationsBottomNav();
        mRecyclerView.addOnScrollListener(recyclerScrollListener);
        return view;
    }

    private void initAnimationsBottomNav(){
        mBottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        mAnimationBottomHide = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_hide_anim);
        mAnimationBottomShow = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_show_anim);
    }

    private RecyclerView.OnScrollListener recyclerScrollListener =
            new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                    }
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0 && mBottomNavigationView.isShown()) {
                        mBottomNavigationView.setVisibility(GONE);
                        mBottomNavigationView.startAnimation(mAnimationBottomHide);
                    } else if (dy < 0 && !mBottomNavigationView.isShown()) {
                        mBottomNavigationView.setVisibility(View.VISIBLE);
                        mBottomNavigationView.startAnimation(mAnimationBottomShow);
                    }
                }
            };

    private void sendQuoteWithSharedPref(QuoteModel quoteData) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("image", quoteData.getUrlImage());
        editor.putString("quote", quoteData.getQuote());
        editor.putString("author", quoteData.getAuthor());
        editor.putInt("id", quoteData.getId());
        editor.putInt("liked", quoteData.getIsLiked());
        editor.apply();
    }

    @Override
    public void showQuotes(List<QuoteModel> listQuotes) {
        mAdapter = new QuoteAdapter(getActivity(), listQuotes, position -> {
            QuoteModel quoteData = listQuotes.get(position);
            sendQuoteWithSharedPref(quoteData);
            if (mStarredFragment != null) {
                mStarredFragment.setSharedQuote();
                Toast.makeText(getActivity(), "Quote added to Starred", Toast.LENGTH_SHORT).show();
            }
            mLinearLayout.setVisibility(GONE);
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showNoQuotes(Throwable e) {
        Log.d(TAG_OTHER, "showNoQuotes: " + e.getMessage());
        mRecyclerView.setVisibility(GONE);
        mLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.resetQuotes();
        mPresenter.detachView();
    }

    @Override
    public void onPauseFragment(Fragment fragment) {
        if (fragment instanceof StarredFragment) {
            mStarredFragment = (StarredFragment) fragment;
            Log.d(TAG_STATE, "onPauseFragment: HomeFragment " + fragment);
        }
    }

    @Override
    public void onResumeFragment() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        Log.d(TAG_STATE, "onResumeFragment: HomeFragment ");
    }

    @Override
    public void passQuotes(Observable<List<QuoteModel>> quotes) {
        if (quotes == null) {
            throw new IllegalArgumentException("Quotes should not be null");
        }
        mPresenter.setQuotes(quotes);
    }

}
