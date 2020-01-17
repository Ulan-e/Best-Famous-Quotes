package com.ulan.app.quotes.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("pageURL")
    @Expose
    private String pageURL;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("previewURL")
    @Expose
    private Object previewURL;
    @SerializedName("previewWidth")
    @Expose
    private Integer previewWidth;
    @SerializedName("previewHeight")
    @Expose
    private Integer previewHeight;
    @SerializedName("webformatURL")
    @Expose
    private String webformatURL;
    @SerializedName("webformatWidth")
    @Expose
    private Integer webformatWidth;
    @SerializedName("webformatHeight")
    @Expose
    private Integer webformatHeight;
    @SerializedName("largeImageURL")
    @Expose
    private String largeImageURL;
    @SerializedName("fullHDURL")
    @Expose
    private String fullHDURL;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    @SerializedName("imageWidth")
    @Expose
    private Integer imageWidth;
    @SerializedName("imageHeight")
    @Expose
    private Integer imageHeight;
    @SerializedName("imageSize")
    @Expose
    private Integer imageSize;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("downloads")
    @Expose
    private Integer downloads;
    @SerializedName("favorites")
    @Expose
    private Integer favorites;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("userImageURL")
    @Expose
    private Object userImageURL;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Object getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(Object previewURL) {
        this.previewURL = previewURL;
    }

    public Integer getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(Integer previewWidth) {
        this.previewWidth = previewWidth;
    }

    public Integer getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(Integer previewHeight) {
        this.previewHeight = previewHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public Integer getWebformatWidth() {
        return webformatWidth;
    }

    public void setWebformatWidth(Integer webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public Integer getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(Integer webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getFullHDURL() {
        return fullHDURL;
    }

    public void setFullHDURL(String fullHDURL) {
        this.fullHDURL = fullHDURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public void setFavorites(Integer favorites) {
        this.favorites = favorites;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Object getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(Object userImageURL) {
        this.userImageURL = userImageURL;
    }
}
