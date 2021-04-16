package com.ulan.app.quotes.data.network.responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ulan.app.quotes.data.network.model.Quote;
import java.util.List;

public class QuotesResponse {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("last_page")
    @Expose
    private Boolean lastPage;
    @SerializedName("quotes")
    @Expose
    private List<Quote> quotes = null;

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