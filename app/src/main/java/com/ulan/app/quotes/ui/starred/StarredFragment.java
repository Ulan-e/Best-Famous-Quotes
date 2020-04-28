package com.ulan.app.quotes.ui.starred;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
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
import com.ulan.app.quotes.data.database.DaoStarredQuotes;
import com.ulan.app.quotes.di.scopes.AppScope;
import com.ulan.app.quotes.ui.base.BaseFragment;
import com.ulan.app.quotes.ui.home.HomeFragment;
import com.ulan.app.quotes.ui.listeners.FragmentLifecycle;

import java.util.List;

import javax.inject.Inject;

import static android.view.View.GONE;
import static com.ulan.app.quotes.helpers.Constants.TAG_OTHER;
import static com.ulan.app.quotes.helpers.Constants.TAG_STATE;


public class StarredFragment extends BaseFragment implements StarredView, FragmentLifecycle {

    private RecyclerView mRecyclerView;
    private TextView mTextNoQuotes;
    private QuoteAdapter mAdapter;
    private BottomNavigationView mBottomNavigationView;
    private Animation mAnimationHide;
    private Animation mAnimationShow;

    @AppScope
    @Inject
    public DaoStarredQuotes mDaoStarredQuotes;

    @Inject
    public StarredPresenterImpl mPresenter;

    @Inject
    public SharedPreferences mSharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPresenter.setStarredQuotes(mDaoStarredQuotes.getAll());
        mPresenter.loadStarredQuotes();
    }

    public void setSharedQuote() {
        if (mAdapter != null) {
            QuoteModel quoteData = new QuoteModel();
            quoteData.setQuote(mSharedPreferences.getString("quote", "q"));
            quoteData.setAuthor(mSharedPreferences.getString("author", "buttun_with_border"));
            quoteData.setUrlImage(mSharedPreferences.getString("image", "i"));
            quoteData.setId(mSharedPreferences.getInt("id", -1));
            quoteData.setIsLiked(mSharedPreferences.getInt("liked", -1));
            quoteData.setIsLiked(1);
            mDaoStarredQuotes.insert(quoteData);
            mAdapter.addToStarred(quoteData);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.starred_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_liked);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setNestedScrollingEnabled(false);
        mTextNoQuotes = view.findViewById(R.id.text_liked_quotes);
        return view;
    }

    @Override
    public void showStarredQuotes(List<QuoteModel> quoteList) {
        initBottomNavAnimation();
        mAdapter = new QuoteAdapter(getActivity(), quoteList, position -> {
            QuoteModel quoteData = quoteList.get(position);
            quoteList.remove(position);
            mAdapter.notifyItemRemoved(position);
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRangeChanged(position, quoteList.size());
            mDaoStarredQuotes.delete(quoteData);

            Toast.makeText(getActivity(), "Item is Removed", Toast.LENGTH_SHORT).show();
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(onScrollListener);

    }

    private void initBottomNavAnimation(){
        mBottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        mAnimationHide = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_hide_anim);
        mAnimationShow = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_show_anim);
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
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
                mBottomNavigationView.startAnimation(mAnimationHide);
            } else if (dy < 0 && mBottomNavigationView.getVisibility() != View.VISIBLE) {
                mBottomNavigationView.setVisibility(View.VISIBLE);
                mBottomNavigationView.startAnimation(mAnimationShow);
            }
        }
    };

    @Override
    public void showNoStarredQuotes() {
        mRecyclerView.setVisibility(GONE);
        mTextNoQuotes.setVisibility(View.VISIBLE);
        mTextNoQuotes.setText(" Starred list is empty ");
    }

    @Override
    public void removeAllQuotes() {
        mDaoStarredQuotes.deleteAll();
        mAdapter.removeFromStarred();
        Toast.makeText(getActivity(), " List is Cleared ", Toast.LENGTH_SHORT).show();
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
                if (mAdapter.getItemCount() > 0) {
                    showDialog();
                } else {
                    Toast.makeText(getActivity(), "List is Empty", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                break;
        }
        return false;
    }

    private void showDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_clear_list);

        Button dialogBtn_cancel = (Button) dialog.findViewById(R.id.cancel_clear_button);
        dialogBtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button dialogBtn_okay = (Button) dialog.findViewById(R.id.ok_clear_button);
        dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.clearStarredQuotes(mAdapter, mDaoStarredQuotes);
                dialog.cancel();
            }
        });
        dialog.show();
    }

    @Override
    public void onPauseFragment(Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            Log.d(TAG_STATE, "onPauseFragment: StarredFragment " + fragment);
        }
    }

    @Override
    public void onResumeFragment() {
        Log.d(TAG_OTHER, "onResumeFragment: StarredFragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.disposeObservable();
    }
}
