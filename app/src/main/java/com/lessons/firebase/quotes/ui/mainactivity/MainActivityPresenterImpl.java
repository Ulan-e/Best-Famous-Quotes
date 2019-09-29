package com.lessons.firebase.quotes.ui.mainactivity;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    MainActivityView mView;

    public MainActivityPresenterImpl(MainActivityView mView) {
        this.mView = mView;
    }

    @Override
    public void setTitleToolbar() {
        mView.showToolbar("Quotes");
    }
}