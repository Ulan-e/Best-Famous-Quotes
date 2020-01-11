package com.ulan.app.quotes.data.network.api;


import com.ulan.app.quotes.data.network.PhotosResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 */

public interface PhotosApi {

    @GET("api/")
    Observable<PhotosResponse> getPhotos(
            @Query("key") String key,
            @Query("image_type") String imageType,
            @Query("per_page") int page,
            @Query("q") String q,
            @Query("editor_choice") boolean editorLike);
}
