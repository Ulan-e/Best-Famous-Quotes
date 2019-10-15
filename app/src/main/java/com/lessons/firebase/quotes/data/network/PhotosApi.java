package com.lessons.firebase.quotes.data.network;


import com.lessons.firebase.quotes.data.network.pojo.PhotosResponse;

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
            @Query("q") String q);
}
