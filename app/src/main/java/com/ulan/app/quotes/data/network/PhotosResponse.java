package com.ulan.app.quotes.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ulan.app.quotes.data.network.model.Photo;

import java.util.List;

public class PhotosResponse {

    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("totalHits")
    @Expose
    public Integer totalHits;
    @SerializedName("hits")
    @Expose
    public List<Photo> hits = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public List<Photo> getHits() {
        return hits;
    }

    public void setHits(List<Photo> hits) {
        this.hits = hits;
    }
}
