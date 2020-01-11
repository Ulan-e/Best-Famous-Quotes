package com.ulan.app.quotes.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ulan.app.quotes.data.network.model.Quote;


import java.util.List;

public class QuotesResponse {

    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("last_page")
    @Expose
    public Boolean lastPage;
    @SerializedName("quotes")
    @Expose
    public List<Quote> quotes = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getLastPage() {
        return lastPage;
    }

    public void setLastPage(Boolean lastPage) {
        this.lastPage = lastPage;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
