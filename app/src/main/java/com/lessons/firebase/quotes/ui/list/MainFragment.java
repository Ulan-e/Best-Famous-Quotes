package com.lessons.firebase.quotes.ui.list;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.lessons.firebase.quotes.di.components.ListComponent;
import com.lessons.firebase.quotes.di.modules.source.SharedPrefModule;
import com.lessons.firebase.quotes.di.modules.uimodules.ListModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.liked.LikedFragment;
import com.lessons.firebase.quotes.ui.mainactivity.MainActivity;
import com.lessons.firebase.quotes.utils.listeners.FragmentLifecycle;
import com.lessons.firebase.quotes.utils.listeners.OnPositionClickListener;
import com.lessons.firebase.quotes.utils.listeners.ShareObservableListener;

import java.util.List;

import io.reactivex.Observable;

import static android.view.View.GONE;
import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;
import static com.lessons.firebase.quotes.utils.Constants.TAG_STATE;

public class MainFragment extends BaseFragment implements MainFragmentView, FragmentLifecycle, ShareObservableListener {

    private ListComponent listComponent;
    private MainFragmentPresenterImpl presenter;
    private SharedPreferences sharedPreferences;
    private LikedFragment likedFragment;
    private RecyclerView recyclerView;
    private QuoteAdapter adapter;

    private BottomNavigationView bottomNavigationView;
    private Animation animationBottomHide;
    private Animation animationBottomShow;

    public Observable<List<QuoteData>> ObservableList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).setOnSendListener(this);

        initListComponent();
        initDataFromMainComponent();
        presenter.loadQuotes(ObservableList);
    }

    private void initDataFromMainComponent() {
        presenter = listComponent.getPresenter();
        sharedPreferences = listComponent.getSharedPreference();
        ObservableList = listComponent.getObservableList();
    }

    private void initListComponent() {
        listComponent = getAppComponent().listComponentBuilder()
                .mainModule(new ListModule(this))
                .sharedModule(new SharedPrefModule(getActivity()))
                .build();
        listComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        animationBottomHide = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_hide_anim);
        animationBottomShow = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_show_anim);

        recyclerView = view.findViewById(R.id.recycler_view_quotes);
        recyclerView.addOnScrollListener(recyclerScrollListener);
        return view;
    }

    private RecyclerView.OnScrollListener recyclerScrollListener =
            new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        bottomNavigationView.setVisibility(View.VISIBLE);
                        bottomNavigationView.startAnimation(animationBottomShow);
                    }
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0 && bottomNavigationView.isShown()) {
                        bottomNavigationView.setVisibility(GONE);
                        bottomNavigationView.startAnimation(animationBottomHide);
                    } else if (dy < 0 && !bottomNavigationView.isShown()) {
                        bottomNavigationView.setVisibility(View.VISIBLE);
                        bottomNavigationView.startAnimation(animationBottomShow);
                    }
                }
            };

    private void sendData(QuoteData quoteData) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", quoteData.getUrlImage());
        editor.putString("quote", quoteData.getQuote());
        editor.putString("author", quoteData.getAuthor());
        editor.putInt("id", quoteData.getId());
        editor.putInt("liked", quoteData.getIsLiked());
        editor.apply();
    }

    @Override
    public void showQuotes(List<QuoteData> listQuotes) {
        adapter = new QuoteAdapter(getActivity(), new OnPositionClickListener() {
            @Override
            public void onPositionClicked(int position) {
                QuoteData quoteData = listQuotes.get(position);
                sendData(quoteData);
                if (likedFragment != null) {
                    likedFragment.setDataShared();
                }
                Toast.makeText(getActivity(), "Quote added to Liked", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setList(listQuotes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showQuotesError(Throwable e) {
        Log.d(TAG_OTHER, "showQuotesError: " + e.getMessage());
        Toast.makeText(getActivity(), "Please connect your phone to internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPauseFragment(Fragment fragment) {
        if (fragment instanceof LikedFragment) {
            likedFragment = (LikedFragment) fragment;
            Log.d(TAG_STATE, "onPauseFragment: MainFragment " + fragment);
        }
    }

    @Override
    public void onResumeFragment() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        Log.d(TAG_STATE, "onResumeFragment: MainFragment ");
    }

    @Override
    public void passObservable(Observable<List<QuoteData>> listObservable) {
        if (listObservable == null) {
            Log.d(TAG_OTHER, "passObservable: Observable<List<QuoteData>> is null object");
        }
        presenter.loadQuotes(listObservable);
    }
}
