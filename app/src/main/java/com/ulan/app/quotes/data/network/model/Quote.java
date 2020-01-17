package com.ulan.app.quotes.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quote {

    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("favorite")
    @Expose
    private Boolean favorite;
    @SerializedName("author_permalink")
    @Expose
    private String authorPermalink;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("favorites_count")
    @Expose
    private Integer favoritesCount;
    @SerializedName("upvotes_count")
    @Expose
    private Integer upvotesCount;
    @SerializedName("downvotes_count")
    @Expose
    private Integer downvotesCount;
    @SerializedName("dialogue")
    @Expose
    private Boolean dialogue;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("url")
    @Expose
    private String url;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getAuthorPermalink() {
        return authorPermalink;
    }

    public void setAuthorPermalink(String authorPermalink) {
        this.authorPermalink = authorPermalink;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public Integer getUpvotesCount() {
        return upvotesCount;
    }

    public void setUpvotesCount(Integer upvotesCount) {
        this.upvotesCount = upvotesCount;
    }

    public Integer getDownvotesCount() {
        return downvotesCount;
    }

    public void setDownvotesCount(Integer downvotesCount) {
        this.downvotesCount = downvotesCount;
    }

    public Boolean getDialogue() {
        return dialogue;
    }

    public void setDialogue(Boolean dialogue) {
        this.dialogue = dialogue;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
