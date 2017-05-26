package com.example.panagiotis.marvelcomics.Services;


import com.example.panagiotis.marvelcomics.pojos.Example;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IData {
    @GET ("comics")
    Observable<Example> get_100_comics(
            @Query("limit") int limit,
            @Query("hash")String hash,
            @Query("apikey")String apikey,
            @Query("ts") String ts
    );

    @GET ("comics/{id}")
    Observable<Example> get_Details(
            @Path("id") String id,
            @Query("hash")String hash,
            @Query("apikey")String apikey,
            @Query("ts") String ts
    );
}
