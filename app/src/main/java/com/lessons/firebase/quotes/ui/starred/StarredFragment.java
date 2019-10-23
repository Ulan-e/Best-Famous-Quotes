package com.lessons.firebase.quotes.ui.starred;

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
import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.adapter.QuoteAdapter;
import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.DaoStarredQuotes;
import com.lessons.firebase.quotes.di.components.StarredComponent;
import com.lessons.firebase.quotes.di.modules.source.SharedPrefModule;
import com.lessons.firebase.quotes.di.modules.uimodules.StarredModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.home.HomeFragment;
import com.lessons.firebase.quotes.utils.listeners.FragmentLifecycle;

import java.util.List;

import static android.view.View.GONE;
import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;
import static com.lessons.firebase.quotes.utils.Constants.TAG_STATE;


public class StarredFragment extends BaseFragment implements StarredFragmentView, FragmentLifecycle {

    private RecyclerView mRecyclerView;
    private TextView mTextNoQuotes;
    private StarredComponent mStarredComponent;
    private QuoteAdapter mAdapter;

    private DaoStarredQuotes mDaoStarredQuotes;
    private StarredPresenterImpl mPresenter;
    private SharedPreferences mSharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getMainComponent();
        mPresenter.loadLikedQuotes();
    }

    private void getMainComponent() {
        mStarredComponent = getAppComponent().likedBuilder()
                .likedModule(new StarredModule(this))
                .sharedModule(new SharedPrefModule(getActivity()))
                .build();
        mStarredComponent.inject(this);

        mDaoStarredQuotes = mStarredComponent.getDaoQuotes();
        mPresenter = mStarredComponent.getPresenter();
        mSharedPreferences = mStarredComponent.getSharedPreferences();
    }

    public void setDataShared() {
        if (mAdapter != null) {
            QuoteData quoteData = new QuoteData();
            quoteData.setQuote(mSharedPreferences.getString("quote", "q"));
            quoteData.setAuthor(mSharedPreferences.getString("author", "buttun_with_border"));
            quoteData.setUrlImage(mSharedPreferences.getString("image", "i"));
            quoteData.setId(mSharedPreferences.getInt("id", -1));
            quoteData.setIsLiked(mSharedPreferences.getInt("liked", -1));
            quoteData.setIsLiked(1);
            mDaoStarredQuotes.setToTable(quoteData);
            mAdapter.addQuote(quoteData);
            mAdapter.notifyDataSetChanged();
        }
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
                int size = mDaoStarredQuotes.getLikedQuotes().size();
                if (size > 0) {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.liked_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_liked);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setNestedScrollingEnabled(false);
        mTextNoQuotes = view.findViewById(R.id.text_liked_quotes);
        return view;
    }

    @Override
    public void showLikedQuotes(List<QuoteData> quoteList) {
        mAdapter = new QuoteAdapter(getActivity(), quoteList, position -> {
            QuoteData quoteData = quoteList.get(position);
            quoteList.remove(position);
            mAdapter.notifyItemRemoved(position);
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRangeChanged(position, quoteList.size());
            mDaoStarredQuotes.deleteQuote(quoteData);

            Toast.makeText(getActivity(), "Item is Removed", Toast.LENGTH_SHORT).show();
        });
        mRecyclerView.setAdapter(mAdapter);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        Animation animationHide = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_hide_anim);
        Animation animationShow = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_show_anim);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && bottomNavigationView.isShown()) {
                    bottomNavigationView.setVisibility(GONE);
                    bottomNavigationView.startAnimation(animationHide);
                } else if (dy < 0 && bottomNavigationView.getVisibility() != View.VISIBLE) {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.startAnimation(animationShow);
                }
            }
        });
    }

    @Override
    public void showNoLikedQuotes() {
        mRecyclerView.setVisibility(GONE);
        mTextNoQuotes.setVisibility(View.VISIBLE);
        mTextNoQuotes.setText(" Starred list is empty ");
    }


    private void showDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_clear_list);

        TextView text = (TextView) dialog.findViewById(R.id.clear_list_title);

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
                mPresenter.getInfoClearList(mAdapter, mDaoStarredQuotes);
                dialog.cancel();
            }
        });
        dialog.show();
    }

    @Override
    public void clearAllQuotes() {
        mDaoStarredQuotes.deleteAll();
        mAdapter.deleteLikedQuotes();
        Toast.makeText(getActivity(), " List is Cleared ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPauseFragment(Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            Log.d(TAG_STATE, "onPauseFragment: StarredFragment " + fragment);
            HomeFragment homeFragment = (HomeFragment) fragment;
        }
    }

    @Override
    public void onResumeFragment() {
        Log.d(TAG_OTHER, "onResumeFragment: StarredFragment");
    }

}
